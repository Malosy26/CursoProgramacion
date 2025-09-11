# Ejercicio práctico 004 · Agencia de viajes local


## Fase 1: Análisis y Casos de Uso

Primero apuntaremos las entidades que han sido identificadas:


`Paquete turistico` 
Es el producto principal de la agencia. Un paquete está compuesto por varios ítems.

`Item de paquete` 
Elementos individuales que conforman un paquete:

>1-`Vuelo`
con atributos como ciudad de origen, destino, aerolínea, fecha y hora.

>2-`Hotel`
con nombre, ciudad, número de noches y precio por noche.

>3-`Atraccion`
con duracion estimada y ciudad.

`Cliente`
La persona que realiza una reserva.

`Reserva`
La venta de un paquete a un cliente, que puede ser para varias personas.

### Casos de uso (Funcionalidades):

`Gestión de Paquetes:`

>Crear un nuevo paquete y añadir, modificar o eliminar ítems de forma ordenada.
 Duplicar un paquete base para modificarlo.
Actualizar automáticamente el coste total de un paquete cuando se añaden o eliminan ítems.


`Gestión de Reservas y Clientes:`

>Registrar una reserva de un paquete para un cliente, especificando el número de personas.
Consultar el historial de reservas de un cliente.
Identificar a los "mejores clientes" por facturación o número de viajes.


`Análisis e Informes:`

>Calcular cuántas personas han viajado con cada paquete.
Calcular el dinero generado por cada cliente.
Ver qué paquetes son los más vendidos en cada temporada.
Identificar paquetes que nunca se han reservado.
Analizar la popularidad de destinos por país o ciudad.

## Fase 2: Diagrama Entidad-Relacion

![Diagrama EntidadRelacion](img/entidadRelacion.png)

## Fase 3: Diagrama normalizado

![Diagrama Normalizado](img/diagramaNormalizado.png)

## Fase 4: Creación de tablas

```sql
-- Se apunta al schema travel creado dentro de la database travelworks 
-- asi nos aseguramos que no se creen en schema public
SET search_path TO travel;


-- Tabla principal de clientes
CREATE TABLE Clientes (
    cliente_id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255),
    correo_elec VARCHAR(255) UNIQUE,
    telefono VARCHAR(20)
);

-- Tabla principal de paquetes
CREATE TABLE Paquete (
    paquete_id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    -- El coste total se calculará dinámicamente o con un trigger
    coste_total NUMERIC(10, 2) DEFAULT 0.00
);

-- Tabla de tipo de ítem: Vuelos
CREATE TABLE Vuelo (
    id_vuelo SERIAL PRIMARY KEY,
    aerolinea VARCHAR(100),
    origen VARCHAR(100),
    destino VARCHAR(100),
    fecha_hora_salida TIMESTAMP NOT NULL
);

-- Tabla de tipo de ítem: Hoteles
CREATE TABLE Hotel (
    id_hotel SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    ciudad VARCHAR(100),
    num_noches INT,
    precio_noche NUMERIC(10, 2)
);

-- Tabla de tipo de ítem: Atracciones
CREATE TABLE Atracciones (
    id_atracciones SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    ciudad VARCHAR(100),
    duracion_estimada_min INT
);

-- Tabla intermedia para los ítems generales del paquete
CREATE TABLE Item (
    id_item SERIAL PRIMARY KEY,
    paquete_id INT NOT NULL REFERENCES Paquete(paquete_id) ON DELETE CASCADE,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    coste NUMERIC(10, 2) NOT NULL,
    -- Aquí se añaden las claves foráneas a cada tipo de ítem, permitiendo valores nulos
    id_vuelo INT REFERENCES Vuelo(id_vuelo) ON DELETE SET NULL,
    id_hotel INT REFERENCES Hotel(id_hotel) ON DELETE SET NULL,
    id_atracciones INT REFERENCES Atracciones(id_atracciones) ON DELETE SET NULL
);

-- Tabla intermedia para unir ítems con sus tipos específicos
CREATE TABLE Item_vuelo (
    id_item INT NOT NULL REFERENCES Item(id_item) ON DELETE CASCADE,
    id_vuelo INT NOT NULL REFERENCES Vuelo(id_vuelo) ON DELETE CASCADE,
    PRIMARY KEY (id_item, id_vuelo)
);

-- Tabla intermedia para unir ítems con sus tipos específicos
CREATE TABLE Item_hotel (
    id_item INT NOT NULL REFERENCES Item(id_item) ON DELETE CASCADE,
    id_hotel INT NOT NULL REFERENCES Hotel(id_hotel) ON DELETE CASCADE,
    PRIMARY KEY (id_item, id_hotel)
);

-- Tabla intermedia para unir ítems con sus tipos específicos
CREATE TABLE Item_atracciones (
    id_item INT NOT NULL REFERENCES Item(id_item) ON DELETE CASCADE,
    id_atracciones INT NOT NULL REFERENCES Atracciones(id_atracciones) ON DELETE CASCADE,
    PRIMARY KEY (id_item, id_atracciones)
);

-- Tabla de reservas
CREATE TABLE Reserva (
    id_reserva SERIAL PRIMARY KEY,
    id_cliente INT NOT NULL REFERENCES Clientes(cliente_id) ON DELETE RESTRICT,
    fecha_reserva DATE NOT NULL DEFAULT CURRENT_DATE,
    num_personas INT NOT NULL,
    coste_reserva NUMERIC(10, 2) NOT NULL
);

-- Tabla intermedia para la relación muchos a muchos entre Paquete y Reserva
CREATE TABLE Reserva_paquete (
    id_reserva INT NOT NULL REFERENCES Reserva(id_reserva) ON DELETE CASCADE,
    id_paquete INT NOT NULL REFERENCES Paquete(paquete_id) ON DELETE RESTRICT,
    PRIMARY KEY (id_reserva, id_paquete)
);
```



