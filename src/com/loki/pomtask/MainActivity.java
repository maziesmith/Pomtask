package com.loki.pomtask;


import com.example.pomtask.R;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.Menu;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	
    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
    	final ActionBar actionbar = getActionBar();
    	getActionBar().setDisplayHomeAsUpEnabled(true);
    	actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionbar.addTab(
    			actionbar.newTab().setText("To do").setTabListener(this));
    	actionbar.addTab(
    			actionbar.newTab().setText("Doing").setTabListener(this));	
    	actionbar.addTab(
    			actionbar.newTab().setText("Done").setTabListener(this));
    	}

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                //openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            case R.id.content_new:
            	openNewContent();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void openNewContent(){
    	Intent neweditTask = new Intent(this,NewEditTaskActivity.class);
    	startActivity(neweditTask);
    }
    public void openSettings(){
    	Intent setting = new Intent(this,Setting.class);
    	startActivity(setting);
    }
    
    
	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
			
    
}
