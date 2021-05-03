package dtu.scheduler;

public class WorkerDoesNotExistException extends Exception {
	public WorkerDoesNotExistException(String errorMessage) {
		super(errorMessage);
	}
}
