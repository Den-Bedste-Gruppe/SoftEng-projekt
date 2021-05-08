package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;

// Mads Harder
public class Project {

	private static int running_ID = 1;

	private String projectID;
	private String name;
	private Worker projectLeader;
	private List<ProjectActivity> activities = new ArrayList<>();
	
	// TODO "På oprettelsestidspunktet er information om aktiviteter og starttidspunkt ikke fuldstændige"
	// Maybe timefram should be null
	//private int[] timeframe = new int[2]; //Start and end weeks
	private TimeFrame timeFrame;

	public Project(String name) throws Exception {
		if (name.length() < 2) throw new Exception("Project name must be at least 2 characters");
		this.name = name;
		int currentYear = DateHelper.thisYear();
		int currentWeek = DateHelper.thisWeek();
		projectID = String.format("%d%03d", currentYear, running_ID);
		running_ID++;
		timeFrame = new TimeFrame();
		timeFrame.setTimeFrame(currentYear, currentWeek, currentYear, currentWeek);
	}

	public Project(String name, Worker projectLeader) throws Exception {
		if (name.length() < 2) throw new Exception("Project name must be at least 2 characters");
		this.name = name;
		int currentYear = DateHelper.thisYear();
		int currentWeek = DateHelper.thisWeek();
		this.projectLeader = projectLeader;
		projectID = String.format("%d%03d", DateHelper.thisYear(), running_ID);
		running_ID++;
		timeFrame = new TimeFrame();
		timeFrame.setTimeFrame(currentYear, currentWeek, currentYear, currentWeek);
	}

	public TimeFrame getTimeframe() {
		return timeFrame;
	}

	public void setTimeframe(int startYear, int startWeek, int endYear, int endWeek) throws Exception {
		timeFrame.setTimeFrame(startYear, startWeek, endYear, endWeek);
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
		ProjectActivity activity;
		for(int i = activities.size(); i>0; i--){
			activity = activities.get(i-1);
			if(activity.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	//Emil Krarup
	public ProjectActivity searchActivity(String name) {
		for(int i = 0; i < activities.size(); i++){
			if (activities.get(i).getName().equals(name)) {
				return activities.get(i);
			}
		}
		return null;
	}
	
	public void addActivity(ProjectActivity activity) throws Exception {
		if(activityWithNameExists(activity.getName())) {
			throw new Exception("Project with same name already exists in project");
		} else if(activity.getName().isEmpty()) {
			throw new IllegalArgumentException("activity cannot be created without name");
		}
		activities.add(activity);
	}
	
	public List<ProjectActivity> getActivities() {
		return activities;
	}
	
	public int getNumOfProjectActivities() {
		return activities.size();
	}
	
	public double getProjectHours() {
		double totalHours = 0;
		for (ProjectActivity activity : activities) {
			totalHours += activity.getTotalHoursSpent();
		}
		
		return totalHours;
	}

	public String getName() {
		return name;
	}
	public void setName(String new_name) {
		name = new_name;
	}
}