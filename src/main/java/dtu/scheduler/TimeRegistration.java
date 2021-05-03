//	Author: Kristian Sofus Knudsen

package dtu.scheduler;

import java.util.Date;

public class TimeRegistration {

	private double hours;
	private Date date;
	private Activity parent;

	public TimeRegistration(double hours, Activity parent) {
		this.parent = parent;
		this.hours = hours;
		parent.addRegistration(this);
	}

	/*
	public TimeRegistration(double hours, Activity parent_activity, Date date) {

	}
	*/

	public double getHours() {
		return hours;
	}
	public void changeHours(double new_hours) {
		hours = new_hours;
		parent.updateTotalHoursSpent();
	}
	public Activity getActivity() {
		return parent;
	}
}
