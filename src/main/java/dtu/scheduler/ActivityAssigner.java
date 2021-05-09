package dtu.scheduler;


//Philip Hviid
public class ActivityAssigner {
	public void assignActivity(Worker worker, ProjectActivity activity) throws Exception {
		worker.addActivity(activity);
		activity.addWorker(worker);
	}
}
