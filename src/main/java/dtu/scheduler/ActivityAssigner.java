package dtu.scheduler;

import dtu.errors.TooManyActivitiesException;

//Philip Hviid
public class ActivityAssigner {
	public void assignActivity(Worker worker, Activity activity) throws TooManyActivitiesException {
		worker.addActivity(activity);
	}
}
