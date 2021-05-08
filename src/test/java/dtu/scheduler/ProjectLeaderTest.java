package dtu.scheduler;

import org.junit.internal.ExactComparisonCriteria;

import dtu.errors.ProjectAlreadyExistsException;
import dtu.errors.WorkerDoesNotExistException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Mads Harder
public class ProjectLeaderTest {
	private SchedulingApp schedulingApp;
	private ErrorMessageHolder errorMessageHolder;
	
	public ProjectLeaderTest(SchedulingApp schedulingApp, ErrorMessageHolder errorMessageHolder) {
		this.schedulingApp = schedulingApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("that a worker is logged in with id {string}")
	public void thatAWorkerIsLoggedInWithId(String workerID) throws WorkerDoesNotExistException {
		schedulingApp.logIn(workerID);
	}
	
	@When("a worker assigns the project {string} a project leader")
	public void aWorkerAssignTheProjectAProjectLeader(String projectName) throws Exception {
		String projectID = schedulingApp.findIDByName(projectName);
		schedulingApp.assignProjectLeader(projectID, schedulingApp.getCurrentUserID());
	}

	@Then("the project {string} has a project leader with id {string}")
	public void theProjectHasAProjectLeaderWithId(String projectName, String leaderID) throws Exception {
		String projectID = schedulingApp.findIDByName(projectName);
		if (!schedulingApp.searchProject(projectID).getProjectLeader().getWorkerId().equals(leaderID)) {
			throw new Exception("Error! the project does now have a project leader");
		}
	}

	@Given("the project {string} has a project leader")
	public void theProjectHasAProjectLeader(String projectName) throws Exception {
		String projectID = schedulingApp.findIDByName(projectName);
		Worker worker = schedulingApp.getCurrentUser();
		schedulingApp.searchProject(projectID).assignLeader(worker);
	}

	@Given("there is a project with name {string}")
	public void thereIsAProjectWithName(String name) throws ProjectAlreadyExistsException {
		schedulingApp.addProject(new Project(name));
	}

	@When("a worker changes the project leader on project {string}")
	public void aWorkerChangeTheProjectLeaderOnProject(String projectName) {
		String projectID = schedulingApp.findIDByName(projectName);
		// TODO fix the new random user
		Worker newWorker = new Worker("TEST");
		schedulingApp.searchProject(projectID).assignLeader(newWorker);
	}
}
