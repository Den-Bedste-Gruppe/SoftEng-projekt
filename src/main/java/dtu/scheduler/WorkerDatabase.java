package dtu.scheduler;

public class WorkerDatabase {
	private static Worker[] workers = {new Worker("QWER"), new Worker("ASDF"), new Worker("ZXCV")};
	
	public static Worker getWorkerAt(int index) {
		return workers[index];
	}
	
	public static boolean isWorkerInDatabase(String workerId) {
		for(Worker worker : workers) {
			if(worker.getWorkerId().equals(workerId)) {
				return true;
			}
		}
		return false;
	}
	
	public static Worker getWorkerById(String id) {
		for(Worker worker: workers) {
			if(worker.getWorkerId().equals(id) ) {
				return worker;
			}
		}
		return null;
	}
}
