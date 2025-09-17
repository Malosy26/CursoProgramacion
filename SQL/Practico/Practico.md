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

-- Crear el esquema
CREATE SCHEMA IF NOT EXISTS travel;

-- Tablas principales
CREATE TABLE Clientes (
    cliente_id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255),
    correo_elec VARCHAR(255) UNIQUE,
    telefono VARCHAR(20)
);

CREATE TABLE Paquete (
    paquete_id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    coste_total NUMERIC(10, 2) DEFAULT 0.00
);

-- La tabla Reserva ahora se relaciona directamente con Paquete (1:N)
CREATE TABLE Reserva (
    id_reserva SERIAL PRIMARY KEY,
    id_cliente INT NOT NULL REFERENCES Clientes(cliente_id) ON DELETE RESTRICT,
    id_paquete INT NOT NULL REFERENCES Paquete(paquete_id) ON DELETE RESTRICT,
    fecha_reserva DATE NOT NULL DEFAULT CURRENT_DATE,
    num_personas INT NOT NULL,
    coste_reserva NUMERIC(10, 2) NOT NULL,
    CONSTRAINT chk_personas CHECK (num_personas > 0)
);

-- La tabla Item es ahora independiente y no tiene FK a Paquete
CREATE TABLE Item (
    id_item SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    coste NUMERIC(10, 2) NOT NULL
);

-- La tabla intermedia para la relación M:M entre Paquete e Item
CREATE TABLE paquete_item (
    id_paquete INT NOT NULL REFERENCES Paquete(paquete_id) ON DELETE CASCADE,
    id_item INT NOT NULL REFERENCES Item(id_item) ON DELETE RESTRICT,
    PRIMARY KEY (id_paquete, id_item),
    orden INT,
    fecha DATE
);

-- Tablas de tipo de ítem con la clave foránea a la tabla 'Item' (relación 1:1)
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
```
---

### **📝 Comentarios de Diseño y Justificación Técnica del Modelo Normalizado Simplificado**

Este diseño de base de datos se basa en un modelo relacional normalizado y simplificado, lo que lo hace ideal para la enseñanza y su aplicación en cualquier sistema de gestión de bases de datos relacionales (RDBMS)[cite: 8]. La principal diferencia con respecto a modelos más complejos es la eliminación de tablas intermedias para las relaciones uno a uno (1:1), lo que resulta en un esquema más directo y fácil de mantener.

---

### **Claves Foráneas y Reglas de Eliminación (`ON DELETE`)**

El modelo establece reglas claras para mantener la integridad referencial y la coherencia de los datos.

* **Relación Clientes a Reserva (`1:M`)**: Se implementa con la clave foránea `id_cliente` en la tabla `Reserva`. La restricción `ON DELETE RESTRICT` en esta relación es fundamental para la protección de datos históricos. Esto evita que se elimine a un cliente que tiene reservas registradas, lo que salvaguarda el historial de ventas y la información de facturación.
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

-- **Índices para búsquedas de clientes y reservas**
-- Acelera la búsqueda del historial de un cliente[cite: 42, 45].
CREATE INDEX idx_cliente_reservas ON Reserva(id_cliente);

-- Optimiza la búsqueda de reservas por paquete.
CREATE INDEX idx_paquete_reservas ON Reserva(id_paquete);

-- Mejora las consultas por fecha[cite: 45], útil para análisis de temporada.
CREATE INDEX idx_fecha_reserva ON Reserva(fecha_reserva);

---

-- **Índices para la gestión de ítems y paquetes**
-- Acelera la unión entre Paquete e Item para obtener el itinerario[cite: 46].
CREATE INDEX idx_paquete_item ON paquete_item(id_paquete);
CREATE INDEX idx_item_paquete ON paquete_item(id_item);

-- Optimiza la búsqueda de ítems específicos.
CREATE INDEX idx_nombre_item ON Item(nombre);

---

-- **Índices para análisis geográfico**
-- Mejoran la velocidad de las consultas sobre destinos y ciudades[cite: 44, 45].
CREATE INDEX idx_destino_vuelo ON Vuelo(destino);
CREATE INDEX idx_ciudad_hotel ON Hotel(ciudad);
CREATE INDEX idx_ciudad_atraccion ON Atracciones(ciudad);
```

