package dtu.scheduler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//Philip Hviid
public class LoginLogoutSteps {
	private String workerId;
	private SchedulingApp schedulingApp;
	private ErrorMessageHolder msg;

	
	public LoginLogoutSteps(SchedulingApp app, ErrorMessageHolder msg) {
		this.schedulingApp = app;
		this.msg = msg;
	}
	
	@Given("that a worker is logged in")
	public void that_a_worker_is_logged_in() throws WorkerDoesNotExistException {
	    schedulingApp.logIn("ASDF");
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
	    assertTrue(schedulingApp.isUserInDatabase(workerId));
	}

	@When("worker logs in")
	public void workerLogsIn() {
		try {
		    schedulingApp.logIn(workerId);
		} catch (WorkerDoesNotExistException e) {
			msg.setErrorMessage(e.getMessage());
		}

	}

	@Then("worker is logged in")
	public void workerIsLoggedIn() {
	    assert(schedulingApp.getCurrentUser().equals("ASDF"));
	}
	
	@Given("the workerID is not in the system")
	public void theWorkerIDIsNotInTheSystem() {
		assertTrue(!schedulingApp.isUserInDatabase(workerId));
	}

	@Then("worker with ID {string} is not logged in")
	public void workerWithIDIsNotLoggedIn(String string) {
	    assert(!(schedulingApp.getCurrentUser()==string));
	}

	@Then("{string} Error is thrown")
	public void errorIsThrown(String string) {
	    assert(msg.getErrorMessage().equals(string));
	}
	
	@Given("a worker is logged in")
	public void aWorkerIsLoggedIn() throws WorkerDoesNotExistException {
		schedulingApp.logIn("ASDF");
	}

	@When("the worker logs out")
	public void theWorkerLogsOut() {
	    schedulingApp.logOut();
	}

	@Then("no worker is logged in")
	public void noWorkerIsLoggedIn() {
		assertFalse(schedulingApp.isUserLoggedIn());
	}
}
