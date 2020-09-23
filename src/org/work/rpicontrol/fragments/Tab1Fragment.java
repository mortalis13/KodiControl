package org.work.rpicontrol.fragments;

import org.work.rpicontrol.R;
import org.work.rpicontrol.utils.Fun;
import org.work.rpicontrol.utils.Vars;
import org.work.rpicontrol.sshutils.ConnectionStatusListener;
import org.work.rpicontrol.sshutils.ExecTaskCallbackHandler;
import org.work.rpicontrol.sshutils.SessionController;
import org.work.rpicontrol.sshutils.SessionUserInfo;
import org.work.rpicontrol.MainService;
import org.work.rpicontrol.ui.SettingsDialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.KeyEvent;
import android.graphics.Color;
import android.os.Handler;

import android.text.TextWatcher;
import android.text.Editable;


public class Tab1Fragment extends Fragment implements OnClickListener {
  
  public static final String FRAGMENT_TAG = "Tab1Fragment";
  private static final String TAG = "rpicontrol";
  
  private Context context;
  private Activity activity;
  
  private View rootView;
  
  private TextView mConnectStatus;

  private Button btnConnect;
  private Button btnDisconnect;
  private Button btnSettings;
  
  private EditText textInput;
  private ImageButton btnSendText;
  
  private Button btnNum1;
  private Button btnNum2;
  private Button btnNum3;
  private Button btnNum4;
  private Button btnNum5;
  private Button btnNum6;
  private Button btnNum7;
  private Button btnNum8;
  private Button btnNum9;
  private Button btnNum0;
  private Button btnVolDown;
  private Button btnUp;
  private Button btnVolUp;
  private Button btnRestart;
  private Button btnLeft;
  private Button btnOk;
  private Button btnRight;
  private Button btnReboot;
  private Button btnDown;
  private Button btnConfig;
  private Button btnBack;
  private Button btnMute;
  
  private Button btnStepBack;
  private Button btnPlay;
  private Button btnStop;
  private Button btnStepForward;
  private Button btnMenu;
  private Button btnVideoInfo;
  private Button btnAspectRatio;
  private Button btnCreateBookmark;
  private Button btnVideoCodecInfo;
  private Button btnShowBookmarks;

  private Handler mHandler;
  private Handler mTvHandler;
  
