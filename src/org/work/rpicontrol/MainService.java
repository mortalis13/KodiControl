package org.work.rpicontrol;

import java.io.File;
import java.util.List;

import org.work.rpicontrol.fragments.Tab2Fragment;
import org.work.rpicontrol.utils.Fun;
import org.work.rpicontrol.utils.Vars;
import org.work.rpicontrol.sshutils.ExecTaskCallbackHandler;
import org.work.rpicontrol.sshutils.SessionController;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.os.Looper;
import android.os.Handler;


public class MainService {
  
  private static MainService instance = new MainService();
  
  private MainActivity mainActivity;
  private FragmentManager fragmentManager;
  private ViewPager viewPager;
  
  private MainService() {
    
  }
  
  public static void init(MainActivity mainActivity, FragmentManager fragmentManager, ViewPager viewPager) {
    instance.mainActivity = mainActivity;
    instance.fragmentManager = fragmentManager;
    instance.viewPager = viewPager;
  }
  
  public static void release() {
    instance.mainActivity = null;
  }
  
  public static MainService get() {
    return instance;
  }
  
  
  public void reloadSshLog() {
    if (fragmentManager == null) {
      Fun.loge("fragmentManager null");
      return;
    }
    
    // String tag = Tab2Fragment.FRAGMENT_TAG;
    int position = 1;
    String tag = "android:switcher:" + viewPager.getId() + ":" + position;
    Tab2Fragment tab2Fragment = (Tab2Fragment) fragmentManager.findFragmentByTag(tag);
    
    if (tab2Fragment == null) {
      Fun.loge("tab2Fragment null");
      return;
    }
    
    tab2Fragment.reloadData();
  }
  
  public void execNamedCommand(String cmd) {
    Fun.logd("execNamedCommand()");
    
    String command = "ls -la";
    if (Vars.COMMANDS_MAP.containsKey(cmd)) {
      command = Vars.COMMANDS_MAP.get(cmd);
    }
    else {
      Fun.loge("No such command: " + cmd);
      return;
    }
    
    execCommand(command);
  }
  
  public void execCommand(String command) {
    Fun.logd("execCommand()");
    
    ExecTaskCallbackHandler taskCallback = new ExecTaskCallbackHandler() {
      public void onComplete(String result) {
        Fun.logd("onComplete:");
        Fun.logd(result);
        
        Fun.SSH_LOG_TEXT.append(result + "\n");
        
        new Handler(Looper.getMainLooper()).post(new Runnable() {
          public void run() {
            reloadSshLog();
          }
        });
      }
      
      public void onFail() {
        Fun.toast(mainActivity, "Command Failed");
      }
    };
    
    Handler mHandler = new Handler();
    SessionController.getSessionController().executeCommand(mHandler, taskCallback, command);
  }
  
}
