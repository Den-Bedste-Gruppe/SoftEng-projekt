package dtu.scheduler;

// Mads Harder
public class ProjectAlreadyExistException extends Exception {
	public ProjectAlreadyExistException(String errorMessage) {
		super(errorMessage);
	}
}
