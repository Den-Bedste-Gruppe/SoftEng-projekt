// Author: Kristian Sofus Knudsen
//
// Simple implementation to pass the tests in which it is involved

package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;

public class Activity {
	//doubles default to 0, no need to set it
	private double hoursSpent;
	private String name;
	private List<TimeRegistration> registrationList = new ArrayList<>();
	private TimeFrame timeFrame = new TimeFrame(); //Start and end weeks
	
	public Activity(String name) {
		this.name = name;

	}

	public Activity(String name, int startWeek, int endWeek) {
		this.name = name;
		
	}
	
	public void setTimeFrame(int startWeek, int endWeek) throws Exception {
		timeFrame.setTimeframe(startWeek, endWeek);
	}

	public int[] getTimeframe() {
		return timeFrame.getTimeFrame();
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