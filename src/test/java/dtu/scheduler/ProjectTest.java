package dtu.scheduler;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectTest {

	private SchedulingApp schedulingApp;
	private ErrorMessageHolder errorMessageHolder;
	private Worker test_worker;

	public ProjectTest(SchedulingApp schedulingApp, ErrorMessageHolder errorMessageHolder, Worker test_worker) {
		this.schedulingApp = schedulingApp;
		this.errorMessageHolder = errorMessageHolder;
		this.test_worker = test_worker;
	}


	@Given("that a worker is logged in")
	public void that_a_worker_is_logged_in() {
	    test_worker = new Worker();
		System.out.println("TEST STEP IMPLEMENTATION INCOMPLETE, FIX IT");
	}

	@When("the worker creates a new project")
	public void the_worker_creates_a_new_project() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the project is created")
	public void the_project_is_created() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the worker creates a new project with name {string}")
	public void the_worker_creates_a_new_project_with_name(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("there is a project with the name {string}")
	public void there_is_a_project_with_the_name(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	//@Given("there is a project with the name {string}")
	//public void there_is_a_project_with_the_name(String string) {
	//    // Write code here that turns the phrase above into concrete actions
	//    throw new io.cucumber.java.PendingException();
	//}

	@Then("the error message {string} is given")
	public void theErrorMessageIsGiven(String message) {
	    assertTrue(errorMessageHolder.getErrorMessage().equals(message));
	}
}