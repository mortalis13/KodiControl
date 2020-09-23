package org.work.rpicontrol.fragments;

import java.util.ArrayList;
import java.util.List;

import org.work.rpicontrol.R;
import org.work.rpicontrol.utils.Fun;
import org.work.rpicontrol.utils.Vars;
import org.work.rpicontrol.MainService;

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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;


public class Tab2Fragment extends Fragment {
  
  public static final String FRAGMENT_TAG = "Tab2Fragment";
  
  private Context context;
  private Activity activity;
  
  private TextView txLog;
  private ImageButton btnExecCommand;
  private Button btnClearLog;
  private EditText inCommand;
  
  
  public Tab2Fragment() {
  }
  
  public Tab2Fragment(Context context) {
    this.context = context;
  }
  
  public static Tab2Fragment newInstance(Context context) {
    Tab2Fragment fragment = new Tab2Fragment(context);
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
    View rootView = inflater.inflate(R.layout.tab2_view, container, false);
    btnExecCommand = rootView.findViewById(R.id.btnExecCommand);
    btnClearLog = rootView.findViewById(R.id.btnClearLog);
    inCommand = rootView.findViewById(R.id.inCommand);
    txLog = rootView.findViewById(R.id.txLog);
    
    txLog.setText(Fun.SSH_LOG_TEXT);
    
    String lastCommand = Fun.getSharedPref(context, Vars.PREF_KEY_LAST_COMMAND);
    inCommand.setText(lastCommand);
    
    btnExecCommand.setOnClickListener(v -> {
      execCommand();
    });
    
    btnClearLog.setOnClickListener(v -> {
      Fun.SSH_LOG_TEXT.setLength(0);
      txLog.setText("");
    });
    
    inCommand.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // if (event == null || event.getAction() != KeyEvent.ACTION_UP) return true;
        execCommand();
        return true;
      }
    });
    
    return rootView;
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
  
  
  // ---------------
  
  private void execCommand() {
    String cmd = inCommand.getText().toString();
    Fun.saveSharedPref(context, Vars.PREF_KEY_LAST_COMMAND, cmd);
    Fun.log("== Command Enter: " + cmd);
    MainService.get().execCommand(cmd);
  }
  
  public void reloadData() {
    txLog.setText(Fun.SSH_LOG_TEXT);
  }
  
}
