package com.loki.pomtask;

public class Time {
	private long startTime;
	private long time;
	private long minute;
	private long second;
	private int count;
	
	
	
	public Time(){
		startTime = 10*60*1000;
		time =startTime;
		minute = (time/1000)%60;
		second = (time/1000)-(time/1000)%60;
		count =0;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	
	public long getSecond() {
		return second;
	}
	public void setSecond(long second) {
		this.second = second;
	}
	public long getStartTime() {
		return startTime;
	}
	public void  setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public long getMinute() {
		return minute;
	}
	public void setMinute(long minute) {
		this.minute = minute;
	} 
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
