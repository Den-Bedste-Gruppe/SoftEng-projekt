package dtu.scheduler;
//Philip Hviid
public abstract class ActivityRegistration {
	private String parentWorkerId;
	
	protected ActivityRegistration(String parentWorkerId) {
		this.parentWorkerId = parentWorkerId;
	}
	
	
	abstract void register();
	
	abstract Activity getParentActivity();
}