#### VISTAS
```sql
SET search_path TO travel;

-- Vista para el análisis de los paquetes más populares
-- Muestra el número de reservas y el total de viajeros para cada paquete.
CREATE OR REPLACE VIEW paquetes_populares AS
SELECT
    p.nombre AS nombre_paquete,
    COUNT(r.id_reserva) AS num_reservas,
    SUM(r.num_personas) AS total_viajeros
FROM Paquete p
JOIN Reserva r ON p.paquete_id = r.id_paquete
GROUP BY p.nombre
ORDER BY num_reservas DESC;

-- Vista para el análisis de los mejores clientes por facturación
-- Muestra el gasto total de cada cliente.
CREATE OR REPLACE VIEW mejores_clientes_facturacion AS
SELECT
    c.nombre,
    c.apellidos,
    SUM(r.coste_reserva) AS facturacion_total
FROM Clientes c
JOIN Reserva r ON c.cliente_id = r.id_cliente
GROUP BY c.nombre, c.apellidos
ORDER BY facturacion_total DESC;

-- Vista para el análisis de paquetes no reservados
-- Muestra los paquetes que no tienen ninguna reserva asociada.
CREATE OR REPLACE VIEW paquetes_no_reservados AS
SELECT
    p.nombre
FROM Paquete p
LEFT JOIN Reserva r ON p.paquete_id = r.id_paquete
WHERE r.id_reserva IS NULL;

-- Vista para el análisis de popularidad de destinos por ciudad
-- Muestra el total de viajeros que han visitado una ciudad, combinando hoteles y atracciones.
CREATE OR REPLACE VIEW popularidad_por_ciudad AS
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
    JOIN paquete_item pi ON i.id_item = pi.id_item
    JOIN Reserva r ON pi.id_paquete = r.id_paquete
    GROUP BY h.ciudad, r.num_personas

    UNION ALL

    -- Subconsulta para atracciones
    SELECT
        a.ciudad,
        r.num_personas
    FROM Atracciones a
    JOIN Item i ON a.id_item = i.id_item
    JOIN paquete_item pi ON i.id_item = pi.id_item
    JOIN Reserva r ON pi.id_paquete = r.id_paquete
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
    -- Determinar el ID del paquete afectado.
    -- OLD para operaciones de eliminación, NEW para inserciones o actualizaciones.
    IF (TG_OP = 'DELETE') THEN
        paquete_id_var := OLD.id_paquete;
    ELSE
        paquete_id_var := NEW.id_paquete;
    END IF;

    -- Recalcular el coste total del paquete sumando el coste de sus ítems asociados.
    UPDATE Paquete
    SET coste_total = (
        SELECT COALESCE(SUM(i.coste), 0)
        FROM Item i
        JOIN paquete_item pi ON i.id_item = pi.id_item
        WHERE pi.id_paquete = paquete_id_var
    )
    WHERE paquete_id = paquete_id_var;

    -- Devolver el registro apropiado según la operación.
    IF (TG_OP = 'DELETE') THEN
        RETURN OLD;
    ELSE
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;
```
#### Triger
```sql
CREATE TRIGGER coste_total
AFTER INSERT OR UPDATE OR DELETE ON travel.paquete_item
FOR EACH ROW
EXECUTE FUNCTION actualizar_coste_paquete();

```


### **Documentación de la Función `actualizar_coste_paquete()`**

Esta función PL/pgSQL está diseñada para ser llamada por un `TRIGGER`. Su propósito principal es mantener el valor de `coste_total` en la tabla `Paquete` siempre actualizado, reflejando la suma de los costes de todos los ítems asociados a dicho paquete.

---

#### **¿Cómo funciona?**

