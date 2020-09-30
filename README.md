
# KodiControl

![Icon](_img/icon.png)

Remote control for [**Kodi**](https://kodi.tv) and [**LibreELEC**](https://libreelec.tv) on a **Raspberry Pi**.

## Download

- [**releases**](https://github.com/mortalis13/KodiControl-Android/releases)

## How to use

- press **Settings** and fill in the `Host`, `User`, `Password`, `Port` and `System Type` (**Kodi** or **XBMC**), the values are saved when the dialog closes
- press **Connect** to establish a **SSH** connection with a **Raspberry Pi**
- use buttons to navigate the menu and control the playback
- use the **Text** field to type in the text when it's required (search dialogs, login info, etc.)
- in the **Logs** tab you can view the system response and execute remote system commands (like `ls`, `cat`, `df -h`, `kodi-send --action="Action(Stop)"`, etc.)

## Details

- the connection is performed using the **SSH** protocol and the [JSch](http://www.jcraft.com/jsch) library
- native **Kodi** commands are executed with the `kodi-send` command available in the **LibreELEC** distribution

## Screenshots

![Image_1](_img/kodicontrol-1.png)<br><br>
![Image_2](_img/kodicontrol-2.png)<br><br>
![Image_2](_img/kodicontrol-3.png)<br><br>
