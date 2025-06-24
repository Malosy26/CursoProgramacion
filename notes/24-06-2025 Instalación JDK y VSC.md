---
title: 24-06-2025 Instalación JDK y VSC
created: '2025-06-24T07:09:54.893Z'
modified: '2025-06-24T08:37:18.648Z'
---

# 24-06-2025 Instalación JDK y VSC

## Instalar Java y VSC

# ☕ Instalación de Java en Windows 11

Este documento describe detalladamente cómo instalar Java Development Kit (JDK) en Windows 11, configurarlo en las variables de entorno del sistema y verificar que está funcionando correctamente.



## Descarga del JDK

La versión más reciente del JDK puede descargarse desde el sitio web oficial de Oracle. Para ello, sigue estos pasos:

1. Abre tu navegador web y accede a:

   [https://www.oracle.com/java/technologies/javase-downloads.html](https://www.oracle.com/java/technologies/javase-downloads.html)

2. En la sección **Java SE**, haz clic en el botón **JDK Download** correspondiente a la última versión LTS (por ejemplo, Java 21 o superior).

3. Desplázate hacia abajo hasta la sección de descargas disponibles para diferentes sistemas operativos.

4. Ubica la opción **Windows x64 Installer** (archivo con extensión `.exe`) y haz clic en el botón de descarga.

5. Acepta los términos de licencia si se te solicita y guarda el instalador en tu equipo.

---

## Instalación del JDK

Una vez descargado el instalador:

1. Haz doble clic sobre el archivo `.exe` para iniciar el asistente de instalación.

2. En la ventana del instalador:
   - Acepta el acuerdo de licencia.
   - Elige la carpeta de instalación (por defecto será algo como `C:\Program Files\Java\jdk-XX`, donde `XX` es la versión correspondiente).

3. Haz clic en **Next** y espera a que finalice la instalación.

4. Cuando el proceso termine, haz clic en **Close** para cerrar el instalador.

---

## Configuración de variables de entorno

Después de instalar el JDK, es necesario configurar las variables de entorno para que el sistema reconozca los comandos `java` y `javac` desde cualquier terminal.

1. Abre el menú Inicio y busca:  
   **"Editar las variables de entorno del sistema"**  
   y haz clic en la opción que aparece.

2. En la ventana **Propiedades del sistema**, haz clic en el botón **Variables de entorno…**

3. En la sección **Variables del sistema**, realiza lo siguiente:

   - Haz clic en **Nueva…** y completa los campos de esta manera:
     - **Nombre de la variable:** `JAVA_HOME`
     - **Valor de la variable:** Ruta de instalación del JDK, por ejemplo:
       ```
       C:\Program Files\Java\jdk-21
       ```

   - Luego, selecciona la variable llamada `Path` y haz clic en **Editar…**

   - En la ventana de edición, haz clic en **Nuevo** e ingresa:
     ```
     %JAVA_HOME%\bin
     ```

4. Confirma los cambios en todas las ventanas abiertas con **Aceptar**.

---

## Verificación de la instalación

Para confirmar que Java está correctamente instalado y configurado:

1. Abre una nueva ventana del **Símbolo del sistema** (`cmd`).

2. Escribe el siguiente comando y presiona Enter:
   ```bash
   java -version




- Instalar VsCode

1. Buscamos la pagina oficial de VsCode y vamos  a descargas en este caso al ser windows descargamos la version para windows(la version arm es para procesadores arm) el instalador que usaremos sera system installer.

2. En extensiones buscar spanish y elegir la extension de español por microsoft. Te preguntara si quieres reiniciar el programa y cambiar el lenguaje le damos a que si.


3. Instalar el extension pack for java buscandolo en las extensiones, el de microsoft.






