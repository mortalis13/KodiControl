package org.work.rpicontrol.ui;

import org.work.rpicontrol.R;
import org.work.rpicontrol.utils.Fun;
import org.work.rpicontrol.utils.Vars;
import org.work.rpicontrol.sshutils.ConnectionStatusListener;
import org.work.rpicontrol.sshutils.ExecTaskCallbackHandler;
import org.work.rpicontrol.sshutils.SessionController;
import org.work.rpicontrol.sshutils.SessionUserInfo;
import org.work.rpicontrol.MainService;
import org.work.rpicontrol.utils.SettingsListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.os.Handler;
import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;


public class SettingsDialog {
  
  private static final String PARENT_DIR = "..";
  
  private Context context;
  private Dialog dialog;
  
  private EditText inHost;
  private EditText inUser;
  private EditText inPass;
  private EditText inPort;
  private RadioButton sysTypeKodi;
  private RadioButton sysTypeXbmc;
  
  private LinearLayout focus;
  
  private SettingsListener settingsListener;
  
  
  public SettingsDialog(Context context) {
    this(context, null);
  }
  
  public SettingsDialog(Context context, String startPath) {
    this.context = context;
    
    dialog = new Dialog(context);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setCanceledOnTouchOutside(true);
    dialog.setContentView(R.layout.settings_view);
    
    dialog.setOnDismissListener(d -> {
      saveConfig();
      // Fun.hideKeyboard(focus);
      // focus.requestFocus();
      if (settingsListener != null) settingsListener.onSettingsDismiss();
    });
    
    Window window = dialog.getWindow();
    window.setWindowAnimations(0);
    
    configUI();
  }
  
  
  private void configUI() {
    inHost = dialog.findViewById(R.id.inHost);
    inUser = dialog.findViewById(R.id.inUser);
    inPass = dialog.findViewById(R.id.inPass);
    inPort = dialog.findViewById(R.id.inPort);
    sysTypeKodi = dialog.findViewById(R.id.sysTypeKodi);
    sysTypeXbmc = dialog.findViewById(R.id.sysTypeXbmc);
    
    focus = dialog.findViewById(R.id.focus);
  }
  
  
  public void showDialog() {
    restoreConfig();
    focus.requestFocus();
    dialog.show();
  }
  
  private void saveConfig() {
    String host = inHost.getText().toString().trim();
    String user = inUser.getText().toString().trim();
    String pass = inPass.getText().toString().trim();
    String port = inPort.getText().toString().trim();
    
    String sysType = Vars.SYS_TYPE_KODI;
    if (sysTypeXbmc.isChecked()) {
      sysType = Vars.SYS_TYPE_XBMC;
    }
    
    Fun.saveSharedPref(context, Vars.PREF_KEY_SSH_HOST, host);
    Fun.saveSharedPref(context, Vars.PREF_KEY_SSH_USER, user);
    Fun.saveSharedPref(context, Vars.PREF_KEY_SSH_PASS, pass);
    Fun.saveSharedPref(context, Vars.PREF_KEY_SSH_PORT, port);
    Fun.saveSharedPref(context, Vars.PREF_KEY_SYS_TYPE, sysType);
  }
  
  private void restoreConfig() {
    String host = Fun.getSharedPref(context, Vars.PREF_KEY_SSH_HOST);
    String user = Fun.getSharedPref(context, Vars.PREF_KEY_SSH_USER);
    String pass = Fun.getSharedPref(context, Vars.PREF_KEY_SSH_PASS);
    String port = Fun.getSharedPref(context, Vars.PREF_KEY_SSH_PORT);
    String sysType = Fun.getSharedPref(context, Vars.PREF_KEY_SYS_TYPE);
    
    if (Fun.empty(host)) {
      host = Vars.DEFAULT_SSH_HOST;
    }
    if (Fun.empty(user)) {
      user = Vars.DEFAULT_SSH_USER;
    }
    if (Fun.empty(pass)) {
      pass = Vars.DEFAULT_SSH_PASS;
    }
    if (Fun.empty(port)) {
      port = Vars.DEFAULT_SSH_PORT;
    }
    if (Fun.empty(sysType)) {
      sysType = Vars.SYS_TYPE_KODI;
    }
    
    inHost.setText(host);
    inUser.setText(user);
    inPass.setText(pass);
    inPort.setText(port);
    
    if (sysType.equals(Vars.SYS_TYPE_XBMC)) {
      sysTypeXbmc.setChecked(true);
    }
    else {
      sysTypeKodi.setChecked(true);
    }
  }
  
  
  public void setSettingsListener(SettingsListener settingsListener) {
    this.settingsListener = settingsListener;
  }
  
}
