// Author: Kristian Sofus Knudsen
//
// Simple implementation to pass the tests in which it is involved

package dtu.scheduler;

public class Activity {

	private double hoursSpent;
	private String name = "";
	
	public Activity() {
		hoursSpent = 0;
	}
	public Activity(String name) {
		hoursSpent = 0;
		this.name = name;
	}

	public void registerHours(double hours) {
		hoursSpent += hours;
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
}
