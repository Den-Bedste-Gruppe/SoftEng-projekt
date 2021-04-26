// Author: Kristian Sofus Knudsen
//
// Simple implementation to pass the tests in which it is involved

package dtu.scheduler;

public class Activity {

	private double hoursSpent;
	
	public Activity() {
		hoursSpent = 0;
	}

	public void registerHours(double hours) {
		hoursSpent += hours;
	}

	public double getHoursSpent() {
		return hoursSpent;
	}
}
