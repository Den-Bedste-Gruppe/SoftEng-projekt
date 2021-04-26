package dtu.scheduler;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//Philip Hviid
public class LoginLogoutSteps {
	private String workerId;
	private SchedulingApp app;
	private ErrorMessageHolder msg;
	private WorkerDAO workerDAO;
	
	public LoginLogoutSteps(SchedulingApp app, ErrorMessageHolder msg, WorkerDAO  workerDAO) {
		this.app = app;
		this.msg = msg;
		this.workerDAO = workerDAO;
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
	    assertTrue(workerDAO.isUserInDatabase(workerId));
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
		assertTrue(!workerDAO.isUserInDatabase(workerId));
	}

	@Then("worker with ID {string} is not logged in")
	public void workerWithIDIsNotLoggedIn(String string) {
	    assert(!(app.getCurrentUser()==string));
	}

	@Then("{string} Error is thrown")
	public void errorIsThrown(String string) {
	    assert(msg.getErrorMessage().equals(string));
	}
	
	@Given("a worker is logged in")
	public void aWorkerIsLoggedIn() throws WorkerDoesNotExistException {
		app.logIn("ASDF");
	}

	@When("the worker logs out")
	public void theWorkerLogsOut() {
	    app.logOut();
	}

	@Then("no worker is logged in")
	public void noWorkerIsLoggedIn() {
		assert(app.getCurrentUser()==null);
	}
}
