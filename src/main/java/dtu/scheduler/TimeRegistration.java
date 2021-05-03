//	Author: Kristian Sofus Knudsen
//
//	This is unused as of right now, will be put to use once we start taking different dates and weeks into account

package dtu.scheduler;

import java.util.Date;

public class TimeRegistration {

	private double hours;
	private Date date;
	private Activity parent;

	public TimeRegistration(double hours, Activity parent_activity) {

	}

	public TimeRegistration(double hours, Activity parent_activity, Date date) {

	}

	public double getHours() {
		return hours;
	}
	public Activity getActivity() {
		return parent;
	}

}
