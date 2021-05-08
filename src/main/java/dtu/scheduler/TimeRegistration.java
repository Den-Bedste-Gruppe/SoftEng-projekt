//	Author: Kristian Sofus Knudsen

package dtu.scheduler;

import java.time.LocalDate;

public class TimeRegistration extends ActivityRegistration {
	//Code smell, should be defined as Activity field in super class, but gave problems with change hours
	//will try to figure out later -- philip
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
