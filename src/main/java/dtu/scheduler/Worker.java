package dtu.scheduler;

public class Worker {
    private String workerId;
    private double week_hours;
	public Worker(String workerId) {
		this.workerId = workerId;
		week_hours = 0;
	}
	
	public String getWorkerId() {
		return workerId;
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