---
>Se decide no usar herencias para que la base de datos sea compatible para cualquier sistema además si, en el caso de haberlas utilizado, si tuvieramos que añadir mas tipos de item seria más complicado.

---

### 📝 Comentarios de Diseño y Justificación Técnica del Modelo Normalizado

---

### **Estructura del Modelo y Normalización**

Este diseño de base de datos adopta un enfoque **normalizado**, que es el estándar de la industria y la mejor práctica para garantizar la integridad y la portabilidad de los datos. A diferencia de la herencia de PostgreSQL, este modelo es compatible con cualquier sistema de gestión de bases de datos relacionales (RDBMS) como MySQL, Oracle o SQL Server. La clave de esta estructura es la separación de las entidades principales (`Vuelo`, `Hotel`, `Atracciones`) y el uso de tablas intermedias (`Item`, `Reserva_paquete`) para gestionar las relaciones entre ellas.

---

### **Claves Foráneas y Reglas de Eliminación (`ON DELETE`)**

Las cláusulas `ON DELETE` en las claves foráneas son cruciales para definir el comportamiento del sistema cuando se intenta eliminar un registro de una tabla "padre" que tiene registros "hijos" relacionados.

#### **1. `ON DELETE CASCADE`**

Esta regla se utiliza en las tablas intermedias que representan relaciones de composición fuerte, donde el registro "hijo" no tiene sentido sin su registro "padre".

* **En la tabla `Item`**: `REFERENCES Paquete(paquete_id) ON DELETE CASCADE`. Esto significa que si se elimina un `Paquete`, todos los `Items` asociados a ese paquete se eliminarán automáticamente. Esto es lógico, ya que un `ítem` como parte de un itinerario no puede existir si el `paquete` al que pertenece ha sido borrado.
* **En las tablas `Item_vuelo`, `Item_hotel`, `Item_atracciones`**: `REFERENCES Item(id_item) ON DELETE CASCADE`. Si un `Item` general se borra, el registro en estas tablas intermedias también se elimina.

#### **2. `ON DELETE RESTRICT`**

Esta regla se aplica en relaciones críticas para la integridad del negocio, donde la eliminación de un registro padre podría llevar a la pérdida de información histórica y valiosa.

