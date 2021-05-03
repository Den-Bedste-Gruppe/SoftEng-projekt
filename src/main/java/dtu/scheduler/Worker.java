package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;

//Philip Hviid
public class Worker {
    private String workerId;
    private double week_hours;
    private List<AssistRequest> requests;
    private List<Activity> activities;
    
    
	public Worker(String workerId) {
		this.workerId = workerId;
		week_hours = 0;
		this.activities = new ArrayList<>();
		this.requests = new ArrayList<>();
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
		activity.registerHours(hours);
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

}
