package dtu.scheduler;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectTest {
	private SchedulingApp schedulingApp;
	private ErrorMessageHolder errorMessageHolder;
	private Worker test_worker;
	//private Project project;

	public ProjectTest(SchedulingApp schedulingApp, ErrorMessageHolder errorMessageHolder) {
		this.schedulingApp = schedulingApp;
		this.errorMessageHolder = errorMessageHolder;
	}


	@Given("there exists a project with name {string}")
	public void thereExistsAProjectWithName(String name) throws Exception {
		schedulingApp.addProject(new Project(name));
	}

	@When("the worker creates a new project")
	public void the_worker_creates_a_new_project() throws Exception {
		//Project project = new Project("TESTPROJECT");
		schedulingApp.addProject(new Project("TESTPROJECT"));
		
	}

	@Then("the project is created")
	public void the_project_is_created() throws Exception {
		if (schedulingApp.searchProject("TESTPROJECT") == null) {
			// TODO write a better error
			throw new Exception("Error! project does not exists");
		};
	}

	@When("the worker creates a new project with name {string}")
	public void the_worker_creates_a_new_project_with_name(String name) {
		System.out.println(schedulingApp.getCurrentUser());
		//Project project = new Project(name);
		try {
			schedulingApp.addProject(new Project(name));
		} catch (ProjectAlreadyExistException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("there is a project with the name {string}")
	public void there_is_a_project_with_the_name(String name) throws Exception {
		if (schedulingApp.searchProject(name) == null) {
			// TODO write a better error
			System.out.println("hello");
			//throw new Exception("Error! project \"" + name + "\" does not exists");
		};
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