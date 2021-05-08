package dtu.scheduler;


//Philip Hviid
public class ActivityAssigner {
	public void assignActivity(Worker worker, ProjectActivity activity) {
		worker.addActivity(activity);
	}
}
