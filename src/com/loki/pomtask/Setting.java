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

		
		if(position==2){
			Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
			openAbout();

		}
		/*switch (position) {
		case 0:
			// openStat();

		case 1:
			// openSettings();

		case 2:
			Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
			openAbout();
			break;
		default:
			// return //
		}*/
	}

	public void openAbout() {
		
		Intent about = new Intent(this, About.class);
		startActivity(about);
	}

}