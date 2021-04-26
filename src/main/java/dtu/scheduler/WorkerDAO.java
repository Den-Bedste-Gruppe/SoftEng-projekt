package dtu.scheduler;
//Philip Hviid
public class WorkerDAO {
	public boolean isUserInDatabase(String workerID) {
		if(workerID.equals("ASDF")) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public Worker getWorkerbyId(String id) throws WorkerDoesNotExistException {
		if(isUserInDatabase(id)) {
			return new Worker(id);
		} else {
			throw new WorkerDoesNotExistException("Signature not in system");
		}
	}
}
