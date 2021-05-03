package dtu.scheduler;

public class Project {
	private String ProjectID;
	private Worker projectLeader;

	public Project(String ID) {
		this.ProjectID = ID;
	}

	public Project(String ID, Worker projectLeader) {
		this.ProjectID = ID;
		this.projectLeader = projectLeader;
	}
	
	public String getProjectID() {
		return ProjectID;
	}
}