  private SettingsDialog settingsDialog;
  
  
  public static Tab1Fragment newInstance(Context context) {
    Tab1Fragment fragment = new Tab1Fragment();
    Bundle args = new Bundle();
    args.putString(Vars.FRAGMENT_TAG_ARG, FRAGMENT_TAG);
    fragment.setArguments(args);
    return fragment;
  }
  
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.tab1_view, container, false);
    
    settingsDialog = new SettingsDialog(context);
    
    btnConnect = (Button) rootView.findViewById(R.id.btnConnect);
    btnSettings = (Button) rootView.findViewById(R.id.btnSettings);
    btnDisconnect = (Button) rootView.findViewById(R.id.btnDisconnect);
    mConnectStatus = (TextView) rootView.findViewById(R.id.connectstatus);
    
    textInput = rootView.findViewById(R.id.textInput);
    btnSendText = rootView.findViewById(R.id.btnSendText);
    
    btnNum1 = rootView.findViewById(R.id.btnNum1);
    btnNum2 = rootView.findViewById(R.id.btnNum2);
    btnNum3 = rootView.findViewById(R.id.btnNum3);
    btnNum4 = rootView.findViewById(R.id.btnNum4);
    btnNum5 = rootView.findViewById(R.id.btnNum5);
    btnNum6 = rootView.findViewById(R.id.btnNum6);
    btnNum7 = rootView.findViewById(R.id.btnNum7);
    btnNum8 = rootView.findViewById(R.id.btnNum8);
    btnNum9 = rootView.findViewById(R.id.btnNum9);
    btnNum0 = rootView.findViewById(R.id.btnNum0);
    btnVolDown = rootView.findViewById(R.id.btnVolDown);
    btnUp = rootView.findViewById(R.id.btnUp);
    btnVolUp = rootView.findViewById(R.id.btnVolUp);
    btnRestart = rootView.findViewById(R.id.btnRestart);
    btnLeft = rootView.findViewById(R.id.btnLeft);
    btnOk = rootView.findViewById(R.id.btnOk);
    btnRight = rootView.findViewById(R.id.btnRight);
    btnReboot = rootView.findViewById(R.id.btnReboot);
    btnDown = rootView.findViewById(R.id.btnDown);
    btnConfig = rootView.findViewById(R.id.btnConfig);
    btnBack = rootView.findViewById(R.id.btnBack);
    btnMute = rootView.findViewById(R.id.btnMute);
    
    btnStepBack = rootView.findViewById(R.id.btnStepBack);
    btnPlay = rootView.findViewById(R.id.btnPlay);
    btnStop = rootView.findViewById(R.id.btnStop);
    btnStepForward = rootView.findViewById(R.id.btnStepForward);
    btnMenu = rootView.findViewById(R.id.btnMenu);
    
    btnVideoInfo = rootView.findViewById(R.id.btnVideoInfo);
    btnAspectRatio = rootView.findViewById(R.id.btnAspectRatio);
    btnCreateBookmark = rootView.findViewById(R.id.btnCreateBookmark);
    btnVideoCodecInfo = rootView.findViewById(R.id.btnVideoCodecInfo);
    btnShowBookmarks = rootView.findViewById(R.id.btnShowBookmarks);
    
    btnConnect.setOnClickListener(this);
    btnDisconnect.setOnClickListener(this);
    btnSettings.setOnClickListener(this);
    
    btnSendText.setOnClickListener(this);
    
    btnNum1.setOnClickListener(this);
    btnNum2.setOnClickListener(this);
    btnNum3.setOnClickListener(this);
    btnNum4.setOnClickListener(this);
    btnNum5.setOnClickListener(this);
    btnNum6.setOnClickListener(this);
    btnNum7.setOnClickListener(this);
    btnNum8.setOnClickListener(this);
    btnNum9.setOnClickListener(this);
    btnNum0.setOnClickListener(this);
    btnVolDown.setOnClickListener(this);
    btnUp.setOnClickListener(this);
    btnVolUp.setOnClickListener(this);
    btnRestart.setOnClickListener(this);
    btnLeft.setOnClickListener(this);
    btnOk.setOnClickListener(this);
    btnRight.setOnClickListener(this);
    btnReboot.setOnClickListener(this);
    btnDown.setOnClickListener(this);
    btnConfig.setOnClickListener(this);
    btnBack.setOnClickListener(this);
    btnMute.setOnClickListener(this);
    
    btnStepBack.setOnClickListener(this);
    btnPlay.setOnClickListener(this);
    btnStop.setOnClickListener(this);
    btnStepForward.setOnClickListener(this);
    btnMenu.setOnClickListener(this);
    
    btnVideoInfo.setOnClickListener(this);
    btnAspectRatio.setOnClickListener(this);
    btnCreateBookmark.setOnClickListener(this);
    btnVideoCodecInfo.setOnClickListener(this);
    btnShowBookmarks.setOnClickListener(this);
    
    textInput.addTextChangedListener(new TextWatcher() {
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        sendText(false);
      }
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
      public void afterTextChanged(Editable s) {}
    });
    
    textInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        sendText(true);
        return false;
      }
    });

    mHandler = new Handler();
    mTvHandler = new Handler();
    
    setStatusNotConnected();
    
    return rootView;
  }
  
  @Override
  public void onResume() {
    Fun.logd("Tab1Fragment.onResume()");
    super.onResume();
  }
  
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity == null) return;
    this.context = activity;
    this.activity = activity;
  }
  
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context == null) return;
    this.context = context;
  }
  
  
  // ---------------------
  
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btnDisconnect:
        disconnectSession();
        break;
      
      case R.id.btnConnect:
        Fun.log("btnConnect");
        connectSession();
        break;
      
      case R.id.btnSettings:
        openSettings();
        break;
        
      case R.id.btnSendText:
        sendTextButtonPress();
        break;
      
      
      case R.id.btnNum1:
        execNamedCommand("Num1");
        break;
      
      case R.id.btnNum2:
        execNamedCommand("Num2");
        break;
      
      case R.id.btnNum3:
        execNamedCommand("Num3");
        break;
      
      case R.id.btnNum4:
        execNamedCommand("Num4");
        break;
      
      case R.id.btnNum5:
        execNamedCommand("Num5");
        break;
      
      case R.id.btnNum6:
        execNamedCommand("Num6");
        break;
      
      case R.id.btnNum7:
        execNamedCommand("Num7");
        break;
      
      case R.id.btnNum8:
        execNamedCommand("Num8");
        break;
      
      case R.id.btnNum9:
        execNamedCommand("Num9");
        break;
      
      case R.id.btnNum0:
        execNamedCommand("Num0");
        break;
      
      case R.id.btnVolDown:
        execNamedCommand("VolDown");
        break;
      
      case R.id.btnUp:
        execNamedCommand("Up");
        break;
      
      case R.id.btnVolUp:
        execNamedCommand("VolUp");
        break;
      
      case R.id.btnRestart:
        execNamedCommand("Restart");
        break;
      
      case R.id.btnLeft:
        execNamedCommand("Left");
        break;
      
      case R.id.btnOk:
        execNamedCommand("Ok");
        break;
      
      case R.id.btnRight:
        execNamedCommand("Right");
        break;
      
      case R.id.btnReboot:
        execNamedCommand("Reboot");
        break;
      
      case R.id.btnDown:
        execNamedCommand("Down");
        break;
      
      case R.id.btnConfig:
        execNamedCommand("Config");
        break;
      
      case R.id.btnBack:
        execNamedCommand("Back");
        break;
        
      case R.id.btnMute:
        execNamedCommand("Mute");
        break;
      
      case R.id.btnStepBack:
        execNamedCommand("StepBack");
        break;
      case R.id.btnPlay:
        execNamedCommand("Play");
        break;
      case R.id.btnStop:
        execNamedCommand("Stop");
        break;
      case R.id.btnStepForward:
        execNamedCommand("StepForward");
        break;
      case R.id.btnMenu:
        execNamedCommand("Menu");
        break;
      
      case R.id.btnVideoInfo:
        execNamedCommand("VideoInfo");
        break;
      case R.id.btnAspectRatio:
        execNamedCommand("AspectRatio");
        break;
      case R.id.btnCreateBookmark:
        execNamedCommand("CreateBookmark");
        break;
      case R.id.btnShowBookmarks:
        execNamedCommand("ShowBookmarks");
        break;
      case R.id.btnVideoCodecInfo:
        execNamedCommand("VideoCodecInfo");
        break;
    }
  }
  
  
  private void openSettings() {
    if (settingsDialog != null) {
      settingsDialog.showDialog();
    }
  }
  
  private void connectSession() {
    Fun.log("connectSession()");

    btnConnect.setEnabled(false);
    
    setStatusWaiting();
    
    String host = Fun.getSharedPref(context, Vars.PREF_KEY_SSH_HOST);
    String user = Fun.getSharedPref(context, Vars.PREF_KEY_SSH_USER);
    String pass = Fun.getSharedPref(context, Vars.PREF_KEY_SSH_PASS);
    String portStr = Fun.getSharedPref(context, Vars.PREF_KEY_SSH_PORT);
    
    int port = 22;
    try {
      port = Integer.parseInt(portStr);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    Fun.log(String.format("Connecting to: %s:%d", host, port));
    SessionUserInfo mSUI = new SessionUserInfo(user, host, pass, port);

    ConnectionStatusListener mListener = new ConnectionStatusListener() {
      public void onDisconnected() {
        mTvHandler.post(new Runnable() {
          public void run() {
            setStatusNotConnected();
            btnConnect.setEnabled(true);
          }
        });
      }
      
      public void onConnected() {
        mTvHandler.post(new Runnable() {
          public void run() {
            setStatusConnected();
          }
        });
      }
    };

    SessionController.getSessionController().setConnectionStatusListener(mListener);
    SessionController.getSessionController().setUserInfo(mSUI);
    SessionController.getSessionController().connect();
  }
  
  private void disconnectSession() {
    Fun.log("disconnectSession()");
    
    try {
      btnConnect.setEnabled(true);
      // SessionController.getSessionController().setConnectionStatusListener(null);
      if (SessionController.isConnected()) {
        SessionController.getSessionController().disconnect();
      }
      else {
        setStatusNotConnected();
      }
    }
    catch (Throwable t) {
      Fun.loge("Disconnect exception " + t.getMessage());
      setStatusNotConnected();
      t.printStackTrace();
    }
  }
  
  
  private void execTest() {
    Fun.log("execTest()");
    
    String command = "ls -la";
    ExecTaskCallbackHandler taskCallback = new ExecTaskCallbackHandler() {
      public void onComplete(String result) {
        Fun.log("onComplete:");
        Fun.log(result);
        
        Fun.SSH_LOG_TEXT.append(result + "\n");
        
        mTvHandler.post(new Runnable() {
          public void run() {
            MainService.get().reloadSshLog();
          }
        });
      }
      
      public void onFail() {
        Fun.toast(context, "Task Failed");
      }
    };
    
    SessionController.getSessionController().executeCommand(mHandler, taskCallback, command);
  }
  
  
  private void execNamedCommand(String cmd) {
    MainService.get().execNamedCommand(cmd);
  }
  
  private void sendText(boolean done) {
    String cmd = "curl -s -X POST -H 'Content-type: application/json' -d '{\"jsonrpc\": \"2.0\", \"method\": \"Input.SendText\", \"params\": {\"text\": \"" + textInput.getText() + "\", \"done\": " + String.valueOf(done) + "}, \"id\": 1}' 127.0.0.1:8080/jsonrpc";
    MainService.get().execCommand(cmd);
  }
  
  private void sendTextButtonPress() {
    textInput.onEditorAction(EditorInfo.IME_ACTION_DONE);
  }
  
  
  // ---------------------
  
  private void setStatusConnected() {
    mConnectStatus.setText("CONNECTED");
    mConnectStatus.setTextColor(Fun.getColor(R.color.connection_status_connected));
  }
  
  private void setStatusWaiting() {
    mConnectStatus.setTextColor(Fun.getColor(R.color.connection_status_waiting));
  }
  
  private void setStatusNotConnected() {
    mConnectStatus.setText("NOT CONNECTED");
    mConnectStatus.setTextColor(Fun.getColor(R.color.connection_status_not_connected));
  }
  
}
