package dtu.scheduler;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginLogoutSteps {
	private String workerId;
	private SchedulingApp app;
	private ErrorMessageHolder msg;
	
	public LoginLogoutSteps(SchedulingApp app, ErrorMessageHolder msg) {
		this.app = app;
		this.msg = msg;
	}
	@Given("a workerID exists with signature {string}")
	public void aWorkerIDExistsWithSignature(String string) {
	    workerId = string;
	}
	
	@Given("a workerID exists")
	public void aWorkerIDExists() {
	    workerId = "ASDF";
	}

	@Given("the workerID is in the system")
	public void theWorkerIDIsInTheSystem() {
	    assertTrue(app.isUserInDatabase(workerId));
	}

	@When("worker logs in")
	public void workerLogsIn() {
		try {
		    app.logIn(workerId);
		} catch (WorkerDoesNotExistException e) {
			msg.setErrorMessage(e.getMessage());
		}

	}

	@Then("worker is logged in")
	public void workerIsLoggedIn() {
	    assert(app.getCurrentUser().equals("ASDF"));
	}
	
	@Given("the workerID is not in the system")
	public void theWorkerIDIsNotInTheSystem() {
		assertTrue(!app.isUserInDatabase(workerId));
	}

	@Then("worker with ID {string} is not logged in")
	public void workerWithIDIsNotLoggedIn(String string) {
	    assert(!(app.getCurrentUser()==string));
	}

	@Then("{string} Error is thrown")
	public void errorIsThrown(String string) {
	    assert(msg.getErrorMessage().equals(string));
	}
}
