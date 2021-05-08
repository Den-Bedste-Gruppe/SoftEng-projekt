package dtu.scheduler;


//Philip Hviid
public class NonProjectRegistration extends ActivityRegistration {
	private NonProjectActivity parentActivity;
	
	public NonProjectRegistration(NonProjectActivity parentActivity, Worker parentWorker) {
		super(parentWorker);
		this.parentActivity = parentActivity;
	}

	public NonProjectActivity getActivity() {
		return parentActivity;
	}
	
	public void register() {
		getParentWorker().addNonProjectRegistration(this);
	}

	Activity getParentActivity() {
		return parentActivity;
	}
	
	
}
