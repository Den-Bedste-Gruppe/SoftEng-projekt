package dtu.scheduler;

import java.util.List;

import dtu.database.*;
import dtu.errors.*;

//Everyone
public class SchedulingApp {
	private Worker currentUser;
	private WorkerRepository workerRepository;
	private ProjectRepository projectRepository = new ProjectRepositoryInMemory();
	private ActivityAssigner activityAssigner;
	private AssistRequestHandler requestHandler;
	private RegistrationHandler registrationHandler;

	public SchedulingApp() {
		this.workerRepository = new WorkerRepositoryInMemory();
		this.activityAssigner = new ActivityAssigner();
		this.requestHandler = new AssistRequestHandler();
		this.registrationHandler = new RegistrationHandler();
	}
	
	public void logIn(String workerId) throws WorkerDoesNotExistException{
	    currentUser = workerRepository.getWorkerById(workerId);		
	}
	
	
	public void createProjectActivity(String activtyName, String projectId) throws Exception {
		ProjectActivity activity = new ProjectActivity(activtyName);
		searchProject(projectId).addActivity(activity);
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
		return workerRepository.isUserInDatabase(workerId);
	}
	
	public void assignActivity(String workerId, ProjectActivity activity) throws WorkerDoesNotExistException {
		if (isUserInDatabase(workerId)) {
			activityAssigner.assignActivity(getWorkerById(workerId), activity);
		} else {
			throw new WorkerDoesNotExistException("No user with exists with initials " + workerId);
		}
		
	}
	
	public double getWeeklyRegisteredHours() {
		return currentUser.getWeeklyRegisteredHours();
	}
	
	public void addProject(Project project) throws ProjectAlreadyExistsException {
		projectRepository.add(project);
	}
	
	public Project searchProject(String ID) {
		return projectRepository.search(ID);
	}
	
	public  List<Project> getProjects() {
		return projectRepository.getProjects();
	}

	public double getHoursRegisteredOnActivity(ProjectActivity activity) throws Exception {
		TimeRegistration registration = currentUser.getTimeRegistrationByActivity(activity);
		return registration.getHours();
	}

	public void registerHours(double hours, ProjectActivity test_activity) throws Exception {
		registrationHandler.registerHours(hours, test_activity, currentUser);
	}

	public void assignProjectLeader(String projectID, String leaderID) throws WorkerDoesNotExistException, ProjectDoesNotExistException {
		Worker worker = workerRepository.getWorkerById(leaderID);
		Project project = searchProject(projectID);
		if (project == null) {
			throw new ProjectDoesNotExistException("Project " + projectID + " does not exist");
		}
		project.assignLeader(worker);
	}
  
	private Worker getWorkerById(String workerId) throws WorkerDoesNotExistException {
		return workerRepository.getWorkerById(workerId);
	}

	public void requestAssistance(ProjectActivity activity, String targetWorkerId) throws WorkerDoesNotExistException {
		AssistRequest newRequest = new AssistRequest(currentUser.getWorkerId(), activity);
		requestHandler.deliverRequest(newRequest, getWorkerById(targetWorkerId));
		
	}
	
	public List<AssistRequest> getWorkerRequests(String workerId) throws WorkerDoesNotExistException {
		return getWorkerById(workerId).getRequests();
	}
		
	public void changeHoursOnActivity(double new_hours, ProjectActivity activity) throws Exception {
		currentUser.changeHours(new_hours, activity);

	}
	
	//used by scheduleNonProjectActivity, not by client
	public void createNonProjectActivity(NonProjectActivity nonProjectActivity) {
		currentUser.addNonProjectActivity(nonProjectActivity);
		
	}

	public boolean workerHasNonProjectActivity(NonProjectActivity nonProjectActivity) {
		return(currentUser.hasNonProjectActivity(nonProjectActivity));

	}

	//used by scheduleNonProjectActivity, not by client
	public void registerNonProject(NonProjectActivity nonProjectActivity) {
		registrationHandler.registerNonProjectActivity(nonProjectActivity, currentUser);
		
	}

	public List<NonProjectRegistration> getNonProjectRegistrations() {
		return currentUser.getNonProjectRegistrations();
	}
	
	//This is used from clientside when scheduling nonprojectactivities
	public void scheduleNonProjectActivity(String name, int startWeek, int endWeek) throws Exception {
		if(name.isEmpty()) {
			throw new IllegalArgumentException("nonproject activity must have a have name");
		}
		NonProjectActivity npa = new NonProjectActivity(name,startWeek,endWeek);
		createNonProjectActivity(npa);
		registerNonProject(npa);
	}


	public List<NonProjectActivity> getWorkersNonProjectActivities() {
		return currentUser.getNonProjectActivies();
	}

	public List<ProjectActivity> getWorkersActivities() {
		return currentUser.getActivities();
	}
	
}
