package dtu.scheduler;

public class Worker {

	private double week_hours;

	public Worker() {
		week_hours = 0; //We don't want to store this as a variable, that would make it work only for the current week
						//Temporary solution to pass the tests
	}
	
	public double getWeeklyRegisteredHours() {
		return week_hours;
	}

	public void registerHours(double hours, Activity activity) throws Exception {
		// By Kristian Sofus Knudsen
		if (hours <= 0 || hours > 24) {
			throw new Exception("Invalid amount of hours");
		}
		week_hours += hours;
		activity.registerHours(hours);
	}
}
