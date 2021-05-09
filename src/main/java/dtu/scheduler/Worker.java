package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;

// Mads Harder
public class Worker {
	private String workerId;
	private double week_hours; //Should not be set by any other function than updateWeeklyHoursSpent
	private List<TimeRegistration> registrationList = new ArrayList<>();
	private List<NonProjectRegistration> nonprojectregistrationList = new ArrayList<>();
	private List<AssistRequest> requests;
	private List<ProjectActivity> activities;
	private List<NonProjectActivity> nonProjectActivies;
 
	public Worker(String workerId) {
		// Input validation checking
		if (workerId.length() == 0) throw new IllegalArgumentException("The ID must be at least one character");
		if (workerId.length() > 4) throw new IllegalArgumentException("The ID can max be 4 characters");
		
		this.workerId = workerId.toUpperCase();
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



	public void changeHours(double new_hours, ProjectActivity activity) throws Exception {
		// By Kristian Sofus Knudsen
		TimeRegistration registration = getTimeRegistrationByActivity(activity);
		if (new_hours <= 0 || new_hours > 24) {
			throw new Exception("Invalid amount of hours");
		}
		double old_hours = registration.getHours();
		registration.changeHours(new_hours);
		updateWeeklyHoursSpent(new_hours-old_hours);
	}

	public TimeRegistration getTimeRegistrationByActivity(ProjectActivity activity) throws Exception {
		// By Kristian Sofus Knudsen
		for (TimeRegistration r : registrationList) {
			if (r.getParentActivity().equals(activity)) {
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

	public List<ProjectActivity> getActivities() {
		return activities;
	}
	
	public void addActivity(ProjectActivity activity) throws Exception {
		for (Activity a : activities) {
			if (a.equals(activity)) {
				throw new Exception("Activity already assigned");
			}
		}
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

	public List<NonProjectActivity> getNonProjectActivies() {
		return nonProjectActivies;
	}
	
	//Phili Hviid
	//for finding how many activities a worker has 
	public int[] activitiesInTimeFrame(TimeFrame timeFrame) {
		int projectActivityOverlaps = 0;
		int nonProjectActivityOverlaps = 0;
		int[] overlaps = {projectActivityOverlaps, nonProjectActivityOverlaps};
		for(ProjectActivity activity : activities) {
			if(timeFrame.hasOverlap(activity.getTimeframe())){
				projectActivityOverlaps++;
			}
		}
		for(NonProjectActivity activity : nonProjectActivies) {
			if(timeFrame.hasOverlap(activity.getTimeframe())){
				nonProjectActivityOverlaps++;
			}
		}
		return overlaps;
	}
	
}
