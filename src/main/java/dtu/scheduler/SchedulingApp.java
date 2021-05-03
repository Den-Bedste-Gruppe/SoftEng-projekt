package dtu.scheduler;

public class SchedulingApp {
	private Worker currentUser;
	private WorkerDAO workerDAO = new WorkerDAO();
	
	
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

	public void changeHoursOnActivity(double new_hours, Activity activity) throws Exception {
		currentUser.changeHours(new_hours, activity);
	}
}
