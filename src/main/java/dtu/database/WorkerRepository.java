package dtu.database;

import dtu.errors.WorkerDoesNotExistException;
import dtu.scheduler.Worker;

public interface WorkerRepository {

	boolean isUserInDatabase(String workerID);

	Worker getWorkerById(String id) throws WorkerDoesNotExistException;
	
	Worker[] getAllWorkers();

}