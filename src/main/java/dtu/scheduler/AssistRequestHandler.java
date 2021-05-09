package dtu.scheduler;
//Philip Hviid
public class AssistRequestHandler {
	
	public void deliverRequest(AssistRequest request, Worker worker) {
		worker.addRequest(request);
	}

}
