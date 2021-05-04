package dtu.scheduler;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Mads Harder
public class ProjectTest {
	private SchedulingApp schedulingApp;
	private ErrorMessageHolder errorMessageHolder;

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
		schedulingApp.addProject(new Project("TESTPROJECT"));
		
	}

	@Then("the project is created")
	public void the_project_is_created() throws Exception {
		if (schedulingApp.getProjects().size() != 1) {
			throw new Exception("Error! No project was created");
		};
	}

	@When("the worker creates a new project with name {string}")
	public void the_worker_creates_a_new_project_with_name(String name) {
		try {
			Project project = new Project(name);
			schedulingApp.addProject(project);
		} catch (ProjectAlreadyExistsException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("there is a project with the name {string}")
	public void there_is_a_project_with_the_name(String name) throws Exception {
		if (schedulingApp.searchProject(name) == null) {
			throw new Exception("Error! project \"" + name + "\" does not exists");
		};
	}

	@Then("the error message {string} is given")
	public void theErrorMessageIsGiven(String message) {
	    assertTrue(errorMessageHolder.getErrorMessage().equals(message));
	}
}