package com.loki.pomtask;

import java.util.Date;

public class Task {
	private String taskName;
	private String list;
	private String prior;
	private Date duedate;
	private Date reminder;
	private String repeat;
	private int goal;
	
	public Task(){
		super();
		this.taskName="";
		this.prior="";
		this.list="";
		this.duedate=new Date();
		this.reminder=new Date();
		this.repeat="";
		this.goal=0;

	}
	public Task(String taskName,String prior,String list,Date duedate,Date reminder,String repeat,int goal){
		this.taskName=taskName;
		this.prior=prior;
		this.list=list;
		this.duedate=duedate;
		this.reminder=reminder;
		this.repeat=repeat;
		this.goal=goal;
		
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	

	public String getPrior() {
		return prior;
	}

	public void setPrior(String prior) {
		this.prior = prior;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}
	
	
}
