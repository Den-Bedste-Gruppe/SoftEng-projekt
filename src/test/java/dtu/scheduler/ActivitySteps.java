package dtu.scheduler;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;




public class ActivitySteps {
	private String targetWorkerId;
	private SchedulingApp schedulingApp;
	private ErrorMessageHolder msg;
	private ActivityAssigner activityAssigner;

	
	public ActivitySteps(SchedulingApp app, ErrorMessageHolder msg, ActivityAssigner activityAssigner) {
		this.schedulingApp = app;
		this.msg = msg;
		this.activityAssigner = activityAssigner;
	}
	
	@Given("the worker is on an activity")
	public void theWorkerIsOnAnActivity() throws WorkerDoesNotExistException {
		String currUser = schedulingApp.getCurrentUser();
		schedulingApp.assignActivity(currUser, new Activity());
	}

	@Given("there is another worker")
	public void thereIsAnotherWorker() {
	    targetWorkerId = "QWER";
	}

	@When("the user requests assistance")
	public void theUserRequestsAssistance() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the other worker has a request for assistance")
	public void theOtherWorkerHasARequestForAssistance() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the worker requests assistance from invalid signature")
	public void theWorkerRequestsAssistanceFromInvalidSignature() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
