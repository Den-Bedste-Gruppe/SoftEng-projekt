package dtu.scheduler;

import java.util.List;

import dtu.database.*;
import dtu.errors.*;
import io.cucumber.java.en_old.Ac;

//Everyone
public class SchedulingApp {
	private Worker currentUser;
	private WorkerRepository workerRepository;
	private ProjectRepository projectRepository = new ProjectRepositoryInMemory();
	private ActivityAssigner activityAssigner;
	private AssistRequestHandler requestHandler;

	public SchedulingApp() {
		this.workerRepository = new WorkerRepositoryInMemory();
		this.activityAssigner = new ActivityAssigner();
		this.requestHandler = new AssistRequestHandler();
	}
	
	public void logIn(String workerId) throws WorkerDoesNotExistException{
	    currentUser = workerRepository.getWorkerById(workerId);		
	}
	
	
	public void createProjectActivity(String activityName, String projectId) throws Exception {
		ProjectActivity activity = new ProjectActivity(activityName);
		searchProject(projectId).addActivity(activity);
	}
	
	public String getCurrentUserID() {
		if(isUserLoggedIn()) {
			return currentUser.getWorkerId();
		}
		return null;
	}

	public Worker getCurrentUser() {
		return currentUser;
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
	
	public void assignActivity(String workerId, ProjectActivity activity) throws Exception {
		if (isUserInDatabase(workerId)) {
			activityAssigner.assignActivity(getWorkerById(workerId), activity);
		} else {
			throw new WorkerDoesNotExistException("No user with exists with initials " + workerId);
		}
	}
	
	public void setAcivityTimeFrame(Project project, Activity activity, int startYear, int startWeek, int endYear, int endWeek) throws Exception {
		if (!currentUser.equals(project.getProjectLeader())) {
			throw new Exception("The current worker is not a project leader and can't set time frame");
		}
		activity.setTimeFrame(startYear, startWeek, endYear, endWeek);
	}
	
	public int[] getAcivityTimeFrame(Activity activity) throws Exception {
		return activity.getTimeframe().getTimeFrameAsList();
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

	public Object searchProjectByName(String name) {
		return projectRepository.searchByName(name);
	}

	public String findIDByName(String projectName) {
		Project p = projectRepository.searchByName(projectName);
		return p.getProjectID();
	}
	
	public  List<Project> getProjects() {
		return projectRepository.getProjects();
	}

	public double getHoursRegisteredOnActivity(ProjectActivity activity) throws Exception {
		TimeRegistration registration = currentUser.getTimeRegistrationByActivity(activity);
		return registration.getHours();
	}

	public void registerHours(double hours, ProjectActivity activity) throws Exception {
		TimeRegistration newTimeRegistration = new TimeRegistration(hours, activity, currentUser);
		newTimeRegistration.register();
	}
	
	//This is used from clientside when scheduling nonprojectactivities, as they will always be both created and registered
	public void scheduleNonProjectActivity(String name, int startYear, int startWeek, int endYear, int endWeek) throws Exception {
		NonProjectActivity npa = new NonProjectActivity(name);
		npa.setTimeFrame(startYear, startWeek, endYear, endWeek);
		createNonProjectActivity(npa, currentUser);
		registerNonProjectActivity(npa, currentUser);
	}
	
	
	public void changeHoursOnActivity(double new_hours, ProjectActivity activity) throws Exception {
		TimeRegistration registration = currentUser.getTimeRegistrationByActivity(activity);
		currentUser.changeHours(new_hours, registration);

	}

	//used by scheduleNonProjectActivity, not by client
	public void registerNonProjectActivity(NonProjectActivity nonProjectActivity, Worker worker) {
		NonProjectRegistration newNonProjectRegistration = new NonProjectRegistration(nonProjectActivity, worker);
		newNonProjectRegistration.register();	
	}
	
	
	//used by scheduleNonProjectActivity, not by client
	public void createNonProjectActivity(NonProjectActivity nonProjectActivity, Worker worker) {
		worker.addNonProjectActivity(nonProjectActivity);
		
	}
	
	public void addNonProjectActivity(String workerId, Integer startYear,
			Integer startWeek, Integer endYear, Integer endWeek) throws Exception {
		NonProjectActivity npa = new NonProjectActivity("testname");
		npa.setTimeFrame(startYear, startWeek, endYear, endWeek);
		getWorkerById(workerId).addNonProjectActivity(npa);
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

	public boolean workerHasNonProjectActivity(NonProjectActivity nonProjectActivity) {
		return(currentUser.hasNonProjectActivity(nonProjectActivity));

	}


	public List<NonProjectRegistration> getNonProjectRegistrations() {
		return currentUser.getNonProjectRegistrations();
	}

	public List<NonProjectActivity> getCurrentUsersNonProjectActivities() {
		return currentUser.getNonProjectActivies();
	}

	public List<ProjectActivity> getCurrentUsersActivities() {
		return currentUser.getActivities();
	}
	
	public List<NonProjectActivity> getWorkersNonProjectActivities(String workerId) throws WorkerDoesNotExistException{
		return getWorkerById(workerId).getNonProjectActivies();
	}
	
	public List<ProjectActivity> getWorkersProjectActivities(String workerId) throws WorkerDoesNotExistException{
		return getWorkerById(workerId).getActivities();
	}
	
	public int [] getOverLaps(String workerId, ProjectActivity activity) throws WorkerDoesNotExistException{
		return getWorkerById(workerId).activitiesInTimeFrame(activity.getTimeframe());
	}

	public void setBudgetedTime(int int1, ProjectActivity activity, Project parentProject) throws Exception {
		if(!currentUser.equals(parentProject.getProjectLeader())) {
			throw new Exception("only project leader can assign budgeted time");
		}
		activity.setBudgetedTime(int1);
	}
	
	
}
