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

	public void test_SetHours(double new_hours) {
		hoursSpent = new_hours;
	}
}
