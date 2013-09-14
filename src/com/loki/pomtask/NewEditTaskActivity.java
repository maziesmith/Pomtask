package com.loki.pomtask;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pomtask.R;

public class NewEditTaskActivity extends Activity{
	private Spinner select_list;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_edit_task);
		addChangeList();
		
	}
	public void addChangeList(){
		select_list=(Spinner)findViewById(R.id.select_list);
		List list =new ArrayList();
		list.add("To Do");
		list.add("Doing");
		list.add("Done");
		ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		select_list.setAdapter(dataAdapter);
	}
}