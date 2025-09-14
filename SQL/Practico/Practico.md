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

```SQL
SET search_path TO travel;

-- Creamos la tabla principal de paquetes
CREATE TABLE Paquete (
    paquete_id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    coste_total NUMERIC(10, 2) DEFAULT 0.00
);

-- Creamos la tabla genérica de ítems, que contiene los atributos comunes
CREATE TABLE Item (
    id_item SERIAL PRIMARY KEY,
    paquete_id INT NOT NULL REFERENCES Paquete(paquete_id) ON DELETE CASCADE,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    coste NUMERIC(10, 2) NOT NULL
);

-- Creamos las tablas de tipo de ítem con la clave foránea a la tabla 'Item'
-- La relación es 1:1, así que la FK es también la PK
CREATE TABLE Vuelo (
    id_item INT PRIMARY KEY REFERENCES Item(id_item) ON DELETE CASCADE,
    aerolinea VARCHAR(100),
    origen VARCHAR(100),
    destino VARCHAR(100),
    fecha_hora_salida TIMESTAMP NOT NULL
);

CREATE TABLE Hotel (
    id_item INT PRIMARY KEY REFERENCES Item(id_item) ON DELETE CASCADE,
    nombre VARCHAR(255),
    ciudad VARCHAR(100),
    num_noches INT,
    precio_noche NUMERIC(10, 2)
);

CREATE TABLE Atracciones (
    id_item INT PRIMARY KEY REFERENCES Item(id_item) ON DELETE CASCADE,
    nombre VARCHAR(255),
    ciudad VARCHAR(100),
    duracion_estimada_min INT
);

-- Creamos las tablas de Clientes y Reservas
CREATE TABLE Clientes (
    cliente_id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255),
    correo_elec VARCHAR(255) UNIQUE,
    telefono VARCHAR(20)
);

CREATE TABLE Reserva (
    id_reserva SERIAL PRIMARY KEY,
    id_cliente INT NOT NULL REFERENCES Clientes(cliente_id) ON DELETE RESTRICT,
    fecha_reserva DATE NOT NULL DEFAULT CURRENT_DATE,
    num_personas INT NOT NULL,
    coste_reserva NUMERIC(10, 2) NOT NULL
);

-- Tabla intermedia para la relación de muchos a muchos entre Paquete y Reserva
CREATE TABLE Reserva_paquete (
    id_reserva INT NOT NULL REFERENCES Reserva(id_reserva) ON DELETE CASCADE,
    id_paquete INT NOT NULL REFERENCES Paquete(paquete_id) ON DELETE RESTRICT,
    PRIMARY KEY (id_reserva, id_paquete)
);
```
---

### **📝 Comentarios de Diseño y Justificación Técnica del Modelo Normalizado Simplificado**

Este diseño de base de datos se basa en un modelo relacional normalizado y simplificado, lo que lo hace ideal para la enseñanza y su aplicación en cualquier sistema de gestión de bases de datos relacionales (RDBMS)[cite: 8]. La principal diferencia con respecto a modelos más complejos es la eliminación de tablas intermedias para las relaciones uno a uno (1:1), lo que resulta en un esquema más directo y fácil de mantener.

---

### **Claves Foráneas y Reglas de Eliminación (`ON DELETE`)**

El modelo establece reglas claras para mantener la integridad referencial y la coherencia de los datos.

