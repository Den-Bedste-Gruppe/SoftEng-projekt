package dtu.scheduler;
//Philip Hviid
public class WorkerDAO {
	public boolean isUserInDatabase(String workerID) {
		return WorkerDatabase.isWorkerInDatabase(workerID);
		
	}
	

	
	public Worker getWorkerById(String id) throws WorkerDoesNotExistException {
		if(isUserInDatabase(id)) {
			return WorkerDatabase.getWorkerById(id);
		} else {
			throw new WorkerDoesNotExistException("Signature not in system");
		}
	}
}
