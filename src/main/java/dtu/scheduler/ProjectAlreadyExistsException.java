package dtu.scheduler;

// Mads Harder
public class ProjectAlreadyExistsException extends Exception {
	public ProjectAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
}
