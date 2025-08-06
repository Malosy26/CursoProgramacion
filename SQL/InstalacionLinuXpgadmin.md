# Guía de Instalación y Configuración de pgAdmin4 en Linux

Esta guía te ayudará a instalar y configurar pgAdmin4 en un sistema Linux basado en Ubuntu, como Linux Mint.

## Instalación de PostgreSQL

Primero, asegúrate de tener PostgreSQL instalado en tu sistema:

```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
```

Inicia el servicio PostgreSQL:

```bash
sudo service postgresql start
```

## Configuración del Repositorio de pgAdmin4

Agrega la clave pública del repositorio de pgAdmin y configura el repositorio:

```bash
# Instalar la clave pública para el repositorio:
curl -fsS https://www.pgadmin.org/static/packages_pgadmin_org.pub | sudo gpg --dearmor -o /usr/share/keyrings/packages-pgadmin-org.gpg

# Crear el archivo de configuración del repositorio usando "noble":
sudo sh -c 'echo "deb [signed-by=/usr/share/keyrings/packages-pgadmin-org.gpg] https://ftp.postgresql.org/pub/pgadmin/pgadmin4/apt/noble pgadmin4 main" > /etc/apt/sources.list.d/pgadmin4.list && apt update'
```

## Instalación de pgAdmin4

Instala pgAdmin4 para modo escritorio:

```bash
sudo apt install pgadmin4-desktop
```

## Configuración de la Contraseña de PostgreSQL

Si necesitas restablecer la contraseña del usuario `postgres`, sigue estos pasos:

1. Conéctate al servidor PostgreSQL:

```bash
sudo -u postgres psql
```

2. Cambia la contraseña del usuario `postgres`:

```sql
ALTER USER postgres PASSWORD 'nueva_contraseña';
```

## Configuración de pgAdmin4

1. Abre pgAdmin4 desde el menú de aplicaciones.
2. Haz clic en "Add New Server" en la sección "Quick Links".
3. Completa los detalles del servidor:
   - **Nombre**: Un nombre descriptivo para la conexión.
   - **Host name/address**: `localhost`
   - **Port**: `5432`
   - **Maintenance database**: `postgres`
   - **Username**: `postgres`
   - **Password**: La contraseña que configuraste para el usuario `postgres`.

## Uso de pgAdmin4

- **Escribir y Ejecutar Consultas SQL**: Usa el "Query Tool" para escribir y ejecutar consultas SQL.
- **Explorar la Base de Datos**: Navega por las bases de datos, esquemas y tablas desde el panel izquierdo.
- **Crear y Modificar Tablas**: Usa el menú contextual para crear y modificar tablas.

## Solución de Problemas

Si encuentras problemas de conexión o visualización, asegúrate de que el servicio PostgreSQL esté en ejecución y verifica la configuración de conexión en pgAdmin4.
