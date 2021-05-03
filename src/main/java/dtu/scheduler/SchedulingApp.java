package dtu.scheduler;

import java.util.List;
import java.util.ArrayList;

public class SchedulingApp {
	private Worker currentUser;
	private WorkerDAO workerDAO = new WorkerDAO();
	private List<Project> projectArray = new ArrayList<>();
	
	public SchedulingApp() {
	}
	
	public void logIn(String workerId) throws WorkerDoesNotExistException{
	    currentUser = workerDAO.getWorkerbyId(workerId);		
	}
	
	public String getCurrentUserID() {
		if(isUserLoggedIn()) {
			return currentUser.getWorkerId();
		}
		return null;
	}

	public Worker getCurrentUser() {
		if(isUserLoggedIn()) {
			return currentUser;
		}
		return null;
	}
	
	public Boolean isUserLoggedIn() {
		return currentUser != null;
	}

	public void logOut() {
		currentUser = null;
	}
	
	public boolean isUserInDatabase(String workerId) {
		return workerDAO.isUserInDatabase(workerId);
	}
	
	public double getWeeklyRegisteredHours() {
		return currentUser.getWeeklyRegisteredHours();
	}
	
	public void addProject(Project project) throws ProjectAlreadyExistException {
		// Test if project already exists
		if (searchProject(project.getProjectID()) != null) {
			throw new ProjectAlreadyExistException("Project already exist");
		}
		
		projectArray.add(project);
	}
	
	public Project searchProject(String ID) {
		for (int i = 0; i < projectArray.size(); i++) {
			Project p = projectArray.get(i);
			if (p.getProjectID().equals(ID)) {
				return p;
			}
		}
		return null;
	};
	
	public  List<Project> getProjects() {
		return projectArray;
	}

	public void registerHours(double hours, Activity test_activity) throws Exception {
		currentUser.registerHours(hours, test_activity);
	}

	public void assingProjectLeader(String projectID, Worker projectLeader) {
		searchProject(projectID).assignLeader(projectLeader);
	}
}
