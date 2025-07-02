# 📘 Apuntes Generales y HTML

---

## 🚀 Atajos y Utilidades Generales

* **Emmet para HTML:** Para generar un bloque de código HTML básico rápidamente, escribe `!` y presiona `Tab` o `Enter`.
* **Comentarios Rápidos:** Usa `Ctrl + Shift + 7` para abrir un comentario en el editor.
* **Formatear Documento (VS Code):** Si las tabulaciones se desajustan, haz clic derecho y selecciona "Formato de documento" (o "Format Document").
* **Tabla de Contenidos en Markdown:** En VS Code, presiona `F1`, busca "Markdown" y selecciona "Markdown All in One: Create Table of Contents".
* **Validadores:**
    * HTML: [https://validator.w3.org/](https://validator.w3.org/)
    * CSS: [https://jigsaw.w3.org/css-validator/](https://jigsaw.w3.org/css-validator/)
* **Herramientas Útiles:**
    * **BuildWith:** Te permite ver con qué tecnologías está construida una página web.
    * **TinyURL:** Para acortar URLs.
    * **Image-Map.net:** Para trabajar con mapas de imágenes en HTML.
* **Recursos para Aprender:** FreeCodeCamp, W3Schools.
* **Ejercicio Recomendado:** Página 79 (referencia a un material externo).

---

## 🌐 HTML

### Atributos `id` y `class`

* Las **`id`** son **atributos únicos**; no puede haber dos elementos con el mismo `id` en un documento HTML.
* Las **`class`** son clases y **sí se pueden repetir**; varios elementos pueden compartir la misma `class`.

### Estructura y Etiquetas HTML de Contenido (con Jerarquía)

El documento HTML sigue una estructura jerárquica principal:

1.  **`<html>`**: El elemento raíz que contiene todo el documento.
    * **`<head>`**: Cabecera del documento, no visible en la página. Contiene metadatos, el título de la página, enlaces a hojas de estilo CSS, etc.
    * **`<body>`**: El cuerpo del documento, donde se muestra todo el contenido visible en la página.

Dentro del `<body>`, encontramos diversas etiquetas para estructurar el contenido:

#### Párrafos y Encabezados

* **`<p>`**: Para escribir bloques de texto corrido (párrafos).
* **`<h1>` a `<h6>`**: Títulos o encabezados de diferentes niveles. Se usan para jerarquizar el contenido en secciones. `<h1>` es el más importante y `<h6>` el menos.

#### Formato de Texto Semántico

* **`<strong>`**: Negrita semántica, resalta un texto como importante.
* **`<em>`**: Cursiva (énfasis), para destacar o enfatizar parte del texto.
* **`<ins>`**: Subrayado, indica contenido que ha sido insertado o añadido.
* **`<del>`**: Tachado, representa contenido que ha sido eliminado.
* **`<blockquote>`**: Cita de otro autor o fuente, usado para mostrar un texto citado.
    * **`<sup>`**: Superíndice (ej: x<sup>2</sup>).
    * **`<sub>`**: Subíndice (ej: H<sub>2</sub>O).
* **`<abbr>`**: Abreviatura o acrónimo. Muestra el significado completo al pasar el ratón por encima (ej: `<abbr title="Central Processing Unit">CPU</abbr>`).
* **`<span>`**: Fragmento de texto en línea. No tiene significado semántico, pero sirve para aplicar estilos o scripts a una parte del texto.
* **`<dfn>`**: Definición, marca la primera vez que se define un término importante.
* **`<br>`**: Salto de línea, corta el texto y continúa en la siguiente línea.
* **`<pre>`**: Texto preformateado. Muestra el texto exactamente como está escrito, incluyendo espacios y saltos de línea.
* **`<code>`**: Código semántico. Sirve para representar fragmentos de código fuente.

#### Enlaces

Los enlaces (`<a>`) permiten la navegación y pueden ser de dos tipos:

* **Enlaces Externos:** Apuntan a una URL completa que incluye protocolo, servidor y ruta.
* **Enlaces Internos:** Apuntan a una sección dentro del mismo documento HTML (ej: `<a href="#seccion">Ir a sección</a>`).

#### Listas

* **`<ul>`**: Lista sin ordenar (viñetas).
* **`<ol>`**: Lista ordenada (numerada).
    * **`<li>`**: Elemento de la lista, contenido dentro de `<ul>` u `<ol>`.

    * **Emmet para listas:**
        * `ul>li*3`: Crea una lista no ordenada con 3 elementos `<li>` vacíos.
        * `ul>li*3{Elemento $}`: Crea una lista con 3 elementos, numerándolos (Elemento 1, Elemento 2, Elemento 3).
        * `ul>li*3{Elemento 1.$}`: Crea una lista con 3 elementos, numerándolos con un punto (Elemento 1.1, Elemento 1.2, Elemento 1.3).
    * Las **listas anidadas** se crean insertando una nueva lista (`<ul>` u `<ol>`) dentro del contenido de un elemento `<li>` existente.

#### Imágenes

* **`<img>`**: Etiqueta para insertar imágenes.
    * **`src`**: La ruta de la imagen (puede ser interna o externa).
    * **`alt`**: **Texto alternativo obligatorio** para accesibilidad y validación.
    * **`width` y `height`**: Para especificar dimensiones. Es recomendable modificar solo uno para mantener la proporción de la imagen.

    * **Imágenes Internas:** Se accede a ellas mediante su ruta relativa o absoluta dentro del proyecto.
    * **Imágenes Externas:** Se accede a ellas mediante una URL completa.
    * **Mapas de Imágenes:** Permiten definir áreas clicables dentro de una imagen (`<map>` y `<area>`).

#### Tablas

* **`<table>`**: Define una tabla.
* **`<caption>`**: Título o descripción de la tabla.
* **`<tr>`**: Define una fila de la tabla.
    * **`<th>`**: Define una celda de encabezado.
    * **`<td>`**: Define una celda de datos.
* **`<thead>`**: Contiene las filas de encabezado de la tabla.
* **`<tbody>`**: Contiene las filas del cuerpo de la tabla.
* **`<tfoot>`**: Contiene las filas del pie de la tabla.

#### Formularios

Los formularios (`<form>`) son la única manera de obtener información del usuario.

* **`action`**: Atributo que especifica la URL (servidor) que procesará los datos del formulario.
* **`method`**: Define cómo se envían los datos.
    * **`GET`**: Envía datos en la URL (menos seguro, más rápido, útil para búsquedas).
    * **`POST`**: Envía datos en el cuerpo de la solicitud (más seguro, adecuado para logins o datos sensibles).
* **`<fieldset>`**: Crea un recuadro alrededor de un grupo de elementos de formulario.
* **`<label>`**: Asocia un texto descriptivo a un control de formulario.
* **`<input>`**: Campo de entrada.
    * **`type`**: Define el tipo de entrada (ej: `text`, `password`, `date`, `number`, `email`, `tel`, `submit`, `reset`).
    * **`required`**: Hace que el campo sea obligatorio.
    * **`placeholder`**: Texto de sugerencia en el campo.
    * **`value`**: Valor inicial del campo.
    * **`min`, `max`**: Valores mínimos y máximos para campos numéricos.
* **`<textarea>`**: Campo de entrada para texto multilinea.

#### SEO (Search Engine Optimization)

* **Ratio Contenido/Código:** Relación entre el número de caracteres del contenido visible y el número de caracteres del código HTML. Un buen ratio es importante para el SEO.

---

## 💻 Entornos de Desarrollo y Herramientas

### IDEs (Entornos de Desarrollo Integrados)

* **Para Markdown:** Notable
* **Para Código (general):** Visual Studio Code (VSC)
* **Oficial para Java:** Apache Netbeans

### Instalación de Java y Visual Studio Code

#### ☕ Instalar Java (JDK)

1.  **Descargar Java LTS:** Ir a [Oracle Java Downloads](https://www.oracle.com/es/java/technologies/downloads/) y descargar la última versión LTS (Long Term Support, ej., JDK 21).
2.  **Configurar Variables de Entorno (`Path`):**
    * Presiona `Win + R` y escribe `cmd`.
    * Escribe `SystemPropertiesAdvanced` y presiona `Enter`.
    * Ve a "Variables de entorno".
    * En "Variables del sistema", busca `Path` y añade la ruta a la carpeta `bin` de tu instalación de Java (ej: `C:\Program Files\Java\jdk-21\bin`).

#### 🧑‍💻 Instalar Visual Studio Code (VSC)

1.  **Descargar VSC:** Ir a [VS Code Download](https://code.visualstudio.com/download#) y descargar el "System Installer x64".
2.  **Instalar Extensiones Recomendadas:**
    * **Spanish (Extension Pack)**: Para el idioma.
    * **Extension Pack for Java (Microsoft)**: Para desarrollo Java.
    * **Markdown All in One**: Mejora el soporte para Markdown, incluyendo la generación de tablas de contenido (`F1` > `markdown all in one: create table of contents`).

### 🖥️ Comandos de Consola

#### 🪟 Windows (MS-DOS/Powershell)

* `cmd`: Iniciar la consola.
* `dir`: Ver el contenido del directorio actual.
* `cd [directorio]`: Cambiar de directorio.
* `cd..`: Subir un nivel en el directorio.
* `cls`: Limpiar la consola.

#### 🐧 Linux

* `clear`: Limpiar la consola.
* `sudo`: Obtener privilegios de superusuario para ejecutar comandos.
* `apt update`: Actualizar la lista de paquetes de los repositorios.
* `apt upgrade`: Actualizar las aplicaciones instaladas.
* `apt install [paquete]`: Instalar un paquete.
* `mkdir [directorio]`: Crear un nuevo directorio.
* `ls`: Listar el contenido del directorio actual.
* `pwd`: Mostrar la ruta del directorio actual.

---

## 🔧 GIT (Sistema de Control de Versiones)

### 📋 Listado de Comandos Principales

* `git init`: Iniciar un nuevo repositorio Git en el directorio actual.
* `git clone "url_repo"`: Clonar un repositorio remoto existente.
* `git status`: Mostrar el estado actual del repositorio (archivos modificados, preparados, etc.).
* `git add .`: Preparar todos los cambios para el próximo commit.
* `git add [ruta/archivo]`: Preparar un archivo específico para el próximo commit.
* `git commit -m "mensaje"`: Guardar los cambios preparados con un mensaje descriptivo.
* `git commit -a -m "mensaje"`: Añadir todos los archivos modificados y guardar los cambios en un solo paso.
* `git push`: Subir los cambios del repositorio local al repositorio remoto.
* `git pull`: Descargar los cambios del repositorio remoto y fusionarlos con el repositorio local.

* **Trabajo con Remotos:**
    * `git remote add [nombre] [url]`: Añadir un repositorio remoto.
    * `git remote -v`: Ver los repositorios remotos configurados.
* `git fetch`: Descargar los cambios del repositorio remoto sin fusionarlos automáticamente.
* `git merge`: Fusionar los cambios de una rama o de un `fetch` en la rama actual.

### 🌿 Ramas GIT

Las ramas permiten trabajar en diferentes versiones del código de forma independiente.

* `git branch "nombre_rama"`: Crear una nueva rama.
* `git checkout "nombre_rama"`: Cambiar a una rama existente.
* `git merge [rama_a_fusionar]`: Fusionar los cambios de una rama en la rama actual.

**Pasos para Fusionar Ramas:**

1.  `git checkout rama_destino`: Cambiarse a la rama donde se quieren integrar los cambios.
2.  `git merge rama_a_fusionar`: Fusionar la rama con los cambios deseados en la rama actual.
3.  `git commit -m "Rama Fusionada"`: Confirmar la fusión.

---

## 👥 Roles y Salarios en Proyectos de Desarrollo (Estimaciones Anuales)

### 🧑‍🎓 Roles Junior (< 2-4 años de experiencia)

* **Front-end Developer:** 25k - 30k €
* **Back-end Developer:** 20k - 25k €
* **FullStack Developer:** 28k - 32k €

### 🧑‍💼 Roles Senior (> 2-4 años de experiencia)

* **Front-end Developer:** 30k - 35k €
* **Back-end Developer:** 25k - 30k €
* **FullStack Developer:** 32k - 35k €

### Roles Especializados (Sueldos Adicionales sobre el Base)

* **🧠 Analista:** +5k €
* **🧑‍🏫 Consultor:** +5k - 8k €
* **🏗️ Arquitecto:** +10k - 15k €

---

## 📀 Virtualización

La virtualización permite ejecutar múltiples sistemas operativos en una sola máquina física.

* **Software de Virtualización:**
    * **Oracle VirtualBox (v7):** Software de virtualización popular y gratuito.
        * Instalación en Linux: `sudo apt install virtualbox` (versión 7.0.16 en repositorios comunes).
        * Descargas oficiales: [VirtualBox Linux Downloads](https://www.virtualbox.org/wiki/Linux_Downloads) (para versiones más recientes como 7.1.10).
    * **VMWare:** Otro software de virtualización.
* **Sistemas Operativos (Ejemplos):**
    * **Arranque Dual:** Configuración donde dos sistemas operativos (ej: Windows y Linux) coexisten en la misma máquina, con gestores de arranque independientes (Grub para Linux, MBR para Windows).
        * Windows versiones: 11 Pro/Home.
        * Distribuciones Linux: Mint Cinnamon (basada en Ubuntu, que a su vez se basa en Debian).
        * Núcleos: GNU/Linux (para Linux), Unix (núcleo base).
    * **Puppy Linux ISO:** Una distribución de Linux ligera.
        * Ejemplo de ISO: [BookwormPup64 10.0.10](https://distro.ibiblio.org/puppylinux/puppy-bookwormpup/BookwormPup64/10.0.10/).

---