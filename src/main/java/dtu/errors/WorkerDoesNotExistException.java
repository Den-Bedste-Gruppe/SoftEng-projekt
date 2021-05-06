package dtu.errors;

public class WorkerDoesNotExistException extends Exception {
	public WorkerDoesNotExistException(String errorMessage) {
		super(errorMessage);
	}
}
