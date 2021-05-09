package dtu.scheduler;
//Philip Hviid
public abstract class ActivityRegistration {
	private Worker parentWorker;
	
	protected ActivityRegistration(Worker parentWorker) {
		this.parentWorker = parentWorker;
	}
	
	abstract void register();
	
	
	abstract Activity getParentActivity();

	public Worker getParentWorker() {
		return parentWorker;
	}
	
}
