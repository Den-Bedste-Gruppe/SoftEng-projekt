package dtu.errors;

public class TooManyActivitiesException extends Exception {
	public TooManyActivitiesException(String errorMessage){
		super(errorMessage);
	}
}
