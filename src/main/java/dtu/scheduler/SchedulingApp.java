package dtu.scheduler;

import java.util.List;
//Philip Hviid
public class SchedulingApp {
	private Worker currentUser;
	private WorkerDAO workerDAO;
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
	
	public String getCurrentUser() {
		if(isUserLoggedIn()) {
			return currentUser.getWorkerId();
		}
		return null;

	}
	
	public Boolean isUserLoggedIn() {
		return !(currentUser==null);
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
	

	public SchedulingApp() {
	}

	public double getWeeklyRegisteredHours() {
		return currentUser.getWeeklyRegisteredHours();
	}

	public double getHoursRegisteredOnActivity(Activity activity) throws Exception {
		TimeRegistration registration = currentUser.getTimeRegistrationByActivity(activity);
		return registration.getHours();
	}

	public void registerHours(double hours, Activity test_activity) throws Exception {
		currentUser.registerHours(hours, test_activity);
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
