package dtu.scheduler;
//Philip Hviid
public class WorkerDAO {
	WorkerDatabase workerDatabase;
	public WorkerDAO(WorkerDatabase workerDatabase) {
		this.workerDatabase = workerDatabase;
	}
	public boolean isUserInDatabase(String workerID) {
		return workerDatabase.isWorkerInDatabase(workerID);
		
	}
	

	
	public Worker getWorkerById(String id) throws WorkerDoesNotExistException {
		if(isUserInDatabase(id)) {
			return workerDatabase.getWorkerById(id);
		} else {
			throw new WorkerDoesNotExistException("Signature not in system");
		}
	}
}
