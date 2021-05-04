package dtu.scheduler;
//Philip Hviid
public class ErrorMessageHolder {

	private String errorMessage = "";
	//contains the single instance of the object used across all dependant classes
	private static ErrorMessageHolder instance;
	
	//private constructor for singleton
	private ErrorMessageHolder() {}
	
	public static ErrorMessageHolder getInstance() {
		if(instance==null) {
			instance = new ErrorMessageHolder();
		}
		return instance;
	}
	
	
	public void setErrorMessage(String message) {
		errorMessage = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}

