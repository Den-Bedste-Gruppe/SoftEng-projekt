package dtu.scheduler;

public class SchedulingApp {
	private Worker currentUser;

	public boolean isUserInDatabase(String workerID) {
		if(workerID.equals("ASDF")) {
			return true;
		} else {
			return false;
		}
		
	}
	
	private Worker getWorkerbyId(String id) throws WorkerDoesNotExistException {
		if(isUserInDatabase(id)) {
			return new Worker(id);
		} else {
			throw new WorkerDoesNotExistException("Signature not in system");
		}
	}

	public void logIn(String workerId) throws WorkerDoesNotExistException{
	    currentUser = getWorkerbyId(workerId);


		
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
	

}
