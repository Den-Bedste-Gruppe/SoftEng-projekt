//	Author: Kristian Sofus Knudsen

package dtu.scheduler;

import java.time.LocalDate;

public class TimeRegistration extends ActivityRegistration {
	private ProjectActivity parentActivity;
	private double hours;
	private LocalDate date;
	
	public TimeRegistration(double hours, ProjectActivity parentActivity, String parentWorkerId) {
		super(parentWorkerId);
		this.parentActivity = parentActivity;
		date = DateHelper.today();
		this.hours = hours;
		parentActivity.addRegistration(this);
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
		parentActivity.updateTotalHoursSpent();
	}
	
	public void register() {
		//TODO redelegate registration functionality here
	}

	@Override
	Activity getParentActivity() {
		return parentActivity;
	}

}
