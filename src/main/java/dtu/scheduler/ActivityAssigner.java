package dtu.scheduler;


//Philip Hviid
public class ActivityAssigner {
	public void assignActivity(Worker worker, Activity activity) {
		worker.addActivity(activity);
	}
}
