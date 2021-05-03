// Author: Kristian Sofus Knudsen
package dtu.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Activity {
	private double budgetedTime;
	private Date[] timeframe;
	private String name = "";
	private double hoursSpent;
	private List<TimeRegistration> registrationList = new ArrayList<>();
	
	public Activity() {
		hoursSpent = 0;
	}

	public Activity(String name) {
		hoursSpent = 0;
		this.name = name;
	}
	
	public void setActivityName (String name) {
		this.name = name;
	}

	public void setTimeFrame (Date[] timeframe) {
		this.timeframe = timeframe; 
	}

	public void addHoursSpent (double hoursSpent) {
		this.hoursSpent += hoursSpent;
	}

	public String getName() {
		return name;
	}

	public void setName(String new_name) {
		name = new_name;
	}

	public double getTotalHoursSpent() {
		return hoursSpent;
	}
	
	public void addRegistration(TimeRegistration new_registration) {
		registrationList.add(new_registration);
		hoursSpent += new_registration.getHours();
	}
	public void updateTotalHoursSpent() {
		double sum = 0;
		for (TimeRegistration r : registrationList) {
			sum += r.getHours();
		}
		hoursSpent = sum;
	}
//	public void resetActivity() {
//		this.budgetedTime = (Double) null ;
//		this.timeframe = null ;
//		this.Name = null ;
//		this.hoursSpent = (Double) null; 
//	}
}