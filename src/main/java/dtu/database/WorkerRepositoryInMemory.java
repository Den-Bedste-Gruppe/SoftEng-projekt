package dtu.database;

import dtu.errors.WorkerDoesNotExistException;
import dtu.scheduler.Worker;

//Philip Hviid
public class WorkerRepositoryInMemory implements WorkerRepository {

	private Worker[] workers = {new Worker("QWER"), new Worker("ASDF"), new Worker("ZXCV"),  new Worker("LMAA")};
	
	public WorkerRepositoryInMemory() {
	}

	@Override
	public boolean isUserInDatabase(String workerID) {
		return isWorkerInDatabase(workerID);
		
	}
	
	@Override
	public Worker getWorkerById(String id) throws WorkerDoesNotExistException {
		Worker worker = getWorkerById2(id);
		if(worker != null) {
			return worker;
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
	
	// TODO rename getWorkerById2
	public Worker getWorkerById2(String id) {
		for(Worker worker: workers) {
			if(worker.getWorkerId().equals(id) ) {
				return worker;
			}
		}
		return null;
	}
	
	public Worker[] getAllWorkers() {
		return workers;
	}

}
