package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import dtu.errors.TooManyActivitiesException;

// By Mads Harder
public class Worker {
	private String workerId;
	private double week_hours; //Should not be set by any other function than updateWeeklyHoursSpent
	private List<TimeRegistration> registrationList = new ArrayList<>();
	private List<NonProjectRegistration> nonprojectregistrationList = new ArrayList<>();
	private List<AssistRequest> requests;
	private List<Activity> activities;
	private List<NonProjectActivity> nonProjectActivies;
 
	public Worker(String workerId) {
		// Input validation checking
		if (workerId.length() == 0) throw new IllegalArgumentException("The ID must be at least one character");
		if (workerId.length() > 4) throw new IllegalArgumentException("The ID can max be 4 characters");
		
		workerId = workerId.toUpperCase();

		this.workerId = workerId;
		week_hours = 0;
		this.activities = new ArrayList<>();
		this.requests = new ArrayList<>();
		this.nonProjectActivies = new ArrayList<>();
	}
	
	public String getWorkerId() {
		return workerId;
	}

	public double getWeeklyRegisteredHours() {
		return week_hours;
	}

	public void updateWeeklyHoursSpent(double i) {
		week_hours += i;
	}



	public void changeHours(double new_hours, Activity activity) throws Exception {
		// By Kristian Sofus Knudsen
		TimeRegistration registration = getTimeRegistrationByActivity(activity);
		if (new_hours <= 0 || new_hours > 24) {
			throw new Exception("Invalid amount of hours");
		}
		double old_hours = registration.getHours();
		registration.changeHours(new_hours);
		updateWeeklyHoursSpent(new_hours-old_hours);
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return workerId;
	}

	public List<Activity> getActivities() {
		return activities;
	}
	
	public void addActivity(Activity activity) {
		activities.add(activity);
	}
	
	public void addRequest(AssistRequest request) {
		requests.add(request);
	}
	
	public List<AssistRequest> getRequests() {
		return requests;
	}
	
	public void addTimeRegistration(TimeRegistration timeRegistration) {
		registrationList.add(timeRegistration);
	}

	public void addNonProjectActivity(NonProjectActivity nonProjectActivity) {
		nonProjectActivies.add(nonProjectActivity);
		
	}

	public boolean hasNonProjectActivity(NonProjectActivity nonProjectActivity) {
		return(nonProjectActivies.contains(nonProjectActivity));
	}

	public void addNonProjectRegistration(NonProjectRegistration new_registration) {
		nonprojectregistrationList.add(new_registration);
	}
	
	public List<NonProjectRegistration> getNonProjectRegistrations() {
		return nonprojectregistrationList;
	}
}
