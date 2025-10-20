# Ejercicio CRUD 

## Estructura del proyecto

```
src/
├── main/
│   ├── java/
│   │   ├── config/
│   │   │   └── DatabaseConfig.java
│   │   ├── model/
│   │   │   └── Persona.java
│   │   ├── controller/
│   │   │   └── PersonaController.java
│   │   ├── view/
│   │   │   └── Menu.java
│   │   └── Main.java
│   └── resources/
│       └── config.properties



```
## 📄 Archivos y su Propósito

### 1. **`config/` - Capa de Configuración**
- **`DatabaseConfig.java`**:
  - **Propósito**: Implementa el patrón **Singleton** para gestionar una única conexión a la base de datos PostgreSQL.
  - **¿Por qué?**:
    - Evita crear múltiples conexiones, optimizando recursos.
    - Carga la configuración desde `config.properties` (URL, usuario, contraseña).
    - Proporciona un método estático (`getInstance()`) para acceder a la conexión en cualquier parte del proyecto.

---

### 2. **`model/` - Capa de Modelo**
- **`Persona.java`**:
  - **Propósito**: Representa la entidad `Persona` y mapea la tabla `personas` de la base de datos.
  - **¿Por qué?**:
    - Centraliza la lógica de datos (atributos como `id`, `nombre`, `edad`).
    - Usa **Lombok** (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) para reducir código repetitivo (getters, setters, `toString`, etc.).
    - Facilita la manipulación de objetos en el controlador y la vista.

---

### 3. **`controller/` - Capa de Control**
- **`PersonaController.java`**:
  - **Propósito**: Gestiona las operaciones **CRUD** (Insert, Select) con la base de datos.
  - **¿Por qué?**:
    - Separa la lógica de negocio de la vista y el modelo.
    - Usa la conexión proporcionada por `DatabaseConfig` para ejecutar consultas SQL.
    - Devuelve listas de objetos `Persona` o realiza inserciones, abstractando la complejidad de la base de datos.

---

### 4. **`view/` - Capa de Vista**
- **`Menu.java`**:
  - **Propósito**: Muestra el menú en consola y gestiona la interacción con el usuario.
  - **¿Por qué?**:
    - Centraliza la entrada/salida de datos (I/O).
    - Delegar las operaciones a `PersonaController` (ej: insertar, listar).
    - Proporciona una interfaz sencilla para el usuario final.

---

### 5. **`Main.java` - Punto de Entrada**
- **Propósito**: Inicia la aplicación llamando al menú principal.
- **¿Por qué?**:
  - Es el punto de entrada estándar en aplicaciones Java.
  - Simplifica la ejecución y prueba del proyecto.

---

### 6. **`resources/config.properties` - Archivo de Configuración**
- **Propósito**: Almacena los parámetros de conexión a la base de datos (URL, usuario, contraseña).
- **¿Por qué?**:
  - Externaliza la configuración, facilitando cambios sin recompilar el código.
  - Mejora la seguridad al no hardcodear credenciales en el código fuente.

---

## 🔄 Flujo de Datos
1. **Usuario** interactúa con el `Menu` (vista).
2. **Menu** delega operaciones al `PersonaController` (controlador).
3. **PersonaController** usa `DatabaseConfig` (singleton) para acceder a la base de datos.
4. **DatabaseConfig** lee `config.properties` y gestiona la conexión.
5. **Persona** (modelo) representa los datos devueltos por la base de datos.

---

## ✨ Beneficios de esta Estructura
- **Separación de responsabilidades**: Cada capa tiene un rol claro.
- **Mantenibilidad**: Cambios en una capa afectan mínimamente a las demás.
- **Escalabilidad**: Fácil de extender (ej: añadir más modelos o vistas).
- **Reutilización**: El singleton `DatabaseConfig` puede usarse en otros proyectos.
- **Seguridad**: Credenciales externalizadas en `config.properties`.

## 📌 ¿Qué es el Patrón Singleton?

El patrón **Singleton** es un **patrón de diseño creacional** que garantiza que una clase tenga **una única instancia** en todo el sistema y proporciona un **punto de acceso global** a dicha instancia. Esto es útil cuando se necesita exactamente un objeto para coordinar acciones en todo el sistema, como la gestión de conexiones a bases de datos, logs, configuraciones, etc.

---

## 🔧 Implementación Básica en Java

### 1. **Singleton Básico (No Thread-Safe)**
```java
public class Singleton {
    private static Singleton instance;

    // Constructor privado para evitar instanciación externa
    private Singleton() {}

    // Método estático para obtener la instancia única
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

> Al crear el proyecto por defecto te hace una clase con el nombre podemos borrarla ya que con la estructura que hemos hecho segun las instrucciones ya tenemos un archivo main.





## 🧾 Configuración del archivo `config.properties`

Este archivo se utiliza para **externalizar la configuración de conexión a la base de datos**.  
De esta forma, si cambias la URL, usuario o contraseña de tu base de datos, **no es necesario recompilar** el código Java.

### 📍 Ubicación del archivo
Guárdalo dentro de:
    src/main/resources/config.properties

### 🧪 Ejemplo de contenido de `config.properties`
    db.url=jdbc:postgresql://localhost:5432/mi_basedatos
    db.user=postgres
    db.password=1234

### 📌 Explicación de cada parámetro
- `db.url` → La URL de conexión JDBC.  
  Formato: `jdbc:postgresql://<host>:<puerto>/<nombre_base_datos>`  
  Ejemplo: `jdbc:postgresql://localhost:5432/mi_basedatos`

