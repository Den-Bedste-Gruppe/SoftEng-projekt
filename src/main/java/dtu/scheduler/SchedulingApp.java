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
}
