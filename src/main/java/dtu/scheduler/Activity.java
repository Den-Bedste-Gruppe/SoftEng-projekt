// Author: Kristian Sofus Knudsen
//
// Simple implementation to pass the tests in which it is involved

package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;

public class Activity {

	private double hoursSpent;
	private String name = "";
	private List<TimeRegistration> registrationList = new ArrayList<>();
	private int[] timeframe = new int[2]; //Start and end weeks
	
	public Activity(String name) {
		hoursSpent = 0;
		this.name = name;

		timeframe[0] = DateHelper.thisWeek();
		timeframe[1] = DateHelper.thisWeek();
	}

	public Activity(String name, int startWeek, int endWeek) {
		hoursSpent = 0;
		this.name = name;

		timeframe[0] = startWeek;
		timeframe[1] = endWeek;
	}

	public int[] getTimeframe() {
		return timeframe;
	}
	//Emil Krarup
	public void setTimeframe(int startWeek, int endWeek) throws Exception {
		if(startWeek < 1 || startWeek > 52) throw new Exception("The given date is not eligible to set time frame");
		if(endWeek < 1 || endWeek > 52) throw new Exception("The given date is not eligible to set time frame");
		timeframe[0] = startWeek;
		timeframe[1] = endWeek;
	}

	public double getTotalHoursSpent() {
		return hoursSpent;
	}

	public String getName() {
		return name;
	}
	public void setName(String new_name) {
		name = new_name;
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
	
}