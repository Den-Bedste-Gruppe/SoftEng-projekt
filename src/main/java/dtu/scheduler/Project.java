package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;

// Mads Harder
public class Project {
	private String projectID;
	private Worker projectLeader;
	private List<Activity> activities = new ArrayList<>();
	
	// TODO "På oprettelsestidspunktet er information om aktiviteter og starttidspunkt ikke fuldstændige"
	// Maybe timefram should be null
	private int[] timeframe = new int[2]; //Start and end weeks

	public Project(String ID) {
		projectID = ID;
		timeframe[0] = DateHelper.thisWeek();
		timeframe[1] = DateHelper.thisWeek();
	}

	public Project(String ID, Worker projectLeader) {
		projectID = ID;
		this.projectLeader = projectLeader;
		timeframe[0] = DateHelper.thisWeek();
		timeframe[1] = DateHelper.thisWeek();
	}

	public int[] getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(int startWeek, int endWeek) {
		timeframe[0] = startWeek;
		timeframe[1] = endWeek;
	}
	
	public String getProjectID() {
		return projectID;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Project ID: " + projectID;
	}

	public void assignLeader(Worker projectLeader) {
		this.projectLeader = projectLeader;
	}
	
	public Worker getProjectLeader() {
		return projectLeader;
	}
	
	private boolean activityWithNameExists(String name) {
		Activity activity;
		for(int i = activities.size(); i>0; i--){
			activity = activities.get(i-1);
			if(activity.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	//Emil Krarup
	public Activity searchActivity(String name) {
		for(int i = 0; i < activities.size(); i++){
			if (activities.get(i).getName().equals(name)) {
				return activities.get(i);
			}
		}
		return null;
	}
	
	public void addActivity(Activity activity) throws Exception {
		if(activityWithNameExists(activity.getName())) {
			throw new Exception("Project with same name already exists in project");
		} else if(activity.getName().isEmpty()) {
			throw new IllegalArgumentException("activity cannot be created without name");
		}
		activities.add(activity);
	}
	
	public List<Activity> getActivities() {
		return activities;
	}
	
	public int getNumOfProjectActivities() {
		return activities.size();
	}
	
	public double getProjectHours() {
		double totalHours = 0;
		for (Activity activity : activities) {
			totalHours += activity.getTotalHoursSpent();
		}
		
		return totalHours;
	}
}