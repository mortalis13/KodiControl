package org.work.rpicontrol.utils;

import android.os.Environment;
import java.util.Map;
import java.util.HashMap;

public class Vars {
  
  public enum LogLevel {VERBOSE, DEBUG, INFO, WARN, ERROR};
  public static final LogLevel APP_LOG_LEVEL = LogLevel.DEBUG;
  
  public static final String APP_LOG_TAG = "rpicontrol";
  
  // ---
  
  public static final String PREFS_FILE = "rpicontrol_prefs";
  
  public static final String PREF_KEY_SSH_HOST = "PREF_KEY_SSH_HOST";
  public static final String PREF_KEY_SSH_USER = "PREF_KEY_SSH_USER";
  public static final String PREF_KEY_SSH_PASS = "PREF_KEY_SSH_PASS";
  public static final String PREF_KEY_SSH_PORT = "PREF_KEY_SSH_PORT";
  public static final String PREF_KEY_LAST_COMMAND = "PREF_KEY_LAST_COMMAND";
  public static final String PREF_KEY_SYS_TYPE = "PREF_KEY_SYS_TYPE";
  
  public static final String DEFAULT_SSH_HOST = "172.26.21.7";
  public static final String DEFAULT_SSH_USER = "root";
  public static final String DEFAULT_SSH_PASS = "ambartel";
  public static final String DEFAULT_SSH_PORT = "22";
  
  public static final String SYS_TYPE_KODI = "Kodi";
  public static final String SYS_TYPE_XBMC = "XBMC";
  
  public static final String FRAGMENT_TAG_ARG = "tag";
  public static final String TAB_TITLE_1 = "Controls";
  public static final String TAB_TITLE_2 = "Logs";
  
  public static final String KODI_SEND_ACTION = "kodi-send --action=";
  
  public static final Map<String, String> COMMANDS_MAP = new HashMap<String, String>() {{
    put("Num1", KODI_SEND_ACTION + "\"Action(Number1)\"");
    put("Num2", KODI_SEND_ACTION + "\"Action(Number2)\"");
    put("Num3", KODI_SEND_ACTION + "\"Action(Number3)\"");
    put("Num4", KODI_SEND_ACTION + "\"Action(Number4)\"");
    put("Num5", KODI_SEND_ACTION + "\"Action(Number5)\"");
    put("Num6", KODI_SEND_ACTION + "\"Action(Number6)\"");
    put("Num7", KODI_SEND_ACTION + "\"Action(Number7)\"");
    put("Num8", KODI_SEND_ACTION + "\"Action(Number8)\"");
    put("Num9", KODI_SEND_ACTION + "\"Action(Number9)\"");
    put("Num0", KODI_SEND_ACTION + "\"Action(Number0)\"");
    
    put("VolDown", KODI_SEND_ACTION + "\"Action(VolumeDown)\"");
    put("Up"     , KODI_SEND_ACTION + "\"Action(Up)\"");
    put("VolUp"  , KODI_SEND_ACTION + "\"Action(VolumeUp)\"");
    put("Left"   , KODI_SEND_ACTION + "\"Action(Left)\"");
    put("Ok"     , KODI_SEND_ACTION + "\"Action(Select)\"");
    put("Right"  , KODI_SEND_ACTION + "\"Action(Right)\"");
    put("Addons" , KODI_SEND_ACTION + "\"ActivateWindow(AddonBrowser)\"");
    put("Down"   , KODI_SEND_ACTION + "\"Action(Down)\"");
    put("Back"   , KODI_SEND_ACTION + "\"Action(Back)\"");
    put("Mute"   , KODI_SEND_ACTION + "\"Action(Mute)\"");
    put("Config" , KODI_SEND_ACTION + "\"ActivateWindow(Settings)\"");
    
    put("StepBack"    , KODI_SEND_ACTION + "\"Action(StepBack)\"");
    put("Play"        , KODI_SEND_ACTION + "\"Action(PlayPause)\"");
    put("Stop"        , KODI_SEND_ACTION + "\"Action(Stop)\"");
    put("StepForward" , KODI_SEND_ACTION + "\"Action(StepForward)\"");
    put("Menu"        , KODI_SEND_ACTION + "\"OSD\"");
    
    put("VideoInfo"      , KODI_SEND_ACTION + "\"Info\"");
    put("AspectRatio"    , KODI_SEND_ACTION + "\"AspectRatio\"");
    put("CreateBookmark" , KODI_SEND_ACTION + "\"CreateBookmark\"");
    put("ShowBookmarks"  , KODI_SEND_ACTION + "\"ActivateWindow(VideoBookmarks)\"");
    put("VideoCodecInfo" , KODI_SEND_ACTION + "\"PlayerProcessInfo\"");
    
    put("Restart", "killall -9 kodi.bin");
    put("Reboot", "reboot");
    put("CpuInfo", "cat /proc/cpuinfo");
  }};
  
}
