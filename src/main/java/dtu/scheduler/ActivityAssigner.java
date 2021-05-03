package dtu.scheduler;

public class ActivityAssigner {
	public void assignActivity(Worker worker, Activity activity) {
		worker.addActivity(activity);
	}
}
