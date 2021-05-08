//Author Emil Krarup
package dtu.scheduler;


import static org.junit.Assert.assertTrue;

import dtu.errors.ProjectAlreadyExistsException;
import dtu.errors.ProjectDoesNotExistException;
import dtu.errors.WorkerDoesNotExistException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SetTimeFrameTest {
	
	private SchedulingApp schedulingApp;
	private ErrorMessageHolder errorMessageHolder;
	private Worker worker;
	private String projectName = "timeframeTest", activityName = "timeframeActivity";
	Project test_project;

	public SetTimeFrameTest(SchedulingApp schedulingApp, ErrorMessageHolder errorMessageHolder) throws Exception {
		this.schedulingApp = schedulingApp;
		this.errorMessageHolder = errorMessageHolder;
		test_project = new Project(projectName);
	}
	
	@Given("There is a worker who is project leader for a given project")
	public void there_is_a_worker_who_is_project_leader_for_a_given_project() throws WorkerDoesNotExistException, ProjectAlreadyExistsException, ProjectDoesNotExistException {
		//worker = new Worker("ASDF");
		schedulingApp.logIn("ASDF");
		schedulingApp.addProject(test_project);
		schedulingApp.assignProjectLeader(test_project.getProjectID(), "ASDF");
	}

	@Given("There is a project activity")
	public void there_is_a_project_activity() throws Exception {
		schedulingApp.createProjectActivity(activityName, test_project.getProjectID());
	}

	@When("The project leader sets a time frame with start week {int} and end week {int} for an activity")
	public void the_project_leader_sets_a_time_frame_with_start_week_and_end_week_for_an_activity(Integer startWeek, Integer endWeek) throws Exception {
	    ProjectActivity curentActivity = test_project.searchActivity(activityName);
	    curentActivity.setTimeFrame(startWeek, endWeek);
	}

	@Then("The activity is given a time frame with start week {int} and end week {int}")
	public void the_activity_is_given_a_time_frame_with_start_week_and_end_week(Integer startWeek, Integer endWeek) {
	    ProjectActivity curentActivity = test_project.searchActivity(activityName);
		int[] timeframe = curentActivity.getTimeframe();
		assertTrue(timeframe[0] == startWeek && timeframe[1] == endWeek);
	}
	
	@When("The project leader sets an illegible time frame")
	public void the_project_leader_sets_an_illegible_time_frame() {
		ProjectActivity curentActivity = test_project.searchActivity(activityName);
		try {
			curentActivity.setTimeFrame(-2, 55);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage()); 
		}
	}

	@Then("The error message {string}")
	public void the_error_message(String errorMessage) {
	  assertTrue(errorMessageHolder.getErrorMessage().equals(errorMessage));
	}
}
