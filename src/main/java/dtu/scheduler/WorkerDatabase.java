package dtu.scheduler;
//Philip Hviid
public class WorkerDatabase {
	private Worker[] workers = {new Worker("QWER"), new Worker("ASDF"), new Worker("ZXCV")};
	
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
	
	public Worker getWorkerById(String id) {
		for(Worker worker: workers) {
			if(worker.getWorkerId().equals(id) ) {
				return worker;
			}
		}
		return null;
	}
}
