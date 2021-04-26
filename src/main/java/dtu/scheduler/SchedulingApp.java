package dtu.scheduler;

public class SchedulingApp {
	private Worker currentUser;
	private WorkerDAO workerDAO = new WorkerDAO();
	
	
	public void logIn(String workerId) throws WorkerDoesNotExistException{
	    currentUser = workerDAO.getWorkerbyId(workerId);		
	}
	
	public String getCurrentUser() {
		if(currentUser==null) {
			return null;
		}
		return currentUser.getWorkerId();
	}

	public void logOut() {
		currentUser = null;
	}
	
	public boolean isUserInDatabase(String workerId) {
		return workerDAO.isUserInDatabase(workerId);
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