* **Relación Clientes a Reserva (`1:M`)**: Se implementa con la clave foránea `id_cliente` en la tabla `Reserva`[cite: 38]. La restricción `ON DELETE RESTRICT` en esta relación es fundamental para la protección de datos históricos. Esto evita que se elimine a un cliente que tiene reservas registradas, lo que salvaguarda el historial de ventas y la información de facturación.
* **Relación Reserva a Paquete (`M:M`)**: Se resuelve con la tabla intermedia `Reserva_paquete`. Esta estructura permite que una reserva contenga múltiples paquetes y que un paquete sea parte de múltiples reservas. El uso de `ON DELETE CASCADE` en `id_reserva` asegura que si una reserva se borra, también se eliminan sus entradas en la tabla intermedia, mientras que `ON DELETE RESTRICT` en `id_paquete` protege a los paquetes de ser eliminados si aún están referenciados en una reserva.
* **Relación Paquete a Ítem (`1:M`)**: La clave foránea `paquete_id` en la tabla `Item` define esta relación. La regla `ON DELETE CASCADE` es apropiada aquí, ya que si un paquete se elimina, todos sus ítems asociados deben eliminarse automáticamente, dado que un ítem no tiene sentido fuera del contexto de su paquete.
* **Relación Ítem a Tipo de Ítem (`1:1`)**: Esta es una de las decisiones de diseño más importantes. Se ha implementado haciendo que la clave foránea `id_item` en las tablas `Vuelo`, `Hotel` y `Atracciones` sea también la clave primaria. Esto garantiza que cada ítem genérico se corresponda con un único registro de tipo específico, como un vuelo, un hotel o una atracción. Con esto, se evita la necesidad de tablas intermedias adicionales.

---

### **Disparadores (`Triggers`) y Procedimientos Almacenados**

Las decisiones sobre `triggers` y procedimientos almacenados se basan en la automatización de tareas y la lógica de negocio.

* **Trigger de Coste**: Se implementa un `trigger` que se activa al insertar, actualizar o eliminar un ítem.La función asociada a este `trigger` recalcula el `coste_total` en la tabla `Paquete` para asegurar que el precio total se mantenga siempre actualizado automáticamente, reflejando fielmente el coste de todos sus ítems.
* **Procedimientos de Gestión**: Se crean procedimientos almacenados separados para tareas específicas: uno para crear un nuevo paquete vacío y otro para duplicar un paquete existente. Esta división de responsabilidades hace que el código sea más claro, modular y fácil de mantener. La lógica para duplicar un paquete es compleja y requiere copiar datos de varias tablas, lo que hace que un procedimiento almacenado sea la herramienta ideal para esta operación.

---
## FASE 5 CREAR VISTAS E INDICES

#### INDICES
```SQL
SET search_path TO travel;

-- Índice para búsquedas rápidas de clientes
-- Útil para ver el historial de reservas de un cliente.
CREATE INDEX idx_cliente_reservas ON Reserva(id_cliente);

-- Índice para búsquedas por fechas en las reservas
-- Crucial para el análisis de temporada y rendimiento.
CREATE INDEX idx_fecha_reserva ON Reserva(fecha_reserva);

-- Índice para optimizar las búsquedas de reservas por paquete
-- Acelera las uniones entre 'Reserva' y 'Reserva_paquete'.
CREATE INDEX idx_paquete_reserva_paquete ON Reserva_paquete(id_paquete);

-- Índice para búsquedas de paquetes por su nombre.
CREATE INDEX idx_nombre_paquete ON Paquete(nombre);

-- Índices para búsquedas geográficas
-- Aceleran los análisis de popularidad por destino.
CREATE INDEX idx_destino_vuelo ON Vuelo(destino);
CREATE INDEX idx_ciudad_hotel ON Hotel(ciudad);
CREATE INDEX idx_ciudad_atraccion ON Atracciones(ciudad);

-- Índice para las búsquedas eficientes de ítems por su paquete.
-- Útil para recuperar el itinerario completo de un paquete.
CREATE INDEX idx_paquete_items ON Item(paquete_id);
```

