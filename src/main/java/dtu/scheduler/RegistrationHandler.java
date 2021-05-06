package dtu.scheduler;
//Philip Hviid
public class RegistrationHandler {
	public void registerHours(double hours, Activity activity, Worker worker) throws Exception {
		// By Kristian Sofus Knudsen
		if (hours <= 0 || hours > 24) {
			throw new Exception("Invalid amount of hours");
		}
		//adds itself to the activity registration-list automatically in constructor
		TimeRegistration new_registration = new TimeRegistration(hours, activity, worker.getWorkerId());
		//manually add to worker registration-list
		worker.addTimeRegistration(new_registration);
		worker.updateWeeklyHoursSpent(hours);
	}

	//Philip Hviid
	public void registerNonProjectActivity(NonProjectActivity nonProjectActivity, Worker worker) throws Exception {
		NonProjectTimeRegistration new_registration = new NonProjectTimeRegistration(nonProjectActivity, worker.getWorkerId());
		worker.addNonProjectTimeRegistration(new_registration);
	}
}
