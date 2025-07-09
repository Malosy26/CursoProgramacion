> **Tip:** Mantén el CSS en archivos externos para mejorar mantenimiento y caché del navegador.

> Luz noctura configurar y activar si ves que los colores se trastocan

---

## 2. Selectores básicos y buenas prácticas
- `*` (universal) — útil para *reset*, úsalo con cuidado.  
- `h1`, `p`, etc. (tipo) — procura un solo `h1` por documento para SEO.  
- `.clase` (clase) — reutilizable; elige nombres descriptivos.  
- `#id` (ID) — único por página; evita duplicados.  
- `[attr=value]` (atributo) — p. ej. `input[required]`.  
- **Pseudoclases:** `:hover`, `:focus`, `:active`, etc.  
- **Pseudoelementos:** `::before`, `::after`, `::first-line`, etc.  

---

## 3. Posicionamiento y Display
| Propiedad | Uso | Nota |
| --------- | --- | ---- |
| `static` | Flujo natural (por defecto) | — |
| `relative` | Desplaza respecto a su posición original | Mantiene su hueco |
| `absolute` | Sale del flujo; referencia ancestro posicionado | Ideal para tooltips |
| `fixed` | Fijo al *viewport* | Barras flotantes |
| `float / clear` | Antiguo sistema de _layout_ | Hoy se prefiere Flex/Grid |
| `display: flex` | Distribución 1‑D | `gap` simplifica espaciado |
| `display: grid` | Distribución 2‑D | `grid-template-areas` |
| `z-index` | Profundidad | Solo en elementos posicionados |
| `overflow` | Control de desbordes | `hidden`, `scroll`, etc. |

---

## 4. Tipografía y texto
- **Fuentes:** `font-family`, `@import`, `@font-face`.  
- **Peso y estilo:** `font-weight`, `font-style`, `font-variant`.  
- **Espaciado:** `line-height`, `letter-spacing`, `word-spacing`.  
- **Transformaciones:** `text-transform`, `text-decoration`.  
- **Alineación:** `text-align`, `vertical-align`, `text-indent`.  

---

## 5. Reset / Normalize
Se usa para eliminar discrepancias entre navegadores:

```css
*{ margin:0; padding:0; box-sizing:border-box; }
```

O bien emplea **normalize.css** para un enfoque menos agresivo.

---

## 6. Listas personalizadas
- `list-style-type` — cambia viñetas (ej. `lower-greek`).  
- `list-style-image` — sustituye viñetas por imágenes.  
- Técnicas con `::before` para iconos SVG o emojis.

---

## 7. Tablas
- `border-collapse: collapse;` — une bordes de celdas.  
- `caption-side: bottom;` — título debajo de la tabla.  
- Propiedades *shorthand*: `border`, `border-radius`, `border-spacing`.

---

## 8. Extras modernos
| Propiedad | Uso destacado |
| --------- | ------------- |
| `box-shadow` | Sombras suaves |
| `border-radius` | Bordes redondeados |
| `transition` / `animation` | Micro‑animaciones y _keyframes_ |
| `transform` | Escalas, giros, traslaciones 2D/3D |
| `filter` | Efectos visuales (blur, grayscale, etc.) |

---

## 9. Recursos recomendados
- **CSS Zen Garden** — inspiración sobre separación contenido/diseño.  
- **MDN Web Docs** — referencia más completa.  
- **Can I Use** — compatibilidad de propiedades por navegador.  