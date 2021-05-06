package dtu.scheduler;

import java.util.Date;
//Philip Hviid
public class NonProjectRegistration {
	private double hours;
	private Date date;
	private NonProjectActivity parent_activity;
	private String parent_worker_id;

	public NonProjectRegistration(NonProjectActivity parent_activity, String parent_worker_id) {
		this.parent_activity = parent_activity;
		this.parent_worker_id = parent_worker_id;
	}

	/*
	public TimeRegistration(double hours, Activity parent_activity, Date date) {

	}
	*/

	public double getHours() {
		return hours;
	}

	public NonProjectActivity getActivity() {
		return parent_activity;
	}
}
