Hemos puesto exclamacion y al darle a eme te hace un bloque de codigo automatico.
https://validator.w3.org/

Las id son atributos unicos no pueden tener el mismo nombre.
Los class son classes y si se pueden repetir es decir tener el mismo nombre.
Para poner comentarios rapido control+mayuscula+7 y se abre ya un comentario.

>Con el f1 saldra un buscador y ponemos markdown el primero create table of contens además tambien saldrán el resto de opciones de la extensión.

>Rellenar esto con los apuntes del repo del profesor.


# HTML
## Tabla de contenidos

- [HTML](#html)
  - [Tabla de contenidos](#tabla-de-contenidos)
- [Estructura y etiquetas HTML de contenido (con jerarquía)](#estructura-y-etiquetas-html-de-contenido-con-jerarquía)
    - [Enlaces](#enlaces)
      - [Enlaces externos](#enlaces-externos)
      - [Enlaces internos](#enlaces-internos)
    - [Ejemplo de uso:](#ejemplo-de-uso)


---

# Estructura y etiquetas HTML de contenido (con jerarquía)

- `<html>` (1)  
  Elemento raíz que contiene todo el documento HTML.

  - `<head>` (1)  
    Cabecera del documento (no visible en la página). Contiene metadatos, título, enlaces a CSS, etc.

  - `<body>` (1)  
    Cuerpo del documento (lo que se muestra en pantalla).

    - `<p>` (2) → Párrafo  
      Para escribir bloques de texto corrido.

    - `<h1>` a `<h6>` (2) → Títulos o encabezados de diferentes niveles  
      Se usan para jerarquizar el contenido en secciones.  
      (`<h1>` es el más importante y `<h6>` el menos).

      - `<strong>` (3) → Negrita semántica  
        Resalta un texto como importante.

      - `<em>` (3) → Cursiva (énfasis)  
        Para destacar o enfatizar parte del texto.

      - `<ins>` (3) → Subrayado (contenido añadido)  
        Muestra que algo fue **insertado** o añadido.

      - `<del>` (3) → Tachado (contenido eliminado)  
        Representa contenido que ha sido **eliminado**.

    - `<blockquote>` (2) → Cita de otro autor o fuente  
      Usado para mostrar un texto citado de otra fuente.

      - `<sup>` (3) → Superíndice  
        Texto elevado (como en potencias: x<sup>2</sup>).

      - `<sub>` (3) → Subíndice  
        Texto bajado (como en fórmulas químicas: H<sub>2</sub>O).

    - `<abbr>` (2) → Abreviatura o acrónimo  
      Muestra el significado completo al pasar el ratón por encima.  
      Ejemplo: `<abbr title="Central Processing Unit">CPU</abbr>`

    - `<span>` (3) → Fragmento de texto en línea  
      No tiene significado semántico, pero sirve para aplicar estilos o scripts.

    - `<dfn>` (3) → Definición  
      Marca la **primera vez** que defines un término importante.

    - `<br>` (3) → Salto de línea  
      Corta el texto y continúa en la siguiente línea (como un Enter).

    - `<pre>` (2) → Texto preformateado  
      Muestra texto exactamente como está escrito (incluyendo espacios y saltos).

    - `<code>` (2) → Código semántico  
      Sirve para representar fragmentos de código fuente.



### Enlaces
 #### Enlaces externos

  - LLamamos a una url una URL tiene:
    - Protocolo.
    - Servidor.
    - Ruta.
  
#### Enlaces internos


### Ejemplo de uso:
```html
<p>Superíndice y Subíndice:<br>
   Hm<sup>3</sup><br>
   H<sub>2</sub>O
</p>


wubuntu