* **En la tabla `Reserva`**: `REFERENCES Clientes(cliente_id) ON DELETE RESTRICT`. Se prohíbe la eliminación de un `Cliente` si tiene `Reservas` asociadas. Esto protege el historial de ventas y la facturación de cada cliente, que son datos vitales para el análisis de negocio.
* **En la tabla `Reserva_paquete`**: `REFERENCES Paquete(paquete_id) ON DELETE RESTRICT`. Se impide la eliminación de un `Paquete` si existen `Reservas` asociadas a él a través de esta tabla intermedia. Esto garantiza que la información de las ventas no se pierda si un paquete se retira de la oferta.

---

### **Justificación Adicional de las Tablas Intermedias**

* **Tabla `Reserva_paquete`**: Esta tabla resuelve la relación **muchos a muchos** entre `Reserva` y `Paquete`. Una reserva puede incluir múltiples paquetes (por ejemplo, una persona que reserva un paquete de viaje de ida y vuelta), y un paquete puede ser parte de muchas reservas. Esta tabla es esencial para registrar cada transacción de manera precisa.
* **Tablas `Item`, `Item_vuelo`, etc.**: Este diseño, con una tabla `Item` genérica y tablas intermedias para cada tipo específico, evita la necesidad de usar una tabla con muchas columnas nulas (el "anti-patrón" de la "súper-tabla"). Esto mantiene la base de datos limpia, organizada y fácil de mantener.

En resumen, la combinación de un diseño normalizado con las reglas de eliminación apropiadas (`CASCADE` para composición, `RESTRICT` para integridad histórica) crea un esquema robusto y coherente, listo para la gestión de datos a largo plazo.


## FASE 5: INDICES y VISTAS
### INDICES
```sql
-- Índice para búsquedas rápidas de clientes
-- y sus reservas, mejorando las consultas de historial.
CREATE INDEX idx_cliente_reservas ON Reserva(id_cliente);
/*
SELECT * FROM Reserva WHERE id_cliente = 123;
En lugar de revisar toda la base de datos con este indice
hacemos que se guarden en estructuras ARBOL para que sea mas facil su acceso
recordar que:
Consumen espacio extra en disco 
porque la BD guarda estructuras internas (árboles B+ generalmente).
*/

-- Índice para búsquedas por fechas en las reservas, útil
-- para análisis de temporada.
CREATE INDEX idx_fecha_reserva ON Reserva(fecha_reserva);

-- Índice para búsquedas eficientes de paquetes y sus ítems.
-- Ayuda en las uniones entre 'Paquete' e 'Item'.
CREATE INDEX idx_paquete_items ON Item(paquete_id);

-- Índices para búsquedas geográficas, cruciales para los
-- análisis por ciudad o destino.
CREATE INDEX idx_destino_vuelo ON Vuelo(destino);
CREATE INDEX idx_ciudad_hotel ON Hotel(ciudad);
CREATE INDEX idx_ciudad_atraccion ON Atracciones(ciudad);

-- Índice para optimizar las búsquedas de reservas por paquete.
CREATE INDEX idx_paquete_reserva_paquete ON Reserva_paquete(id_paquete);

```
### VISTAS

```sql
-- Vista para el análisis de los paquetes más populares
-- Muestra el número de reservas y el total de viajeros para cada paquete.
CREATE VIEW paquetes_populares AS
SELECT
    p.nombre AS nombre_paquete,
    COUNT(rp.id_reserva) AS num_reservas,
    SUM(r.num_personas) AS total_viajeros
FROM Paquete AS p
JOIN Reserva_paquete AS rp ON p.paquete_id = rp.id_paquete
JOIN Reserva AS r ON rp.id_reserva = r.id_reserva
GROUP BY p.nombre
ORDER BY num_reservas DESC;

-- Vista para el análisis de los mejores clientes por facturación
-- Muestra el gasto total de cada cliente.
CREATE VIEW mejores_clientes_facturacion AS
SELECT
    c.nombre,
    c.apellidos,
    SUM(r.coste_reserva) AS facturacion_total
FROM Clientes AS c
JOIN Reserva AS r ON c.cliente_id = r.id_cliente
GROUP BY c.nombre, c.apellidos
ORDER BY facturacion_total DESC;


```