#### VISTAS
```sql
-- Vista para el análisis de los paquetes más populares
-- Muestra el número de reservas y el total de viajeros para cada paquete,
-- ordenados de mayor a menor popularidad.
CREATE VIEW paquetes_populares AS
SELECT
    p.nombre AS nombre_paquete,
    COUNT(rp.id_reserva) AS num_reservas,
    SUM(r.num_personas) AS total_viajeros
FROM Paquete p
JOIN Reserva_paquete rp ON p.paquete_id = rp.id_paquete
JOIN Reserva r ON rp.id_reserva = r.id_reserva
GROUP BY p.nombre
ORDER BY num_reservas DESC;

-- Vista para el análisis de los mejores clientes por facturación
-- Muestra el gasto total de cada cliente, ordenado del que más ha gastado al que menos[cite: 43].
CREATE VIEW mejores_clientes_facturacion AS
SELECT
    c.nombre,
    c.apellidos,
    SUM(r.coste_reserva) AS facturacion_total
FROM Clientes c
JOIN Reserva r ON c.cliente_id = r.id_cliente
GROUP BY c.nombre, c.apellidos
ORDER BY facturacion_total DESC;

-- Vista para el análisis de paquetes no reservados
-- Muestra los paquetes que no tienen ninguna reserva asociada[cite: 40].
CREATE VIEW paquetes_no_reservados AS
SELECT
    p.nombre
FROM Paquete p
LEFT JOIN Reserva_paquete rp ON p.paquete_id = rp.id_paquete
WHERE rp.id_reserva IS NULL;

-- Vista para el análisis de popularidad de destinos por ciudad
-- Muestra el total de viajeros que han visitado una ciudad, combinando hoteles y atracciones.
CREATE VIEW popularidad_por_ciudad AS
SELECT
    ciudad,
    SUM(num_personas) AS total_viajeros
FROM (
    -- Subconsulta para hoteles
    SELECT
        h.ciudad,
        r.num_personas
    FROM Hotel h
    JOIN Item i ON h.id_item = i.id_item
    JOIN Reserva_paquete rp ON i.paquete_id = rp.id_paquete
    JOIN Reserva r ON rp.id_reserva = r.id_reserva
    GROUP BY h.ciudad, r.num_personas

    UNION ALL

    -- Subconsulta para atracciones
    SELECT
        a.ciudad,
        r.num_personas
    FROM Atracciones a
    JOIN Item i ON a.id_item = i.id_item
    JOIN Reserva_paquete rp ON i.paquete_id = rp.id_paquete
    JOIN Reserva r ON rp.id_reserva = r.id_reserva
    GROUP BY a.ciudad, r.num_personas
) AS ciudad_viajeros
GROUP BY ciudad
ORDER BY total_viajeros DESC;


```

### **Vistas e Índices**

La optimización del rendimiento es crucial para la gestión del negocio[cite: 45].

* **Índices**: Los índices se crean en las claves foráneas y en las columnas que se utilizan con frecuencia en las cláusulas `WHERE` y `JOIN`, como las fechas, los destinos y los identificadores de cliente. [Esto acelera las consultas para el análisis de ventas y el historial del cliente, lo que es vital para la gestión del negocio].
* **Vistas**: Se utilizan para simplificar las consultas de informes complejas. Al encapsular la lógica de las uniones y las agregaciones en una vista, el personal de la agencia puede obtener datos de los paquetes más populares o de los mejores clientes con consultas sencillas, sin tener que entender la estructura interna de las tablas.


## FASE 6. Definir triggers para actualizar el coste total de los paquetes.

### Funcion para el trigger

```sql
SET search_path TO travel;

CREATE OR REPLACE FUNCTION actualizar_coste_paquete()
RETURNS TRIGGER AS $$
DECLARE
    paquete_id_var INT;
BEGIN
    -- Determinar el ID del paquete afectado
    -- NEW se usa para INSERT/UPDATE, OLD para DELETE
    IF (TG_OP = 'DELETE') THEN
        paquete_id_var := OLD.paquete_id;
    ELSE
        paquete_id_var := NEW.paquete_id;
    END IF;

    -- Recalcular el coste total del paquete
    -- SUM() devolverá 0 si no hay ítems, lo que es lo correcto
    UPDATE Paquete
    SET coste_total = (
        SELECT COALESCE(SUM(coste), 0)
        FROM Item
        WHERE paquete_id = paquete_id_var
    )
    WHERE paquete_id = paquete_id_var;

    -- Devolver NEW para INSERT/UPDATE y OLD para DELETE
    IF (TG_OP = 'DELETE') THEN
        RETURN OLD;
    ELSE
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;
```

### TRIGGER 