- `db.user` → Nombre de usuario de PostgreSQL.  
  Por defecto suele ser `postgres` si usas la instalación estándar.

- `db.password` → Contraseña del usuario de PostgreSQL.

---

## 🧭 Cómo encontrar la información necesaria

1. **Nombre de la base de datos (`mi_basedatos`)**
   - Abre tu terminal de PostgreSQL:
        psql -U postgres
   - Lista las bases de datos disponibles:
        \l
   - Si no existe, créala:
        CREATE DATABASE mi_basedatos;

2. **Usuario y contraseña**
   - El usuario por defecto es `postgres`.  
   - Si quieres crear otro usuario:
        CREATE USER mi_usuario WITH PASSWORD 'mi_contraseña';
        GRANT ALL PRIVILEGES ON DATABASE mi_basedatos TO mi_usuario;

3. **Puerto de conexión**
   - Por defecto PostgreSQL usa el puerto `5432`.  
   - Puedes verificarlo en el archivo de configuración (ruta depende del sistema):
     - Windows: `C:\Program Files\PostgreSQL\<versión>\data\postgresql.conf`
     - Linux/Mac: `/etc/postgresql/<versión>/main/postgresql.conf`
   - O desde psql:
        SHOW port;

4. **Probar la conexión** (opcional pero recomendado)
   - Desde la terminal:
        psql -h localhost -p 5432 -U postgres -d mi_basedatos
   - Si la conexión es exitosa, tu `config.properties` está bien configurado ✅

---

## ⚠️ Recomendaciones de seguridad

- **No subas** el archivo `config.properties` a repositorios públicos (por ejemplo GitHub).  
- Añade `config.properties` a tu `.gitignore` si usas Git.  
- Para entornos productivos, considera usar variables de entorno o un gestor de secretos en lugar de credenciales en texto plano.

---

## ✅ Resumen rápido para copiar/pegar

1. Crea el archivo:
    src/main/resources/config.properties

2. Pega este contenido (ajusta valores según tu entorno):
    db.url=jdbc:postgresql://localhost:5432/mi_basedatos
    db.user=postgres
    db.password=1234

3. Prueba la conexión con:
    psql -h localhost -p 5432 -U postgres -d mi_basedatos



proyecto propiedades click derecho run y poner el main


```bash
dvr@ ~ $ sudo -i -u postgres
postgres@dvr-W65-W67RB:~$ psql
psql (18.0 (Ubuntu 18.0-1.pgdg24.04+3))
Digite «help» para obtener ayuda.

postgres=# ALTER USER postgres WITH PASSWORD '1234';
ALTER ROLE
postgres=# \q
postgres@dvr-W65-W67RB:~$ 
```


```bash
dvr@ ~ $ psql -U postgres -W -h localhost
Contraseña: 
psql (18.0 (Ubuntu 18.0-1.pgdg24.04+3))
Conexión SSL (protocolo: TLSv1.3, cifrado: TLS_AES_256_GCM_SHA384, compresión: desactivado, ALPN: postgresql)
Digite «help» para obtener ayuda.

postgres=# ^C
postgres=# CREATE DATABASE academia;
CREATE DATABASE
postgres=# \c academia
Contraseña: 
Conexión SSL (protocolo: TLSv1.3, cifrado: TLS_AES_256_GCM_SHA384, compresión: desactivado, ALPN: postgresql)
Ahora está conectado a la base de datos «academia» con el usuario «postgres».
academia=# CREATE TABLE cursos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL
);
CREATE TABLE
academia=# CREATE TABLE alumnos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    curso_id INT REFERENCES cursos(id)
);
CREATE TABLE
academia=# INSERT INTO cursos (nombre, fecha_inicio, fecha_fin)
VALUES
('Programación Java', '2025-10-01', '2026-01-15'),
('Bases de Datos PostgreSQL', '2025-11-10', '2026-02-28'),
('Desarrollo Web con Spring Boot', '2026-01-20', '2026-04-30');
INSERT 0 3
academia=# SELECT * FROM cursos;
 id |             nombre             | fecha_inicio | fecha_fin  
----+--------------------------------+--------------+------------
  1 | Programación Java              | 2025-10-01   | 2026-01-15
  2 | Bases de Datos PostgreSQL      | 2025-11-10   | 2026-02-28
  3 | Desarrollo Web con Spring Boot | 2026-01-20   | 2026-04-30
(3 filas)

academia=# \dt
          Listado de tablas
 Esquema | Nombre  | Tipo  |  Dueño   
---------+---------+-------+----------
 public  | alumnos | tabla | postgres
 public  | cursos  | tabla | postgres
(2 filas)

academia=# 



```