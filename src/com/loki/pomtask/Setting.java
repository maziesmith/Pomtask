package com.loki.pomtask;

import com.example.pomtask.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Setting extends ListActivity {
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