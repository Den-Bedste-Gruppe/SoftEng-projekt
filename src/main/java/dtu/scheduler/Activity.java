package dtu.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Activity {
	double budgetedTime ;
	Date[] timeframe ;
	String Name ;
	double hoursSpent ;
	
	public Activity() {
		
	}
	public Activity(String Name) {
		this.Name = Name ;
	}
	
	public void setActivityName (String name) {
		this.Name = name ;
	}
	public void setTimeFrame (Date[] timeframe) {
		this.timeframe = timeframe ; 
	}

	public void addHoursSpent (double hoursSpent) {
		this.hoursSpent += hoursSpent ;
	}
	public String getName() {
		return Name;
	}
	
//	public void resetActivity() {
//		this.budgetedTime = (Double) null ;
//		this.timeframe = null ;
//		this.Name = null ;
//		this.hoursSpent = (Double) null; 
//	}
}
