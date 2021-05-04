package dtu.scheduler;

// Mads Harder
public class Project {
	private String projectID;
	private Worker projectLeader;

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
}