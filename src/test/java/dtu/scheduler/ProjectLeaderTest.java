package dtu.scheduler;

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
	
	@When("a worker assign the project {string} a project leader")
	public void aWorkerAssignTheProjectAProjectLeader(String projectID) {
		Worker projectLeader = schedulingApp.getCurrentUser();
		schedulingApp.assingProjectLeader(projectID, projectLeader);
	}

	@Then("the project {string} has a project leader with id {string}")
	public void theProjectHasAProjectLeaderWithId(String projectID, String leaderID) throws Exception {
		System.out.println(schedulingApp.searchProject(projectID).getProjectLeader());
		System.out.println(leaderID);
		if (!schedulingApp.searchProject(projectID).getProjectLeader().getWorkerId().equals(leaderID)) {
			throw new Exception("Error! the project does now have a project leader");
		}
	}

	@Given("the project {string} has a project leader")
	public void theProjectHasAProjectLeader(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("a worker change the project leader on project {string}")
	public void aWorkerChangeTheProjectLeaderOnProject(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the project {string} has a new project leader")
	public void theProjectHasANewProjectLeader(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
