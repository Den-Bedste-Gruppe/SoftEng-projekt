package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;

// Mads Harder
public class Project {
	private String projectID;
	private Worker projectLeader;
	private List<Activity> activities = new ArrayList<>();
	public Project(String ID) {
		this.projectID = ID;
	}

	public Project(String ID, Worker projectLeader) {
		this.projectID = ID;
		this.projectLeader = projectLeader;
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
	
	public void addActivity(Activity activity) throws Exception {
		if(activityWithNameExists(activity.getName())) {
			throw new Exception("Project with same name already exists in project");
		} else if(activity.getName().isBlank()) {
			throw new IllegalArgumentException("activity cannot be created without name");
		}
		activities.add(activity);
	}
	
	public List<Activity> getActivities() {
		return activities;
	}
}