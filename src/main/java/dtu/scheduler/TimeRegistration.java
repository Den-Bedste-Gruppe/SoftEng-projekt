//	Author: Kristian Sofus Knudsen

package dtu.scheduler;

import java.util.Date;

public class TimeRegistration {

	private double hours;
	private Date date;
	private ProjectActivity parent_activity;
	private String parent_worker_id;

	public TimeRegistration(double hours, ProjectActivity parent_activity, String parent_worker_id) {
		this.parent_activity = parent_activity;
		this.hours = hours;
		this.parent_worker_id = parent_worker_id;
		parent_activity.addRegistration(this);
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
		parent_activity.updateTotalHoursSpent();
	}
	public ProjectActivity getActivity() {
		return parent_activity;
	}
}
