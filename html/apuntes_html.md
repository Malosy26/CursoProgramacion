Hemos puesto exclamacion y al darle a eme te hace un bloque de codigo automatico html.
https://validator.w3.org/

Las id son atributos unicos no pueden tener el mismo nombre.
Los class son classes y si se pueden repetir es decir tener el mismo nombre.
Para poner comentarios rapido control+mayuscula+7 y se abre ya un comentario.

>Remember : para abrir directamente el bloque de html con emmet es con ! no con <!

>Con el f1 saldra un buscador y ponemos markdown el primero create table of contens además tambien saldrán el resto de opciones de la extensión.

>Rellenar esto con los apuntes del repo del profesor.

>Pagina para aprender freecodecamp w3school

>Hacer ejercicio pagina 79



# HTML
## Tabla de contenidos

- [HTML](#html)
  - [Tabla de contenidos](#tabla-de-contenidos)
- [Estructura y etiquetas HTML de contenido (con jerarquía)](#estructura-y-etiquetas-html-de-contenido-con-jerarquía)
    - [Enlaces](#enlaces)
      - [Enlaces externos](#enlaces-externos)
      - [Enlaces internos](#enlaces-internos)
    - [Ejemplo de uso:](#ejemplo-de-uso)
    - [Listas](#listas)
    - [Imagenes](#imagenes)
      - [Imagenes internas](#imagenes-internas)
      - [Imagenes externas](#imagenes-externas)
      - [Mapa de imagenes](#mapa-de-imagenes)
    - [Tablas](#tablas)
    - [Formularios](#formularios)
    - [Etiquetas semanticas](#etiquetas-semanticas)


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
```

wubuntu

### Listas
- `u1>` (2) → Lista sin ordenar  
- `o1>` (2) → Lista ordenadas
  - `<li>` (3) → elemento de la lista 
  - -> ul>li*3 hacer una lista con 3 elementos vacios 
  - -> ul>li*3{Elemento $} -> haz una lista con 3 elementos con numero elemento 1 a 3
  - -> ul>li*3{Elemento 1.$} -> haz una lista con 3 elementos con numero 1.1 a 1l3
  
  Listas anidadas en el ejemplo de las listas.html
                  
>Inciso: si se descuadran las tabulaciones del documento boton derecho dar formato al documentoy te lo ordena.

>Builwith te dice con que esta hecha esa pagina.



Si quiero añadir una lista anidada a una lista ya hecha la lista va en el contenido del elemento.

### Imagenes
  #### Imagenes internas

- `<img>` () → para las imagenes ejemplo abajo

<img src="imagenes/star-trek.jpg" alt="Imagen de star-trek" width="200" height="">

```Markdown
<img src="imagenes/star-trek.jpg" alt="Imagen de star-trek" width="200" height="">

```
> en el alt siempre hay que poner un texto para poder pasar la validación en el width y el height solo se modifica 1 para mantener la proporción el sistema el que dejes en blanco lo adapta.

  #### Imagenes externas
  >https://tinyurl.com/ 
  con esta pagina se recorta la url de la imagen y se usa tal y como la otra


#### Mapa de imagenes
  >https://www.image-map.net/ En esta pagina se trabaja con la imagen para que te de el codigo del mapa para html


### Tablas


- `<table>` (2) → tabla  
- `<caption>` (3) → título 
- `<tr>` (3) → fila  
    - `<th>` (4) → celda cabecera  
    - `<td>` (4) → celda  

- `<thead>` (3) → filas cabecera
- `<tbody>` (3) → filas cuerpo
- `<tfoot>` (3) → filas pie 


### Formularios

>Es la unica manera de obtener informacion del usuario.

post y get -> el get es mas inseguro pero mas rapido y el post encripta la cabecera por ejemplo usar post en el login y en el formulario de busqueda de una pagina usar el get se pone en el form.



- `<form>` (2) → 
    - `<action>` (3) → la pagina(servidor) que va a procesar los datos del formulario.
    - `<fieldset>` (3) → Recuadro para agrupar los campos del formulario
        - `<label+input:text>` (4) → Recuadro para texto (ej. nombre)
        - `<label+input:password>` (4) → Recuadro para la contraseña (ej. en un login)
        - `<label+input:date>` (4) → Eleccion de fecha
        - `<label+input:email>` (4) → para un correo electronico
        - `<label+input:number>` (4) → para introducir números
        - `<label+input:checkbox>` (4) → para una casilla de verificación (selección múltiple)
        - `<label+input:radio>` (4) → para botones de opción (selección única)
        - `<label+input:file>` (4) → para subir archivos
        - `<label+input:hidden>` (4) → campo oculto que no se muestra al usuario
        - `<label+input:range>` (4) → para un control deslizante de rango
        - `<label+input:search>` (4) → para campos de búsqueda
        - `<label+input:tel>` (4) → para números de teléfono
        - `<label+input:url>` (4) → para direcciones URL
        - `<label+input:color>` (4) → para seleccionar un color
        - `<label+input:datetime-local>` (4) → para seleccionar fecha y hora local
        - `<label+input:month>` (4) → para seleccionar un mes y año
        - `<label+input:time>` (4) → para seleccionar una hora
        - `<label+input:week>` (4) → para seleccionar una semana y año
        - `<input:submit>` (4) → botón para enviar el formulario
        - `<input:reset>` (4) → botón para restablecer los campos del formulario
        - `<input:button>` (4) → botón genérico (requiere JavaScript para su funcionalidad)
        - `<input:image>` (4) → botón de envío con una imagen

### Etiquetas semanticas

- `<header>` (2) → cabezera de página
- `<nav>` (2) → Contenedor para enlaces de navegación
- `<main>` (2) → zona principal solo hay uno
- `<aside>` (2) → Contenido secundario o relacionado, como barras laterales o publicidad (fecha por ejemplo)
- `<section>` (2) → Agrupa contenido temáticamente relacionado
- `<article>` (2) → Contenido autónomo y redistribuible, como una entrada de blog o noticia
- 
> Importante: todos los section y los article deben tener un h2 a h6 o en su defecto un atributo aria_label

- `<figure>` (2) → Contenido auto-contenido, como imágenes, diagramas, fotos, con un pie de foto opcional (`<figcaption>`)
- `<figcaption>` → (3)
- `<footer>` (2) → Pie de página de una sección o documento, a menudo con información de autoría, derechos de copyright o enlaces relacionados
   


###SEO

> Inciso: ratio=contenido(caracteres del contenigo)/codigo(caracteres del codigo)


  














