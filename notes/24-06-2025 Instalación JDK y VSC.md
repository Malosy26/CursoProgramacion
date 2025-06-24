---
title: 24-06-2025 Instalación JDK y VSC
created: '2025-06-24T07:09:54.893Z'
modified: '2025-06-24T08:37:18.648Z'
---

# 24-06-2025 Instalación JDK y VSC



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





# 🖥️ Guía para instalar Visual Studio Code y configurarlo para Java

## 🔧 Paso 1: Instalar Visual Studio Code

1. Abre tu navegador y busca la página oficial de **[Visual Studio Code](https://code.visualstudio.com/)**.
2. Dirígete a la sección de **Descargas**.
3. Como estás usando **Windows**, descarga la versión para Windows.  
   > 🔹 Si tu computadora tiene un procesador ARM (menos común), selecciona la versión ARM.  
   > 🔹 En la mayoría de los casos, selecciona el instalador que dice **"System Installer"**.
4. Ejecuta el archivo descargado y sigue los pasos del instalador para completar la instalación.

---

## 🌍 Paso 2: Cambiar el idioma a español

1. Abre Visual Studio Code.
2. Ve al menú de **Extensiones** (puedes usar el atajo `Ctrl + Shift + X`).
3. En la barra de búsqueda escribe: `Spanish`.
4. Instala la extensión llamada **"Spanish Language Pack for Visual Studio Code"** publicada por **Microsoft**.
5. Una vez instalada, aparecerá una ventana preguntando si deseas reiniciar y cambiar el idioma.  
   Haz clic en **"Sí"** para aplicar el idioma español.

---

## ☕ Paso 3: Instalar el Extension Pack for Java

1. Nuevamente, en el menú de **Extensiones**, busca:  
   `Extension Pack for Java`.
2. Asegúrate de elegir el **paquete de Microsoft** (verifica que el autor sea "Microsoft").
3. Haz clic en **Instalar**.  
   Esto instalará automáticamente todas las herramientas esenciales para programar en Java:
   - Soporte para el lenguaje Java
   - Depurador de Java
   - Maven
   - Pruebas unitarias
   - IntelliCode (sugerencias inteligentes)

---

✅ ¡Listo! Ahora tienes Visual Studio Code configurado para trabajar cómodamente con Java y en español.







