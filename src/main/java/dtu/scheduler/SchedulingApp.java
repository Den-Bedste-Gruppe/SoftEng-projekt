package dtu.scheduler;

public class SchedulingApp {
	private Worker currentUser;
	private WorkerDAO workerDAO;
	private ActivityAssigner activityAssigner;
	
	public SchedulingApp(WorkerDAO workerDAO, ActivityAssigner activityAssigner) {
		this.workerDAO = workerDAO;
		this.activityAssigner = activityAssigner;
	}
	
	
	public void logIn(String workerId) throws WorkerDoesNotExistException{
	    currentUser = workerDAO.getWorkerbyId(workerId);		
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
	
	public void assignActivity(String worker, Activity activity) throws WorkerDoesNotExistException {
		activityAssigner.assignActivity(workerDAO.getWorkerbyId(worker), activity);
	}
	

	public SchedulingApp() {
	}

	public double getWeeklyRegisteredHours() {
		return currentUser.getWeeklyRegisteredHours();
	}

	public void registerHours(double hours, Activity test_activity) throws Exception {
		currentUser.registerHours(hours, test_activity);
	}
}
