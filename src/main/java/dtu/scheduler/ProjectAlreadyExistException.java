package dtu.scheduler;

public class ProjectAlreadyExistException extends Exception {
	public ProjectAlreadyExistException(String errorMessage) {
		super(errorMessage);
	}
}
