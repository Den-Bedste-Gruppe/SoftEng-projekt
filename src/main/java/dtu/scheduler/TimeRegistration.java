//	Author: Kristian Sofus Knudsen

package dtu.scheduler;

import java.time.LocalDate;

public class TimeRegistration extends ActivityRegistration {
	// TODO
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
	}

	public double getHours() {
		return hours;
	}
	public void changeHours(double new_hours) throws Exception {
		if (new_hours <= 0 || new_hours > 24) {
			throw new Exception("Invalid amount of hours");
		}
		hours = new_hours;
		parentActivity.updateTotalHoursSpent();
		getParentWorker().updateWeeklyHoursSpent();
	}
	
	public void register() {
		parentActivity.addRegistration(this);
		getParentWorker().addTimeRegistration(this);
		getParentWorker().updateWeeklyHoursSpent();
	}

	@Override
	Activity getParentActivity() {
		return parentActivity;
	}

	public int getWeek() {
		return DateHelper.getWeekFromDate(date);
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		String s = date.toString();
		s += " " + parentActivity.getName();
		s += " Hours: " + hours;
		return s;
	}

}