```sql
CREATE TRIGGER coste_total
AFTER INSERT OR UPDATE OR DELETE ON travel.Item
FOR EACH ROW
EXECUTE FUNCTION actualizar_coste_paquete();
```

### 7. Crear procedimientos almacenados para crear o duplicar paquetes.


#### Procedimiento de crear paquete

```sql
CREATE OR REPLACE PROCEDURE crear_paquete(
    IN nombre_paquete_nuevo VARCHAR(255)
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO Paquete (nombre, coste_total)
    VALUES (nombre_paquete_nuevo, 0.00);

    RAISE NOTICE 'Paquete "%" creado con éxito.', nombre_paquete_nuevo;
END;
$$;
```
#### Procedimiento de duplicar paquete

```sql
SET search_path TO travel;

CREATE OR REPLACE PROCEDURE duplicar_paquete_por_nombre(
    IN nombre_paquete_origen VARCHAR(255),
    IN nuevo_nombre_paquete VARCHAR(255)
)

AS $$
DECLARE
    paquete_origen_id INT;
    nuevo_paquete_id_var INT;
    item_record RECORD;
    nuevo_item_id_var INT;
BEGIN
    -- 1. Buscar el ID del paquete de origen a partir de su nombre.
    SELECT paquete_id INTO paquete_origen_id
    FROM Paquete
    WHERE nombre = nombre_paquete_origen;

    -- 2. Verificar si el paquete existe.
    IF paquete_origen_id IS NULL THEN
        RAISE NOTICE 'Error: El paquete "%" no existe. No se puede duplicar.', nombre_paquete_origen;
        RETURN;
    END IF;

    -- 3. Insertar el nuevo paquete y obtener su ID.
    INSERT INTO Paquete (nombre, coste_total)
    SELECT nuevo_nombre_paquete, coste_total
    FROM Paquete
    WHERE paquete_id = paquete_origen_id
    RETURNING paquete_id INTO nuevo_paquete_id_var;

    -- 4. Recorrer todos los ítems del paquete original.
    FOR item_record IN
        SELECT *
        FROM Item
        WHERE paquete_id = paquete_origen_id
    LOOP
        -- 5. Insertar el ítem genérico en la nueva tabla 'Item' y obtener su ID.
        INSERT INTO Item (paquete_id, nombre, descripcion, coste)
        VALUES (nuevo_paquete_id_var, item_record.nombre, item_record.descripcion, item_record.coste)
        RETURNING id_item INTO nuevo_item_id_var;

        -- 6. Copiar los datos específicos del ítem (vuelo, hotel o atracción).
        -- Intentar insertar en Vuelo
        BEGIN
            INSERT INTO Vuelo (id_item, aerolinea, origen, destino, fecha_hora_salida)
            SELECT nuevo_item_id_var, aerolinea, origen, destino, fecha_hora_salida
            FROM Vuelo
            WHERE id_item = item_record.id_item;
        EXCEPTION WHEN foreign_key_violation THEN
            -- Ignora si no existe una relación
        END;

        -- Intentar insertar en Hotel
        BEGIN
            INSERT INTO Hotel (id_item, nombre, ciudad, num_noches, precio_noche)
            SELECT nuevo_item_id_var, nombre, ciudad, num_noches, precio_noche
            FROM Hotel
            WHERE id_item = item_record.id_item;
        EXCEPTION WHEN foreign_key_violation THEN
            -- Ignora si no existe una relación
        END;

        -- Intentar insertar en Atracciones
        BEGIN
            INSERT INTO Atracciones (id_item, nombre, ciudad, duracion_estimada_min)
            SELECT nuevo_item_id_var, nombre, ciudad, duracion_estimada_min
            FROM Atracciones
            WHERE id_item = item_record.id_item;
        EXCEPTION WHEN foreign_key_violation THEN
            -- Ignora si no existe una relación
        END;

    END LOOP;

    RAISE NOTICE 'Paquete "%" duplicado con éxito. Nuevo paquete ID: %', nombre_paquete_origen, nuevo_paquete_id_var;
END;
$$ LANGUAGE plpgsql;
```