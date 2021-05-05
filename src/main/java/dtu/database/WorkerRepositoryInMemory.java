package dtu.database;

import dtu.errors.WorkerDoesNotExistException;
import dtu.scheduler.Worker;

//Philip Hviid
public class WorkerRepositoryInMemory implements WorkerRepository {

	private Worker[] workers = {new Worker("QWER"), new Worker("ASDF"), new Worker("ZXCV")};
	
	public WorkerRepositoryInMemory() {
	}

	@Override
	public boolean isUserInDatabase(String workerID) {
		return isWorkerInDatabase(workerID);
		
	}
	

	
	@Override
	public Worker getWorkerById(String id) throws WorkerDoesNotExistException {
		if(isUserInDatabase(id)) {
			return getWorkerById2(id);
		} else {
			throw new WorkerDoesNotExistException("Signature not in system");
		}
	}
	public Worker getWorkerAt(int index) {
		return workers[index];
	}
	
	public boolean isWorkerInDatabase(String workerId) {
		for(Worker worker : workers) {
			if(worker.getWorkerId().equals(workerId)) {
				return true;
			}
		}
		return false;
	}
	
	public Worker getWorkerById2(String id) {
		for(Worker worker: workers) {
			if(worker.getWorkerId().equals(id) ) {
				return worker;
			}
		}
		return null;
	}

}
