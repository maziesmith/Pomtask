package com.loki.pomtask;

import java.util.ArrayList;
import java.util.List;

import com.example.pomtask.R;
import com.loki.pomtask.TimerActivity.MyCountDownTimer;

import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class SetTime extends Activity implements OnClickListener {
	
	private Spinner select_time;
	
	
	//-----------------------menu --------------------
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
	//----------------------- end menu --------------------
	

	

	private CountDownTimer countDownTimer;
	private boolean timerHasStarted = false;
	private Button startB;
	public TextView text;
	long startTime = 10*1000;
	private final long interval = 1 * 1000;
	private Time time = new Time();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.settime);
		super.onCreate(savedInstanceState);
		addChangeTime();
		

		startB = (Button) this.findViewById(R.id.button);
		startB.setOnClickListener(this);
		text = (TextView) this.findViewById(R.id.timer);
		
		
		
		
		//text.setText(text.getText() + String.valueOf(time.getStartTime() / 1000));
		
		
	}

	@Override
	public void onClick(View v) {
		if (!timerHasStarted) {
			countDownTimer.start();
			timerHasStarted = true;
			startB.setText("STOP");
		} else {
			countDownTimer.cancel();
			timerHasStarted = false;
			startB.setText("RESTART");
		}
	}

	public void addChangeTime() {
		select_time = (Spinner) findViewById(R.id.settime);
		List<String> list = new ArrayList<String>();
		list.add("10 minute");
		list.add("15 minute");
		list.add("20 minute");
		list.add("25 minute");
		list.add("30 minute");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		select_time.setAdapter(dataAdapter);
		
		
		select_time.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
					if(pos==0){  time.setStartTime(10*60*1000); countDownTimer = new MyCountDownTimer(time.getStartTime(), interval);}
					if(pos==1){ time.setStartTime(15*60*1000); countDownTimer = new MyCountDownTimer(time.getStartTime(), interval);}
					if(pos==2){ time.setStartTime(20*60*1000); countDownTimer = new MyCountDownTimer(time.getStartTime(), interval);}
					if(pos==3){ time.setStartTime(25*60*1000); countDownTimer = new MyCountDownTimer(time.getStartTime(), interval);}
					if(pos==4) {time.setStartTime(30*60*1000); countDownTimer = new MyCountDownTimer(time.getStartTime(), interval);}
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}
	
	
	
	
	
	public class MyCountDownTimer extends CountDownTimer {
		public MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		@Override
		public void onFinish() {
			text.setText("Time's up!");
		}

		@Override
		public void onTick(long millisUntilFinished) {
			text.setText("seconds remaining: " + millisUntilFinished / 1000);
		}
	}
}