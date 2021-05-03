package dtu.scheduler;

// By Mads Harder
public class Worker {
	private String workerId;
	private double week_hours;

	public Worker(String workerId) {
		// Input validation checking
		if (workerId.length() == 0) throw new IllegalArgumentException("The ID must be at least one character");
		if (workerId.length() > 4) throw new IllegalArgumentException("The ID can max be 4 characters");
		
		workerId = workerId.toUpperCase();

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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return workerId;
	}
}
