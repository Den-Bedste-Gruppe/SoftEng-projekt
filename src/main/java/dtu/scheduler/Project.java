package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;

// Mads Harder
public class Project {

	private static int running_ID = 1;

	private String projectID;
	private String name;
	private Worker projectLeader = null;
	private List<ProjectActivity> activities = new ArrayList<>();
	
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

		projectID = String.format("%d%03d", DateHelper.thisYear(), running_ID); //Automatic ID
		running_ID++;
		
		timeFrame = new TimeFrame();
		timeFrame.setTimeFrame(currentYear, currentWeek, currentYear, currentWeek);
	}

	public TimeFrame getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(int startYear, int startWeek, int endYear, int endWeek) throws Exception {
		if (DateHelper.isEndDateBeforeStartDate(startYear, startWeek, endYear, endWeek)) {
			throw new Exception("The end date before the start date");
		}
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
	
	public boolean activityWithNameExists(String name) {
		ProjectActivity activity;
		for (int i = 0; i < activities.size(); i++){
			activity = activities.get(i);
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
	
	public void addActivity(ProjectActivity activity) throws IllegalArgumentException, Exception {
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