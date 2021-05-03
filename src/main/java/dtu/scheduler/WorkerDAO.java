package dtu.scheduler;
//Philip Hviid
public class WorkerDAO {
	public boolean isUserInDatabase(String workerID) {
		return WorkerDatabase.isWorkerInDatabase(workerID);
		
	}
	
	public Worker getWorkerbyId(String id) throws WorkerDoesNotExistException {
		if(isUserInDatabase(id)) {
			return new Worker(id);
		} else {
			throw new WorkerDoesNotExistException("Signature not in system");
		}
	}
}