La función se activa automáticamente después de cualquier operación de inserción, actualización o eliminación en la tabla intermedia `paquete_item`. Su lógica se puede resumir en los siguientes pasos:

1.  **Identificación del Paquete Afectado**: La función determina el `paquete_id` del paquete que ha sido modificado. Para ello, utiliza la variable especial `TG_OP` (que indica la operación que activó el disparador) y las variables de registro `NEW` y `OLD`.
    * Si la operación es un `DELETE` (eliminación), se utiliza `OLD.id_paquete` para obtener el ID del paquete al que pertenecía el ítem eliminado.
    * Para `INSERT` (inserción) o `UPDATE` (actualización), se utiliza `NEW.id_paquete` para obtener el ID del paquete afectado.

2.  **Recálculo del Coste Total**: Se ejecuta una sentencia `UPDATE` en la tabla `Paquete` para el registro afectado. La clave de esta sentencia es una subconsulta que realiza los siguientes pasos:
    * Se unen las tablas `Item` y `paquete_item` para vincular los ítems con su paquete.
    * Se suma el `coste` de todos los ítems que pertenecen al `paquete_id` afectado.
    * Se utiliza la función `COALESCE` para manejar el caso en que un paquete no tenga ítems (por ejemplo, después de eliminar el último ítem). En lugar de devolver un valor `NULL`, `COALESCE` asegura que el resultado sea `0`, manteniendo la consistencia de los datos.

3.  **Devolución del Registro**: La función finaliza devolviendo el registro `NEW` o `OLD` a PostgreSQL, lo cual es un requisito de las funciones de `TRIGGER`. Esto permite que la operación de la base de datos que activó el disparador continúe sin interrupciones.

---

#### **Sintaxis Clave**

* `TG_OP`: Variable que indica la operación que activó el `TRIGGER` (`INSERT`, `UPDATE`, `DELETE`).
* `NEW`: Registro que contiene la fila de datos que acaba de ser insertada o actualizada.
* `OLD`: Registro que contiene la fila de datos antes de ser eliminada o actualizada.
* `COALESCE(SUM(i.coste), 0)`: Esta función garantiza que la suma de los costes no sea `NULL` si el paquete no tiene ítems, devolviendo en su lugar un valor de `0`.


### 7. Crear procedimientos almacenados para crear o duplicar paquetes.


#### Procedimiento de crear paquete

```sql

SET search_path TO travel;

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
LANGUAGE plpgsql
AS $$
DECLARE
    paquete_origen_id INT;
    nuevo_paquete_id_var INT;
BEGIN
    -- Obtener el ID del paquete original
    SELECT paquete_id INTO paquete_origen_id
    FROM Paquete
    WHERE nombre = nombre_paquete_origen;

    IF paquete_origen_id IS NULL THEN
        RAISE EXCEPTION 'Error: El paquete "%" no existe. No se puede duplicar.', nombre_paquete_origen;
    END IF;

    -- 1. Insertar el nuevo paquete
    INSERT INTO Paquete (nombre, coste_total)
    SELECT nuevo_nombre_paquete, coste_total
    FROM Paquete
    WHERE paquete_id = paquete_origen_id
    RETURNING paquete_id INTO nuevo_paquete_id_var;

    -- 2. Insertar los ítems asociados al nuevo paquete
    INSERT INTO paquete_item (id_paquete, id_item, orden, fecha)
    SELECT nuevo_paquete_id_var, id_item, orden, fecha
    FROM paquete_item
    WHERE id_paquete = paquete_origen_id;

EXCEPTION
    WHEN OTHERS THEN
        RAISE NOTICE 'Error al duplicar el paquete: %', SQLERRM;
        RAISE;
END;
$$;

```

###  Fase 8. Poblar las tablas con datos de ejemplo.

