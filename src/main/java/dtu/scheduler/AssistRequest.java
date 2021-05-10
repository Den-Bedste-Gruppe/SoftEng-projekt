package dtu.scheduler;

public class AssistRequest {
	private boolean accepted;
	private String senderId;
	private ProjectActivity activity;
	
	public AssistRequest(String senderId, ProjectActivity activity) {
		this.senderId = senderId;
		this.activity = activity;
		this.accepted = false;
	}
	
	public String getSenderId() {
		return senderId;
	}
	
	public ProjectActivity getActivity() {
		return activity;
	}
	
	public void toggleStatus() {
		accepted =! accepted;
	}
	
	public void deliverRequest(Worker worker) {
		worker.addRequest(this);
	}
	
	public void acceptRequest(Worker worker) throws Exception {
		ProjectActivity activity = getActivity();
		activity.assignWorker(worker);
		toggleStatus();
		worker.getRequests().remove(this);
	}
	
}
