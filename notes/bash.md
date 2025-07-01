
# Personalizacion del bash y información sobre su aprendizaje




## 🎨 Personaliza tu Bash Prompt con Colores y Rama Git

Esta guía te enseña a modificar el prompt de Bash en Linux para mostrar:

✅ Usuario  
✅ Ruta en celeste  
✅ Rama Git en amarillo (si estás en un repositorio)

---

###### 🧩 Paso 1: Abre el archivo `.bashrc`

```bash
nano ~/.bashrc
```

---

###### 🧠 Paso 2: Asegúrate de tener soporte para rama Git

Añade esta línea (si no existe) **antes** de definir `PS1`:

```bash
source /usr/share/git/completion/git-prompt.sh
```

> Esto activa la función `__git_ps1` para mostrar la rama de Git automáticamente.

---

###### 🎨 Paso 3: Define un nuevo prompt con colores

Sustituye o añade esta línea:

```bash
PS1='\u@ \[\033[0;36m\]\w\[\033[0m\]\[\033[0;33m\]$(__git_ps1 " (%s)")\[\033[0m\] \$ '
```

######## 🔍 ¿Qué significa?

| Fragmento                      | Significado                          |
|-------------------------------|--------------------------------------|
| `\u@`                         | Usuario (ej: `dvr@`)                 |
| `\[\033[0;36m\]`              | Inicio de color celeste              |
| `\w`                          | Ruta actual                          |
| `\[\033[0m\]`                 | Reset de color                       |
| `\[\033[0;33m\]`              | Inicio de color amarillo             |
| `$(__git_ps1 " (%s)")`        | Rama de Git entre paréntesis         |
| `\[\033[0m\]`                 | Reset final del color                |
| `\$`                          | Símbolo ($ o ## según permisos)       |

---

###### 💾 Paso 4: Guarda y aplica los cambios

1. Guarda el archivo con `Ctrl + O`, pulsa `Enter`.
2. Sal del editor con `Ctrl + X`.
3. Aplica los cambios:

```bash
source ~/.bashrc
```

---

###### ✅ Resultado esperado

Si estás en un repositorio Git, el prompt se verá así:

```bash
dvr@ ~/REPOSITORIOS/ifcd0112 (main)$
```

- La **ruta** se verá en celeste
- La **rama Git** en amarillo

---

###### 🌈 Colores disponibles en Bash

| Color    | Código              |
|----------|---------------------|
| Negro    | `\[\033[0;30m\]`     |
| Rojo     | `\[\033[0;31m\]`     |
| Verde    | `\[\033[0;32m\]`     |
| Amarillo | `\[\033[0;33m\]`     |
| Azul     | `\[\033[0;34m\]`     |
| Magenta  | `\[\033[0;35m\]`     |
| Celeste  | `\[\033[0;36m\]`     |
| Blanco   | `\[\033[0;37m\]`     |
| Reset    | `\[\033[0m\]`        |

---

🎉 ¡Listo! Tu terminal ahora es funcional, clara y con estilo.




## 🧠 Guía para Aprender a Configurar Bash (y Dominarlo)

Esta guía te enseña qué es Bash, qué lenguaje usa, cómo configurarlo y dónde aprender más.

---

###### 💬 ¿Qué lenguaje usa Bash?

- Bash usa su propio lenguaje: **Bash Script** o **Shell Script**.
- Es un lenguaje **interpretado**, ideal para automatizar tareas y controlar el sistema.

---

###### ⚙️ ¿Qué puedes configurar en Bash?

- Variables de entorno (`PATH`, `EDITOR`, etc.)
- Alias personalizados (`alias gs='git status'`)
- El prompt (`PS1`) y su color
- Autocompletado y sugerencias
- Funciones propias para automatizar comandos
- El comportamiento del shell en general

---

###### 📘 ¿Dónde consultar todas las opciones?

######## 1. Manual integrado

```bash
man bash
```

> Usa `/` para buscar términos como `PS1`, `alias`, `functions`...

---

######## 2. Arch Wiki (muy útil)

- https://wiki.archlinux.org/title/Bash

> Ideal para aprender desde lo básico hasta lo avanzado.

---

######## 3. GNU Bash Reference Manual

- https://www.gnu.org/software/bash/manual/bash.html

> Manual oficial completo, útil como referencia estructurada.

---

###### 📚 Aprende Bash scripting

######## Sitios interactivos recomendados:

- https://www.learnshell.org/
- https://linuxcommand.org/
- https://www.codecademy.com/learn/learn-the-command-line

---

###### 🛠️ Archivos de configuración de Bash

| Archivo              | Función                                 |
|----------------------|------------------------------------------|
| `~/.bashrc`          | Configuración de shell interactivo       |
| `~/.bash_profile`    | Se ejecuta al iniciar sesión             |
| `/etc/bash.bashrc`   | Configuración global para todos los usuarios |
| `/etc/profile`       | Configuración global al iniciar sesión   |

---

###### 🧪 Comandos útiles para explorar Bash

```bash
help                ## Muestra ayuda de comandos internos de Bash
compgen -b          ## Builtin commands (integrados en Bash)
compgen -a          ## Alias definidos
compgen -c          ## Todos los comandos disponibles
compgen -A function ## Funciones definidas
shopt               ## Opciones del shell
set                 ## Variables actuales
declare -f          ## Ver funciones definidas
```

---

###### ✅ Recomendaciones para empezar

- Modifica tu `~/.bashrc` poco a poco (prompt, alias, funciones).
- Crea scripts `.sh` simples para practicar lógica de programación.
- Mira `.bashrc` avanzados en GitHub para inspirarte.
- Cuando domines Bash, prueba otras shells: **Zsh**, **Fish**, **Oh My Bash**, **Starship**...

---

🎓 **¿Quieres un `.bashrc` avanzado de ejemplo?** ¡Pídelo y te lo preparo!

