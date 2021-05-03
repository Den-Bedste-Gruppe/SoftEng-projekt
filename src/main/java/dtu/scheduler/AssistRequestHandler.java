package dtu.scheduler;
//Philip Hviid
public class AssistRequestHandler {
	public void deliverRequest(AssistRequest request, Worker worker) {
		System.out.println(worker.getWorkerId());
		worker.addRequest(request);
	}
	
	public void acceptRequest(AssistRequest request) {
		
	}
}
