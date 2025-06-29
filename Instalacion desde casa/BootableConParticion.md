# 🖥️ Instalación de un USB Booteable con Ventoy en Linux

> ✍️ Por Daniel Villa Rayo

---

## 🧰 1. Requisitos previos

Antes de comenzar, asegúrate de tener lo siguiente:

- ✅ La **ISO de Linux Mint** descargada.
- ✅ Una **memoria USB** de al menos **16 GB** (se recomienda 32 GB o más).
- ✅ Conexión a internet para descargar herramientas necesarias.
- ✅ Soporte para el sistema de archivos **exFAT** en Linux.

Instala el soporte exFAT con:

```bash
sudo apt update
sudo apt install exfat-fuse exfat-utils
```

---

## 📥 2. Descargar e instalar Ventoy

1. 📦 Descarga la última versión de **Ventoy para Linux** desde:  
   👉 https://www.ventoy.net/en/download.html

2. 📂 Extrae el archivo descargado:

```bash
tar -xvf ventoy-*.tar.gz
```

3. 📁 Entra en la carpeta extraída:

```bash
cd ventoy-*
```

4. 🔌 Conecta tu USB y verifica el nombre del dispositivo:

```bash
lsblk
```

🔍 Identifica el nombre correcto del USB, por ejemplo `/dev/sdc` (⚠️ sin número al final).

5. 🧹 (Opcional) Limpia la tabla de particiones del USB:

```bash
sudo wipefs -a /dev/sdc
```

6. 🚀 Instala Ventoy en el USB (⚠️ esto borrará todos los datos del USB):

```bash
sudo ./Ventoy2Disk.sh -i /dev/sdc
```

Confirma con **y** cuando se te pregunte.  
Después de la instalación, el USB tendrá dos particiones:
- `sdc1` → exFAT, para almacenar ISOs.
- `sdc2` → Reservada para Ventoy.

---

## 📁 3. Copiar ISOs y archivos

1. 🔍 Verifica si la partición exFAT se ha montado automáticamente. Si no:

```bash
sudo mkdir -p /media/ventoy
sudo mount /dev/sdc1 /media/ventoy
```

2. 📥 Copia la ISO de Linux Mint:

```bash
cp ~/Descargas/linuxmint*.iso /media/ventoy/
```

3. 📁 Puedes añadir más ISOs. Ventoy te dejará elegir cuál iniciar al arrancar.

---

## 🧪 4. Arrancar desde el USB

1. 🔄 Reinicia el ordenador.
2. 🛠️ Entra al menú de arranque o BIOS/UEFI y selecciona el USB.
3. 📜 Ventoy mostrará una lista de las ISOs copiadas.
4. 🧩 Selecciona la ISO deseada (ej. Linux Mint) y presiona Enter.

---

## 📝 Notas finales

- Puedes tener múltiples ISOs en el USB.
- Ventoy es compatible con Windows, Linux, antivirus, utilidades, etc.
- Si deseas borrar Ventoy y restaurar el USB:

```bash
sudo wipefs -a /dev/sdc
```

- Siempre haz una copia de seguridad de tus datos antes de formatear.
