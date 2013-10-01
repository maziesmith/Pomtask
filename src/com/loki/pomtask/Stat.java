package com.loki.pomtask;

import com.example.pomtask.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Stat extends Activity {
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
		setContentView(R.layout.stat);
	}
	

}