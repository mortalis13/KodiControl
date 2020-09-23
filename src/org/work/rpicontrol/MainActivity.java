package org.work.rpicontrol;

import java.io.File;
import java.util.List;
import android.view.WindowManager;

import org.work.rpicontrol.components.CustomViewPager;
import org.work.rpicontrol.fragments.Tab1Fragment;
import org.work.rpicontrol.fragments.Tab2Fragment;
import org.work.rpicontrol.utils.Fun;
import org.work.rpicontrol.utils.Vars;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
  
  private SectionsPagerAdapter sectionsPagerAdapter;
  
  private CustomViewPager viewPager;
  private TabLayout tabLayout;
  
  private Context context;
  
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    
    context = this;
    Fun.setContext(context);
    
    FragmentManager fm = getSupportFragmentManager();
    for (Fragment fragment: fm.getFragments()) {
      fm.beginTransaction().remove(fragment).commitNow();
    }
    
    viewPager = findViewById(R.id.viewPager);
    tabLayout = findViewById(R.id.tabs);
    
    sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
    viewPager.setAdapter(sectionsPagerAdapter);
    viewPager.setScrollDuration(0);
    tabLayout.setupWithViewPager(viewPager);
    
    MainService.init(this, fm, viewPager);
  }
  
  @Override
  public void onBackPressed() {
    Fun.logd("MainActivity.onBackPressed()");
    super.onBackPressed();
    finish();
    android.os.Process.killProcess(android.os.Process.myPid());
  }
  
  
  // ------------------------------ Classes ------------------------------
  
  public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
    }
    
    @Override
    public Fragment getItem(int position) {
      Fun.logd("SectionsPagerAdapter.getItem(): " + position);
      
      Fragment fragment = null;
      switch (position) {
        case 0:
          fragment = Tab1Fragment.newInstance(context);
          break;
        case 1:
          fragment = Tab2Fragment.newInstance(context);
          break;
      }
      
      return fragment;
    }
    
    @Override
    public int getCount() {
      return 2;
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
      case 0:
        return Vars.TAB_TITLE_1;
      case 1:
        return Vars.TAB_TITLE_2;
      }
      
      return null;
    }
  }
  
}
