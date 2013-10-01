package com.loki.pomtask;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.pomtask.R;

@SuppressLint("ValidFragment")
public class NewEditTaskActivity extends FragmentActivity {
	private EditText taskName;
	private Spinner select_list;
	private Spinner select_repeat;
	private EditText duedate;
	private EditText reminder;
	private static String date;
	private DBAdapter myDB;
	private Task task;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_edit_task);
		openDB();
		addTaskNameListener();
		addChangeList();
		addDuedate();
		addReminder();
		addChangeRepeat();
	}
	public void addReminder() {
		reminder = (EditText) findViewById(R.id.reminderp);
		reminder.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				showTimePickerDialog(v, reminder);
				showDatePickerDialog(v);

			}
		});
		
	}
	public void addDuedate() {
		
		duedate = (EditText) findViewById(R.id.duedatep);
		duedate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showTimePickerDialog(v, duedate);
				showDatePickerDialog(v);

			}
		});
	}
	public void addTaskNameListener(){
		taskName=(EditText)findViewById(R.id.taskname);
		taskName.setOnEditorActionListener(new EditText.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			    if (actionId == EditorInfo.IME_ACTION_SEARCH ||
			            actionId == EditorInfo.IME_ACTION_DONE ||
			            event.getAction() == KeyEvent.ACTION_DOWN &&
			            event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
			        if (!event.isShiftPressed()) {
			           // the user is done typing. 
			        	task.setTaskName(taskName.getText().toString());
			           return true; // consume.
			        }                
			    }
			    return false; // pass on to other listeners. 
			}
			});
		
	}
	public void openDB() {
		myDB = new DBAdapter(this);
		myDB.open();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		closeDB();
	}

	public void closeDB() {
		myDB.close();
	}

	public void addChangeList() {
		select_list = (Spinner) findViewById(R.id.select_list);
		List<String> list = new ArrayList<String>();
		list.add("To Do");
		list.add("Doing");
		list.add("Done");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		select_list.setAdapter(dataAdapter);
		
		select_list.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
		        // An item was selected. You can retrieve the selected item using
		        // parent.getItemAtPosition(pos)
		    }

		    public void onNothingSelected(AdapterView<?> parent) {
		        // Another interface callback
		    }
		});
	}

	public void addChangeRepeat() {
		select_repeat = (Spinner) findViewById(R.id.repeat);
		List<String> list = new ArrayList<String>();
		list.add("None");
		list.add("Every day");
		list.add("Every Week");
		list.add("Every Month");
		list.add("Every Year");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		select_repeat.setAdapter(dataAdapter);
	}

	public void onClick_AddTask(View v) {
		
		//myDB.insertRow(taskName, tPrior, tList, tOrder, tDuedate, tReminder, tRepeat, tGoal);
	}

	public void onClick_ClearTask(View v) {
		TextView taskname=(TextView)findViewById(R.id.taskname);
		RadioGroup prior=(RadioGroup)findViewById(R.id.priority);
		Spinner tList = select_list;
		Spinner tRepeat=select_repeat;
		//tOrder
		EditText tDuedate=duedate;
		EditText tReminder=reminder;
		EditText tGoal=(EditText)findViewById(R.id.goal);
		
		taskname.setText("");
		prior.clearCheck();
		tList.setSelection(0);
		tDuedate.setText("");
		tReminder.setText("");
		tRepeat.setSelection(0);
		tGoal.setText("");
		Intent goToMain = new Intent(this,MainActivity.class);
		startActivity(goToMain);
			
		
		
	}

	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// use current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);

		}

		@Override
		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
			DateFormatSymbols dfs = new DateFormatSymbols();
			String[] months = dfs.getShortMonths();
			date = day + "/" + months[month] + "/" + year;

		}
	}

	@SuppressLint("ValidFragment")
	public static class TimerPickerFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {
		private EditText w;

		public TimerPickerFragment(EditText w) {
			super();
			this.w = w;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);

			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
					DateFormat.is24HourFormat(getActivity()));
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// Do something with the time chosen by the user
			w.setText(date + "-" + hourOfDay + ":" + minute);
		}
	}

	public void showTimePickerDialog(View v, EditText w) {
		DialogFragment newFragment = new TimerPickerFragment(w);
		newFragment.show(getSupportFragmentManager(), "timePicker");

	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getSupportFragmentManager(), "datePicker");
	}
}