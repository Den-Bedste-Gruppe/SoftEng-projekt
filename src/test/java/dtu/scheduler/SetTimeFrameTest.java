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
	Project testProject;

	public SetTimeFrameTest(SchedulingApp schedulingApp, ErrorMessageHolder errorMessageHolder) throws Exception {
		this.schedulingApp = schedulingApp;
		this.errorMessageHolder = errorMessageHolder;
		testProject = new Project(projectName);
	}
	
	@Given("There is a worker who is project leader for a given project")
	public void there_is_a_worker_who_is_project_leader_for_a_given_project() throws WorkerDoesNotExistException, ProjectAlreadyExistsException, ProjectDoesNotExistException {
		schedulingApp.logIn("ASDF");
		schedulingApp.addProject(testProject);
		schedulingApp.assignProjectLeader(testProject.getProjectID(), "ASDF");
	}

	@Given("There is a project activity")
	public void there_is_a_project_activity() throws Exception {
		schedulingApp.createProjectActivity(activityName, testProject.getProjectID());
	}
	
	// Mads Harder
	@When("the project is given the time frame start year {int} start week {int} end year {int} end week {int}")
	public void theProjectIsGivenTheTimeFrameStartYearStartWeekEndYearEndWeek(Integer startYear, Integer startWeek, Integer endYear, Integer endWeek) throws Exception {
		testProject.setTimeFrame(startYear, startWeek, endYear, endWeek);
	}

	// Mads Harder
	@Then("the project have a time frame with start year {int} start week {int} end year {int} end week {int}")
	public void theProjectHaveATimeFrameWithStartYearStartWeekEndYearEndWeek(Integer startYear, Integer startWeek, Integer endYear, Integer endWeek) {
		TimeFrame timeFrame = testProject.getTimeFrame();
		assertTrue(timeFrame.getStartYear() == startYear);
		assertTrue(timeFrame.getStartWeek() == startWeek);
		assertTrue(timeFrame.getEndYear() == endYear);
		assertTrue(timeFrame.getEndWeek() == endWeek);
	}
	
	// Mads Harder
	@When("the project is given an illegible date as a time frame")
	public void theProjectIsGivenAnIllegibleDateAsATimeFrame() {
		try {
			testProject.setTimeFrame(-2, -4, -2, -3);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	// Mads Harder
	@When("The project leader sets a time with current year, start week {int} and end week {int} for an activity")
	public void theProjectLeaderSetsATimeWithCurrentYearStartWeekAndEndWeekForAnActivity(Integer startWeek, Integer endWeek) throws Exception {
	    int currentYear = 2021;
	    ProjectActivity curentActivity = testProject.searchActivity(activityName);
	    curentActivity.setTimeFrame(currentYear, startWeek, currentYear, endWeek);
	}

	// Mads Harder
	@Then("The activity is given a time frame with current year, start week {int} and end week {int}")
	public void theActivityIsGivenATimeFrameWithCurrentYearStartWeekAndEndWeek(Integer startWeek, Integer endWeek) {
	    int currentYear = 2021;
	    ProjectActivity curentActivity = testProject.searchActivity(activityName);
		int[] timeframe = curentActivity.getTimeframe().getTimeFrame();
		assertTrue(timeframe[0] == currentYear);
		assertTrue(timeframe[1] == startWeek);
		assertTrue(timeframe[2] == currentYear);
		assertTrue(timeframe[3] == endWeek);
	}
	
	// Mads Harder
	@When("the project is given a end date before the start date")
	public void theProjectIsGivenAEndDateBeforeTheStartDate() {
		try {
			testProject.setTimeFrame(2026, 53, 2026, 52);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	// Mads Harder 
	@When("the project activity is given a end date before the start date")
	public void theProjectActivityIsGivenAEndDateBeforeTheStartDate() {
	    ProjectActivity curentActivity = testProject.searchActivity(activityName);
	    try {
	    	curentActivity.setTimeFrame(2026, 53, 2020, 53);
	    } catch (Exception e) {
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}
	
	@When("The project leader sets an illegible time frame")
	public void the_project_leader_sets_an_illegible_time_frame() {
		ProjectActivity curentActivity = testProject.searchActivity(activityName);
		try {
			curentActivity.setTimeFrame(2021, -2, 2021, 55);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage()); 
		}
	}

	@Then("The error message {string}")
	public void the_error_message(String errorMessage) {
	  assertTrue(errorMessageHolder.getErrorMessage().equals(errorMessage));
	}
}
