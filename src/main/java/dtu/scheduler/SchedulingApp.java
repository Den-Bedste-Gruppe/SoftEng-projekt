package dtu.scheduler;

import java.util.List;
import java.util.ArrayList;

//Philip Hviid
public class SchedulingApp {
	private Worker currentUser;
	private WorkerDAO workerDAO;
	private List<Project> projectArray = new ArrayList<>();
	private ActivityAssigner activityAssigner;
	private AssistRequestHandler requestHandler;

	public SchedulingApp(WorkerDAO workerDAO, ActivityAssigner activityAssigner, AssistRequestHandler requestHandler) {
		this.workerDAO = workerDAO;
		this.activityAssigner = activityAssigner;
		this.requestHandler = requestHandler;
	}
	
	public void logIn(String workerId) throws WorkerDoesNotExistException{
	    currentUser = workerDAO.getWorkerById(workerId);		
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
	
	public void assignActivity(String workerId, Activity activity) throws WorkerDoesNotExistException {
		activityAssigner.assignActivity(getWorkerById(workerId), activity);
	}
	
	public double getWeeklyRegisteredHours() {
		return currentUser.getWeeklyRegisteredHours();
	}
	
	public void addProject(Project project) throws ProjectAlreadyExistsException {
		// Test if project already exists
		if (searchProject(project.getProjectID()) != null) {
			throw new ProjectAlreadyExistsException("Project already exist");
		}
		
		projectArray.add(project);
	}
	
	public Project searchProject(String ID) {
		ProjectSearch projectSearcher = new ProjectSearch(projectArray);
		return projectSearcher.search(ID);
	}
	
	public  List<Project> getProjects() {
		return projectArray;
	}

	public double getHoursRegisteredOnActivity(Activity activity) throws Exception {
		TimeRegistration registration = currentUser.getTimeRegistrationByActivity(activity);
		return registration.getHours();
	}

	public void registerHours(double hours, Activity test_activity) throws Exception {
		currentUser.registerHours(hours, test_activity);
	}

	public void assingProjectLeader(String projectID, Worker projectLeader) {
		searchProject(projectID).assignLeader(projectLeader);
	}
  
	private Worker getWorkerById(String workerId) throws WorkerDoesNotExistException {
		return workerDAO.getWorkerById(workerId);
	}

	public void requestAssistance(Activity activity, String targetWorkerId) throws WorkerDoesNotExistException {
		AssistRequest newRequest = new AssistRequest(currentUser.getWorkerId(), activity);
		requestHandler.deliverRequest(newRequest, getWorkerById(targetWorkerId));
		
	}
	
	public List<AssistRequest> getWorkerRequests(String workerId) throws WorkerDoesNotExistException {
		return getWorkerById(workerId).getRequests();
	}
		
	public void changeHoursOnActivity(double new_hours, Activity activity) throws Exception {
		currentUser.changeHours(new_hours, activity);

	}
}
