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

	public SetTimeFrameTest(SchedulingApp schedulingApp, ErrorMessageHolder errorMessageHolder) {
		this.schedulingApp = schedulingApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("There is a worker who is project leader for a given project")
	public void there_is_a_worker_who_is_project_leader_for_a_given_project() throws WorkerDoesNotExistException, ProjectAlreadyExistsException, ProjectDoesNotExistException {
		//worker = new Worker("ASDF");
		schedulingApp.logIn("ASDF");
		schedulingApp.addProject(new Project(projectName));
		schedulingApp.assignProjectLeader(projectName, "ASDF");
	}

	@Given("There is a project activity")
	public void there_is_a_project_activity() throws Exception {
		schedulingApp.createProjectActivity(activityName, projectName);
	}

	@When("The project leader sets a time frame with start week {int} and end week {int} for an activity")
	public void the_project_leader_sets_a_time_frame_with_start_week_and_end_week_for_an_activity(Integer startWeek, Integer endWeek) throws Exception {
	    Project curentProject = schedulingApp.searchProject(projectName);
	    Activity curentActivity = curentProject.searchActivity(activityName);
	    curentActivity.setTimeFrame(startWeek, endWeek);
	}

	@Then("The activity is given a time frame with start week {int} and end week {int}")
	public void the_activity_is_given_a_time_frame_with_start_week_and_end_week(Integer startWeek, Integer endWeek) {
		Project curentProject = schedulingApp.searchProject(projectName);
	    Activity curentActivity = curentProject.searchActivity(activityName);
		int[] timeframe = curentActivity.getTimeframe();
		assertTrue(timeframe[0] == startWeek && timeframe[1] == endWeek);
	}
	
	@When("The project leader sets an illegible time frame")
	public void the_project_leader_sets_an_illegible_time_frame() {
		Project curentProject = schedulingApp.searchProject(projectName);
		Activity curentActivity = curentProject.searchActivity(activityName);
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