```sql
-- Insertar Clientes
INSERT INTO Clientes (nombre, apellidos, correo_elec, telefono) VALUES
('Juan', 'García', 'juan.garcia@example.com', '601234567'), ('María', 'López', 'maria.lopez@example.com', '602345678'),
('Carlos', 'Sánchez', 'carlos.sanchez@example.com', '603456789'), ('Ana', 'Díaz', 'ana.diaz@example.com', '604567890'),
('David', 'Pérez', 'david.perez@example.com', '605678901'), ('Sofía', 'Martínez', 'sofia.martinez@example.com', '606789012'),
('Javier', 'Rodríguez', 'javier.rodriguez@example.com', '607890123'), ('Laura', 'Gómez', 'laura.gomez@example.com', '608901234'),
('Pedro', 'Fernández', 'pedro.fernandez@example.com', '609012345'), ('Elena', 'Ruiz', 'elena.ruiz@example.com', '610123456'),
('Sergio', 'Hernández', 'sergio.hernandez@example.com', '611234567'), ('Paula', 'Jiménez', 'paula.jimenez@example.com', '612345678'),
('Fernando', 'Torres', 'fernando.torres@example.com', '613456789'), ('Isabel', 'Moreno', 'isabel.moreno@example.com', '614567890'),
('Daniel', 'Muñoz', 'daniel.munoz@example.com', '615678901'), ('Cristina', 'Alonso', 'cristina.alons@example.com', '616789012'),
('Ricardo', 'Navarro', 'ricardo.navarro@example.com', '617890123'), ('Marta', 'Gil', 'marta.gil@example.com', '618901234'),
('Andrés', 'Vega', 'andres.vega@example.com', '619012345'), ('Lucía', 'Ramos', 'lucia.ramos@example.com', '620123456');

-- Insertar Paquetes
INSERT INTO Paquete (nombre, coste_total) VALUES
('Aventura en la Amazonía', 0.00), ('Descanso en el Caribe', 0.00), ('Ruta histórica por Europa', 0.00),
('Safari en Kenia', 0.00), ('Escapada a Bali', 0.00), ('Tour por la Toscana', 0.00),
('Explorando los Alpes', 0.00), ('Navidad en Nueva York', 0.00), ('Verano en las Maldivas', 0.00),
('Recorriendo Japón', 0.00), ('Fin de semana en Londres', 0.00), ('Senderismo en los Andes', 0.00),
('Crucero por el Mediterráneo', 0.00), ('Luna de miel en Cancún', 0.00), ('Descubriendo la Patagonia', 0.00),
('Tour gastronómico por España', 0.00), ('Relax en Santorini', 0.00), ('Viaje a la Costa Azul', 0.00),
('Aventura en Tailandia', 0.00), ('Explorando Vietnam', 0.00), ('Ruta por Marruecos', 0.00),
('Clásico en Roma y Venecia', 0.00), ('Fin de año en Praga', 0.00), ('Vacaciones en Hawái', 0.00),
('Paseo por París', 0.00), ('Cultura en El Cairo', 0.00), ('Naturaleza en Islandia', 0.00),
('Aventura en Costa Rica', 0.00), ('Recorrido por la Gran Muralla', 0.00), ('Tour por la India', 0.00),
('Descanso en Jamaica', 0.00), ('Esquí en los Pirineos', 0.00), ('Aventura en Perú', 0.00),
('Viaje a la Antártida', 0.00), ('Tour por Australia', 0.00), ('Escapada a Dubái', 0.00),
('Vacaciones en Riviera Maya', 0.00), ('Recorriendo Argentina', 0.00), ('Aventura en la selva', 0.00),
('Fin de semana en Florencia', 0.00);

-- Insertar Items y sus detalles
DO $$
DECLARE new_item_id INT; BEGIN
    INSERT INTO Item (nombre, descripcion, coste) VALUES ('Vuelo a Tokio', 'Vuelo de Madrid a Tokio.', 850.00) RETURNING id_item INTO new_item_id;
    INSERT INTO Vuelo (id_item, aerolinea, origen, destino, fecha_hora_salida) VALUES (new_item_id, 'Japan Airlines', 'Madrid', 'Tokio', '2025-01-10 10:00:00');
    INSERT INTO Item (nombre, descripcion, coste) VALUES ('Vuelo a Roma', 'Vuelo de Madrid a Roma.', 150.00) RETURNING id_item INTO new_item_id;
    INSERT INTO Vuelo (id_item, aerolinea, origen, destino, fecha_hora_salida) VALUES (new_item_id, 'Iberia', 'Madrid', 'Roma', '2025-02-15 12:00:00');
    INSERT INTO Item (nombre, descripcion, coste) VALUES ('Hotel en Roma', 'Estancia de 3 noches en Roma.', 300.00) RETURNING id_item INTO new_item_id;
    INSERT INTO Hotel (id_item, nombre, ciudad, num_noches, precio_noche) VALUES (new_item_id, 'Hotel Coliseo', 'Roma', 3, 100.00);
    INSERT INTO Item (nombre, descripcion, coste) VALUES ('Tour por el Coliseo', 'Visita guiada al Coliseo de Roma.', 50.00) RETURNING id_item INTO new_item_id;
    INSERT INTO Atracciones (id_item, nombre, ciudad, duracion_estimada_min) VALUES (new_item_id, 'Coliseo', 'Roma', 120);
    FOR i IN 12..80 LOOP
        INSERT INTO Item (nombre, descripcion, coste) VALUES ('Item ' || i, 'Descripción genérica para el item ' || i, (i * 10.5)) RETURNING id_item INTO new_item_id;
        IF i % 3 = 0 THEN
            INSERT INTO Vuelo (id_item, aerolinea, origen, destino, fecha_hora_salida) VALUES (new_item_id, 'Aerolínea ' || i, 'Origen ' || i, 'Destino ' || i, '2025-05-01 10:00:00'::timestamp + (i || ' days')::interval);
        ELSIF i % 3 = 1 THEN
            INSERT INTO Hotel (id_item, nombre, ciudad, num_noches, precio_noche) VALUES (new_item_id, 'Hotel ' || i, 'Ciudad ' || i, 3, 50.00);
        ELSE
            INSERT INTO Atracciones (id_item, nombre, ciudad, duracion_estimada_min) VALUES (new_item_id, 'Atracción ' || i, 'Ciudad ' || i, 120);
        END IF;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

-- Relacionar Paquetes e Items en la tabla 'paquete_item'
INSERT INTO paquete_item (id_paquete, id_item, orden, fecha) VALUES
(1, 1, 1, '2025-01-10'), (1, 2, 2, '2025-01-15'), (2, 3, 1, '2025-02-15'), (2, 4, 2, '2025-02-20'),
(3, 5, 1, '2025-03-20'), (3, 6, 2, '2025-03-25'), (3, 7, 3, '2025-03-28');

-- Insertar más relaciones aleatorias sin duplicados
INSERT INTO paquete_item (id_paquete, id_item, orden, fecha)
SELECT
    p.paquete_id,
    i.id_item,
    1,
    '2025-06-01'::date + (p.paquete_id || ' days')::interval
FROM Paquete p, Item i
WHERE NOT EXISTS (
    SELECT 1 FROM paquete_item pi
    WHERE pi.id_paquete = p.paquete_id AND pi.id_item = i.id_item
)
ORDER BY RANDOM()
LIMIT 100;

-- Insertar Reservas con IDs de clientes y paquetes válidos
INSERT INTO Reserva (id_cliente, id_paquete, num_personas, coste_reserva) VALUES
(1, 1, 2, 1200.00), (2, 3, 1, 500.00), (3, 5, 3, 1500.00), (4, 1, 4, 2400.00),
(5, 2, 2, 1000.00), (6, 4, 1, 800.00), (7, 6, 2, 750.00), (8, 7, 3, 900.00);

-- Insertar más reservas de forma aleatoria para generar datos para las vistas
INSERT INTO Reserva (id_cliente, id_paquete, num_personas, coste_reserva)
SELECT
    floor(random() * 20 + 1)::int,
    floor(random() * 40 + 1)::int,
    floor(random() * 4 + 1)::int,
    (random() * 2000 + 500)::numeric(10, 2)
FROM generate_series(1, 50);

```
### FASE 9  Ejecutar consultas avanzadas y subconsultas que respondan a todos los casos de uso

