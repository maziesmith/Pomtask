package com.loki.pomtask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.pomtask.R;

public class TaskList extends ListActivity {
	private DBAdapter myDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_list);
		myDB = new DBAdapter(this);
		myDB.open();
		Cursor c = myDB.getAllRows();
		List<Task> tasks = new ArrayList<Task>();
		while (!c.isAfterLast()) {
			Task task = CursorToTask(c);
			tasks.add(task);
			c.moveToNext();
		}
		c.close();
		ArrayAdapter<Task> adapter = new ArrayAdapter<Task>(this,
				android.R.layout.simple_list_item_1, tasks);
		setListAdapter(adapter);

	}
	@Override
	  protected void onResume() {
	    myDB.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    myDB.close();
	    super.onPause();
	  }
	private Task CursorToTask(Cursor c) {
		// TODO Auto-generated method stub
		Task task = new Task();
		task.setTaskName(c.getString(1));
		task.setPrior(c.getString(2));
		task.setList(c.getString(3));
		task.setDuedate(StrToDate(c.getString(4)));
		task.setReminder(StrToDate(c.getString(5)));
		task.setRepeat(c.getString(6));
		task.setGoal(c.getInt(7));
		return task;
	}

	private Date StrToDate(String string) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = format.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

}
