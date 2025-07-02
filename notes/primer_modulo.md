# PRIMER MODULO

- [PRIMER MODULO](#primer-modulo)
  - [🔀 Arranque dual](#-arranque-dual)
  - [💻 IDEs](#-ides)
  - [☕🖥️ Instalar Java y VSC](#️-instalar-java-y-vsc)
    - [☕ Instalar Java](#-instalar-java)
  - [🖥️ Comandos de Consola](#️-comandos-de-consola)
    - [🪟 Windows (MS-DOS/Powershell)](#-windows-ms-dospowershell)
    - [🐧 Linux](#-linux)
    - [🧑‍💻 Instalar Visual Studio Code (VSC)](#-instalar-visual-studio-code-vsc)
  - [🔧 GIT](#-git)
    - [📋 Listado de comandos](#-listado-de-comandos)
      - [🌿 Ramas GIT](#-ramas-git)


## 🔀 Arranque dual
- Los SO son independientes  
- El gestor de arranque de Linux: Grub  
- El gestor de arranque de Win: MBR  
- Windows versión: 11 Pro/Home  
- Linux distribución: Mint Cinnamon → Ubuntu → Debian  
- Núcleos: GNU/Linux → Unix  

---

## 💻 IDEs
- Para MarkDown: Notable  
- Para código: Visual Studio Code  
- Oficial Java: Apache Netbeans  

---

## ☕🖥️ Instalar Java y VSC

### ☕ Instalar Java  
- LTS → Soporte extendido  
1️⃣ Ir a [🔗 Oracle Java](https://www.oracle.com/es/java/technologies/downloads/)  
2️⃣ Descargar versión *LTS (21)*  
3️⃣ Configurar **variables de entorno**  
   - ⌨️ `Win + R` → `cmd`  
   - ⚙️ `SystemPropertiesAdvanced`  
     *(📁 Panel de Control > 🖥️ Sistema > ⚙️ Configuración avanzada del sistema > 🌐 Variables de entorno)*  
   - 🧬 Variables de Entorno  
   - 🛣️ `Path` (del sistema): `C:\Program Files\Java\jdk-21`  

---

## 🖥️ Comandos de Consola

### 🪟 Windows (MS-DOS/Powershell)
- `cmd` → iniciar consola  
- `dir` → ver directorio  
- `cd` → cambiar directorio  
- `cd..` → subir nivel  
- `cls` → limpia consola  

### 🐧 Linux
- `clear` → limpia consola  
- `sudo` → privilegios superusuario  
- `apt update` → actualizar repos  
- `apt upgrade` → actualizar apps  
- `apt install paquete` → instalar paquete  
- `mkdir` → crear directorio  
- `ls` → lista contenido  
- `pwd` → ruta actual  

### 🧑‍💻 Instalar Visual Studio Code (VSC)  
1️⃣ Ir a [🔗 VS Code Download](https://code.visualstudio.com/download#)  
2️⃣ Descargar `System Installer x64`  
3️⃣ Instalar extensiones:  
   - 🌍 **Spanish**  
   - 📦 **Extension Pack for Java** (Microsoft)  
   - 📝 **Markdown All in One**  
     - F1 > markdown all in one: create table of contents  

---

## 🔧 GIT

### 📋 Listado de comandos
- `git init` → iniciar repo  
- `git clone "repo"` → clonar repo  
- `git status` → estado  
- `git add .` → añadir todo  
- `git add notes/Apuntes.md` → archivo individual  
- `git commit -m "mensaje"` → guardar  
- `git commit -a -m "mensaje"` → añadir y guardar  
- `git push` → subir repo  
- `git pull` → descargar repo  

- `git remote`  
  - `git remote add` → añadir remoto  
  - `git remote -v` → ver remotos  
- `git fetch` → descargar sin fusionar  
- `git merge` → fusionar cambios  

#### 🌿 Ramas GIT
- `git branch "rama"` → crear rama  
- `git checkout "rama"` → cambiar rama  
- `git merge rama` → fusionar ramas  

Pasos:  
1️⃣ `git checkout rama_destino`  
2️⃣ `git merge rama_a_fusionar`  
3️⃣ `git commit -m "Rama Fusionada"`  

---