#### Consulta de costes totales por tipos de item

```sql
SELECT
    tipo_item,
    SUM(total_ingresos) AS ingresos_totales
FROM (
    SELECT
        CASE
            WHEN v.id_item IS NOT NULL THEN 'Vuelo'
            WHEN h.id_item IS NOT NULL THEN 'Hotel'
            WHEN a.id_item IS NOT NULL THEN 'Atracción'
            ELSE 'Otro'
        END AS tipo_item,
        (r.num_personas * i.coste) AS total_ingresos
    FROM
        Reserva r
    JOIN
        paquete_item pi ON r.id_paquete = pi.id_paquete
    JOIN
        Item i ON pi.id_item = i.id_item
    LEFT JOIN Vuelo v ON i.id_item = v.id_item
    LEFT JOIN Hotel h ON i.id_item = h.id_item
    LEFT JOIN Atracciones a ON i.id_item = a.id_item
) AS ingresos_por_tipo
GROUP BY
    tipo_item
ORDER BY
    ingresos_totales DESC;
```

#### Paquetes populares(Usada la vista creada anteriormente)

```sql
SELECT * FROM paquetes_populares;
```

##### Mejores clientes por facturacion(Vista creada anteroirmente)

```sql
SELECT * FROM mejores_clientes_facturacion;
```

