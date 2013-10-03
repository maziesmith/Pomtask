package com.loki.pomtask;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pomtask.R;

@SuppressLint("ValidFragment")
public class NewEditTaskActivity extends FragmentActivity {
	private EditText taskName;
	private RadioGroup priorG;
	private Spinner select_list;
	private Spinner select_repeat;
	private EditText duedate;
	private EditText reminder;
	private static String date;
	private DBAdapter myDB;
	private Task task;
	private Calendar due;
	private Calendar remin;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_edit_task);
		task = new Task();
		openDB();
		addChangeList();
		addDuedate();
		addReminder();
		addChangeRepeat();
		//Date date = new Date(datePicker.getYear() - 1900, datePicker.getMonth(), datePicker.getDayOfMonth());
	}

	public void addReminder() {
		reminder = (EditText) findViewById(R.id.reminderp);
		remin=Calendar.getInstance();
		reminder.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				showTimePickerDialog(v, reminder,remin);
				showDatePickerDialog(v);

			}
		});

	}

	public void addDuedate() {

		duedate = (EditText) findViewById(R.id.duedatep);
		due=Calendar.getInstance();
		duedate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showTimePickerDialog(v, duedate,due);
				showDatePickerDialog(v);
				
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
		final List<String> list = new ArrayList<String>();
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
				task.setList(String.valueOf(list.get(pos)));

			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		
	}

	public void addChangeRepeat() {
		select_repeat = (Spinner) findViewById(R.id.repeat);
		final List<String> list = new ArrayList<String>();
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
		select_repeat.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				task.setRepeat(String.valueOf(list.get(pos)));

			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		
	}

	public void onClick_AddTask(View v) throws ParseException {
		priorG = (RadioGroup) findViewById(R.id.priority);
		int selectedPid = priorG.getCheckedRadioButtonId();
		RadioButton prior = (RadioButton) findViewById(selectedPid);
		taskName = (EditText) findViewById(R.id.taskname);
		task.setTaskName(taskName.getText().toString());
		task.setPrior(prior.getText().toString());
		task.setDuedate(due.getTime());
		task.setReminder(remin.getTime());
		task.setGoal(Integer.parseInt((((EditText)findViewById(R.id.goal)).getText().toString())));
		Toast.makeText(getApplicationContext(),task.getDuedate().toString(),
				Toast.LENGTH_LONG).show();
		
		myDB.insertRow(task.getTaskName(), task.getPrior(), task.getList(), task.getDuedate().toString(), task.getReminder().toString(), task.getRepeat(), task.getGoal());
		Intent mainact = new Intent(this, TaskList.class);
		
		startActivity(mainact);
	}

	private Date parseDate(EditText w) throws ParseException {
		SimpleDateFormat df=new SimpleDateFormat("d/MMM/yyyy-HH:mm");
		Date result=df.parse(w.getText().toString());
		return result;
	}

	public void onClick_ClearTask(View v) {
		TextView taskname = (TextView) findViewById(R.id.taskname);
		RadioGroup prior = (RadioGroup) findViewById(R.id.priority);
		Spinner tList = select_list;
		Spinner tRepeat = select_repeat;
		EditText tDuedate = duedate;
		EditText tReminder = reminder;
		EditText tGoal = (EditText) findViewById(R.id.goal);

		taskname.setText("");
		prior.clearCheck();
		tList.setSelection(0);
		tDuedate.setText("");
		tReminder.setText("");
		tRepeat.setSelection(0);
		tGoal.setText("");
		Intent goToMain = new Intent(this, MainActivity.class);
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
		private Calendar d;
		
		public TimerPickerFragment(EditText w,Calendar d) {
			super();
			this.w = w;
			this.d=d;
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
			int pday=date.indexOf("/");
			int pmonth=date.indexOf("/", pday+1);
			int day=Integer.parseInt(date.substring(0, pday));
			int month=StrToNum(date.substring(pday+1,pmonth));
			int year=Integer.parseInt(date.substring(pmonth+1, date.length()));
			d.set(year, month, day, hourOfDay, minute, 00);
		}
		public int StrToNum(String d){
			int result=0;
			if(d.equals("Jan"))result=0;
			else if(d.equals("Feb"))result=1;
			else if(d.equals("Mar"))result=2;
			else if(d.equals("Apr"))result=3;
			else if(d.equals("May"))result=4;
			else if(d.equals("Jun"))result=5;
			else if(d.equals("Jul"))result=6;
			else if(d.equals("Aug"))result=7;
			else if(d.equals("Sep"))result=8;
			else if(d.equals("Oct"))result=9;
			else if(d.equals("Nov"))result=10;
			else result=11;
			
			
			return result;
		}
	}

	public void showTimePickerDialog(View v, EditText w,Calendar d) {
		DialogFragment newFragment = new TimerPickerFragment(w,d);
		newFragment.show(getSupportFragmentManager(), "timePicker");

	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getSupportFragmentManager(), "datePicker");
	}
}