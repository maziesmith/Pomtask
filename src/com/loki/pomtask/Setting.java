package com.loki.pomtask;

import com.example.pomtask.R;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Setting extends ListActivity  {
		
	//-----------------------manu --------------------
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
	
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
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
    public void openSearch(){
    	Intent search = new Intent(this,Search.class);
    	startActivity(search);
    }
    
	//----------------------- end manu --------------------
	
    public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		String[] values = new String[] { "Statistics", "Set a Timer", "About" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}
    
    
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);

		if(position==0){
			Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
			openStat();

		}
		if(position==1){
			Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
			openSetTime();

		}
		if(position==2){
			Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
			openAbout();

		}
		
	}

	public void openAbout() {
		
		Intent about = new Intent(this, About.class);
		startActivity(about);
	}
	public void openStat() {
		
		Intent stat = new Intent(this, Stat.class);
		startActivity(stat);
	}

	public void openSetTime() {
	
	Intent settime = new Intent(this,SetTime.class);
	startActivity(settime);
	}



}