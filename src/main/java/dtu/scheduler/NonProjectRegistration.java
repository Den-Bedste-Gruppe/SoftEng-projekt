package dtu.scheduler;

import java.util.Date;
//Philip Hviid
public class NonProjectRegistration extends ActivityRegistration {
	private NonProjectActivity parentActivity;
	
	public NonProjectRegistration(NonProjectActivity parentActivity, String parentWorkerId) {
		super(parentWorkerId);
		this.parentActivity = parentActivity;
	}

	public NonProjectActivity getActivity() {
		return parentActivity;
	}
	
	public void register() {
		//TODO redelegate registration functionality here
	}

	@Override
	Activity getParentActivity() {
		return parentActivity;
	}
	
}
