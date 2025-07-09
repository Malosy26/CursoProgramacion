# Apuntes SQL

> sudo /usr/bin/apt -o APT::Get::Always-Include-Phased-Updates=true upgrade -y



## Instalacion mySQL

- Empezamos haciendo el update y el upgrade.
- sudo apt install mysql-server mysql-client
- Para comprobar la instalacion :
  - sudo service mysql status
- Para poner la contraseña para el acceso al cliente mysql
  - sudo mysql -u root
  - ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'admin';
FLUSH PRIVILEGES;
EXIT;
  - sudo mysql -u root -p ( con esto te pide la contraseña ) o mysql -u root -p 
- Instalar workbench
  - sudo apt install flatpak
  - instalar la 40
  - GTK_THEME=Adwaita:light mysql-workbench (por que si no no tira)
-Script para sql workbench con tema oscuro
nano ~/launch-workbench.sh
#!/bin/bash
GTK_THEME=Adwaita:light mysql-workbench
chmod +x ~/launch-workbench.sh

cp /usr/share/applications/mysql-workbench.desktop ~/.local/share/applications/mysql-workbench.desktop

 nano ~/.local/share/applications/mysql-workbench.desktop
 edita el desktop
 nano ~/.local/share/applications/mysql-workbench.desktop

[Desktop Entry]
Name=MySQL Workbench (Tema Claro)
Comment=Run MySQL Workbench with a light GTK theme to fix UI issues
Exec=env GTK_THEME=Adwaita:light mysql-workbench
Icon=mysql-workbench
Terminal=false
Type=Application
Categories=Development;Database;







