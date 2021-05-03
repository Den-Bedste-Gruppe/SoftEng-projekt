package dtu.scheduler;

public class AssistRequest {
	private boolean accepted;
	private String senderId;
	private Activity activity;
	
	public AssistRequest(String senderId, Activity activity) {
		this.senderId = senderId;
		this.activity = activity;
		this.accepted = false;
	}
	
	
	public String getSenderId() {
		return senderId;
	}
	
	public void changeStatus() {
		accepted =! accepted;
	}
	
}
