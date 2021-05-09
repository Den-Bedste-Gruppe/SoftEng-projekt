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
	
	public void toggleStatus() {
		accepted =! accepted;
	}
	
}