#### Historial completo de reserva de un cliente especifico
```sql
SELECT
    c.nombre AS nombre_cliente,
    c.apellidos AS apellidos_cliente,
    r.fecha_reserva,
    p.nombre AS nombre_paquete,
    r.num_personas,
    r.coste_reserva
FROM
    Clientes c
JOIN
    Reserva r ON c.cliente_id = r.id_cliente
JOIN
    Paquete p ON r.id_paquete = p.paquete_id
WHERE
    c.correo_elec = 'juan.garcia@example.com' -- Cambia el correo para buscar a otro cliente
ORDER BY
    r.fecha_reserva DESC;
```

#### Paquetes que no se han reservado nunca

```sql
SELECT * FROM paquetes_no_reservados;
```

#### Paquetes mas vendidos en una temporada especifica

```sql
SELECT
    p.nombre AS nombre_paquete,
    SUM(r.num_personas) AS total_viajeros_verano
FROM
    Reserva r
JOIN
    Paquete p ON r.id_paquete = p.paquete_id
WHERE
    r.fecha_reserva BETWEEN '2025-06-01' AND '2025-09-30'
GROUP BY
    p.nombre
ORDER BY
    total_viajeros_verano DESC;
```

#### Detalles de un paquete especifico con todos sus items

```sql
SELECT
    p.nombre AS nombre_paquete,
    pi.orden,
    pi.fecha,
    i.nombre AS nombre_item,
    i.coste,
    CASE
        WHEN v.id_item IS NOT NULL THEN 'Vuelo'
        WHEN h.id_item IS NOT NULL THEN 'Hotel'
        WHEN a.id_item IS NOT NULL THEN 'Atracción'
        ELSE 'Desconocido'
    END AS tipo_item
FROM
    Paquete p
JOIN
    paquete_item pi ON p.paquete_id = pi.id_paquete
JOIN
    Item i ON pi.id_item = i.id_item
LEFT JOIN
    Vuelo v ON i.id_item = v.id_item
LEFT JOIN
    Hotel h ON i.id_item = h.id_item
LEFT JOIN
    Atracciones a ON i.id_item = a.id_item
WHERE
    p.nombre = 'Ruta histórica por Europa' -- Cambia el nombre del paquete para buscar otro
ORDER BY
    pi.orden;
```

#### Probar procedimiento(duplicar) y consulta para ver que lo realiza

