package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;

//Philip Hviid
public class Worker {
    private String workerId;
    private double week_hours;

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

	public void registerHours(double hours, Activity activity) throws Exception {
		// By Kristian Sofus Knudsen
		if (hours <= 0 || hours > 24) {
			throw new Exception("Invalid amount of hours");
		}
		week_hours += hours;
		//adds itself to the activity registration-list automatically in constructor
		TimeRegistration new_registration = new TimeRegistration(hours, activity);
		//manually add to worker registration-list
		registrationList.add(new_registration);
	}

	public TimeRegistration getTimeRegistrationByActivity(Activity activity) throws Exception {
		for (TimeRegistration r : registrationList) {
			if (r.getActivity().equals(activity)) {
				return r;
			}
		}
		throw new Exception("No registration found for given activity: " + activity.getName());
	}

}
