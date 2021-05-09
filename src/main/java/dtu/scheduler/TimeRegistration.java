//	Author: Kristian Sofus Knudsen

package dtu.scheduler;

import java.time.LocalDate;

public class TimeRegistration extends ActivityRegistration {
	//Code smell, should be defined as Activity field in super class, but gave problems with change hours
	//will try to figure out later -- philip
	private ProjectActivity parentActivity;
	private double hours;
	private LocalDate date;
	
	public TimeRegistration(double hours, ProjectActivity parentActivity, Worker parentWorker) throws Exception {
		super(parentWorker);
		if (hours <= 0 || hours > 24) {
			throw new Exception("Invalid amount of hours");
		}
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
		getParentWorker().addTimeRegistration(this);
		getParentWorker().updateWeeklyHoursSpent(hours);
	}

	@Override
	Activity getParentActivity() {
		return parentActivity;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
