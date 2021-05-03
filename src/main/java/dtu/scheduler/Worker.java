package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;

//Philip Hviid
public class Worker {
    private String workerId;
    private double week_hours; //Should not be set by any other function than updateWeeklyHoursSpent

	private List<TimeRegistration> registrationList = new ArrayList<>();
    
	public Worker(String workerId) {
		this.workerId = workerId;
		week_hours = 0;
	}
	
	public String getWorkerId() {
		return workerId;
	}
	public double getWeeklyRegisteredHours() {
		return week_hours;
	}

	public void updateWeeklyHoursSpent() {
		double sum = 0;
		for (TimeRegistration r : registrationList) {
			sum += r.getHours();
		}
		week_hours = sum;
	}

	public void registerHours(double hours, Activity activity) throws Exception {
		// By Kristian Sofus Knudsen
		if (hours <= 0 || hours > 24) {
			throw new Exception("Invalid amount of hours");
		}
		//adds itself to the activity registration-list automatically in constructor
		TimeRegistration new_registration = new TimeRegistration(hours, activity, workerId);
		//manually add to worker registration-list
		registrationList.add(new_registration);
		updateWeeklyHoursSpent();
	}

	public void changeHours(double new_hours, Activity activity) throws Exception {
		// By Kristian Sofus Knudsen
		TimeRegistration registration = getTimeRegistrationByActivity(activity);
		if (new_hours <= 0 || new_hours > 24) {
			throw new Exception("Invalid amount of hours");
		}
		registration.changeHours(new_hours);
		updateWeeklyHoursSpent();
	}

	public TimeRegistration getTimeRegistrationByActivity(Activity activity) throws Exception {
		// By Kristian Sofus Knudsen
		for (TimeRegistration r : registrationList) {
			if (r.getActivity().equals(activity)) {
				return r;
			}
		}
		throw new Exception("No registration found for given activity: " + activity.getName());
	}

}
