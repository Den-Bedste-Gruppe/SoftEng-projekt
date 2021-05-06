package dtu.errors;

// Mads Harder
public class ProjectDoesNotExistException extends Exception {
	public ProjectDoesNotExistException(String errorMessage) {
		super(errorMessage);
	}
}
