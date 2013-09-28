package com.loki.pomtask;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.pomtask.R;

public class NewEditTaskActivity extends FragmentActivity {
	private Spinner select_list;
	private static EditText dateEdit;
	private static String date;
	private static String time;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_edit_task);
		dateEdit = (EditText) findViewById(R.id.duedatep);
        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	showTimePickerDialog(v);
            	showDatePickerDialog(v);
           
            }
        });
		addChangeList();

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
	}
	
	 
	public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState){
			//use current date as the default date in the picker
			final Calendar c=Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month=c.get(Calendar.MONTH);
			int day=c.get(Calendar.DAY_OF_MONTH);
			
			//create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(),this,year,month,day);
			
		}

		@Override
		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
			DateFormatSymbols dfs=new DateFormatSymbols();
			String[] months=dfs.getShortMonths();
			date=day+"/"+months[month]+"/"+year;
			
		}
	}
	
	public static class TimerPickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
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
	    	dateEdit.setText(date + "-" + hourOfDay + ":"    + minute);
        }
	}
	public void showTimePickerDialog(View v){
		DialogFragment newFragment=new TimerPickerFragment();
		newFragment.show(getSupportFragmentManager(), "timePicker");
		
	}
	public void showDatePickerDialog(View v){
		DialogFragment newFragment=new DatePickerFragment();
		newFragment.show(getSupportFragmentManager(),"datePicker");
	}
}