```sql
SET search_path TO travel;

CALL duplicar_paquete_por_nombre('Ruta histórica por Europa', 'Ruta histórica por Europa (copia)');

SELECT
    p.nombre AS nombre_paquete,
    p.coste_total,
    string_agg(i.nombre || ' (' || i.coste || '€)', ', ' ORDER BY pi.orden) AS items_del_paquete
FROM
    Paquete p
JOIN
    paquete_item pi ON p.paquete_id = pi.id_paquete
JOIN
    Item i ON pi.id_item = i.id_item
WHERE
    p.nombre = 'Ruta histórica por Europa'
    OR p.nombre = 'Ruta histórica por Europa (copia)'
GROUP BY
    p.paquete_id, p.nombre, p.coste_total
ORDER BY
    p.nombre;
```


#### FASE 10 Documentar el diseño y justificar cada decisión técnica tomada


### **Documento de Diseño y Justificación Técnica**

El diseño de esta base de datos se ha desarrollado con una estructura relacional optimizada para la gestión de una agencia de viajes, enfocándose en la **normalización** para garantizar la integridad y la eficiencia.

#### **1. Estructura de la Base de Datos**

* **Modelo Normalizado**: La base de datos sigue un modelo en **tercera forma normal (3NF)**. Esto significa que cada tabla tiene una función única y los datos no están duplicados. Por ejemplo, la información de un cliente se almacena solo en la tabla `Clientes` y se referencia desde la tabla `Reserva` a través de una clave foránea. Esta estructura previene anomalías de inserción, actualización y borrado.
* **Claves Primarias y Foráneas**: Las claves primarias (`SERIAL`) se utilizan para garantizar la unicidad de cada registro, mientras que las claves foráneas establecen relaciones lógicas entre las tablas. Por ejemplo, `id_cliente` en `Reserva` se relaciona con `cliente_id` en `Clientes`, asegurando que cada reserva esté vinculada a un cliente válido.
* **Herencia de Tablas**: Se implementó una jerarquía para la tabla `Item` (`Vuelo`, `Hotel`, `Atracciones`). En lugar de tener una sola tabla con muchas columnas opcionales, se optó por un diseño donde cada tipo de ítem tiene su propia tabla que se relaciona con `Item` a través de una clave primaria compartida. Esto simplifica el modelo, hace las consultas más claras y permite una fácil adición de nuevos tipos de ítems en el futuro.

#### **2. Rendimiento y Automatización**

* **Índices**: Se crearon índices en columnas clave como las que se usan en las cláusulas `JOIN` y `WHERE` (`id_cliente`, `id_paquete`, etc.). Esto acelera drásticamente la búsqueda y el filtrado de datos, lo cual es crucial para generar informes rápidamente en bases de datos con muchos registros.
* **Vistas**: Las vistas (`paquetes_populares`, `mejores_clientes_facturacion`) se diseñaron para simplificar consultas complejas y repetitivas. Almacenan la lógica de consulta y permiten que los usuarios finales o las aplicaciones obtengan informes predefinidos con una simple sentencia `SELECT`, sin necesidad de entender las uniones (`JOIN`) internas.
* **Triggers y Procedimientos**:
    * **Trigger `actualizar_coste_paquete`**: Este trigger se ejecuta automáticamente cada vez que se modifica la tabla `paquete_item`, asegurando que el `coste_total` en la tabla `Paquete` se mantenga siempre actualizado. Esta automatización elimina el riesgo de errores humanos y garantiza la consistencia de los datos.
    * **Procedimientos**: Los procedimientos almacenados (`crear_paquete`, `duplicar_paquete_por_nombre`) encapsulan lógica de negocio compleja, lo que permite ejecutar operaciones comunes de manera segura y eficiente con una sola llamada, reduciendo la posibilidad de errores manuales.

#### **3. Manejo de Datos de Prueba**

Para asegurar que la base de datos es robusta, se generó una cantidad significativa de datos de prueba. Se corrigió la generación de números aleatorios para evitar violaciones de claves foráneas, garantizando que los `id` generados siempre correspondan a registros existentes en las tablas relacionadas. Esto confirma que las restricciones de la base de datos funcionan según lo previsto y que el diseño es sólido para un uso en producción.
