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

	private int[] weeks = new int[2];
	
	public Activity(String name) {
		hoursSpent = 0;
		this.name = name;

		int thisWeek = DateHelper.thisWeek();

		weeks[0] = thisWeek;
		weeks[1] = thisWeek;
	}

	/*
	public void registerHours(double hours) {
		hoursSpent += hours;
	}
	*/

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