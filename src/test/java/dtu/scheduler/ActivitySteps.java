package dtu.scheduler;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dtu.errors.WorkerDoesNotExistException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



//Philip Hviid
public class ActivitySteps {
	private String targetWorkerId;
	private SchedulingApp schedulingApp;
	private ErrorMessageHolder msg;
	private ActivityAssigner activityAssigner;
	private ProjectActivity activity;
	private int amounthOfNonProjectRegistrations;
	private NonProjectActivity nonProjectActivity;
	private Project project;
	private String projectID;
	private int activityCount = 0;
	private int[] overlaps;

	
	public ActivitySteps(SchedulingApp app, ErrorMessageHolder msg, ActivityAssigner activityAssigner) {
		this.schedulingApp = app;
		this.msg = msg;
		this.activityAssigner = activityAssigner;
	}
	
	@Given("the worker is on an activity")
	public void theWorkerIsOnAnActivity() throws Exception {
		String currUser = schedulingApp.getCurrentUserID();
		activity = new ProjectActivity("Test", project);
		schedulingApp.assignActivity(currUser, activity);
	}

	@Given("there is another worker")
	public void thereIsAnotherWorker() {
	    targetWorkerId = "QWER";
	}

	@When("the user requests assistance")
	public void theUserRequestsAssistance() throws WorkerDoesNotExistException {
	    schedulingApp.requestAssistance(activity, targetWorkerId);
	}

	@Then("the other worker has a request for assistance")
	public void theOtherWorkerHasARequestForAssistance() throws WorkerDoesNotExistException {
	    assertTrue((schedulingApp.getWorkerRequests(targetWorkerId).size()==1));
	}

	@When("the worker requests assistance from invalid signature")
	public void theWorkerRequestsAssistanceFromInvalidSignature() {
	    try {
			schedulingApp.requestAssistance(activity, "ASDFG");
		} catch (WorkerDoesNotExistException e) {
			msg.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("A project exists")
	public void aProjectExists() throws Exception {
		project = new Project("P1");
		projectID = project.getProjectID();
	    schedulingApp.addProject(project);
	    assertTrue(project.getActivities().size()==0);
	}

	@Given("Worker is the project leader of current project")
	public void workerIsTheProjectLeaderOfCurrentProject() throws Exception {
	    schedulingApp.assignProjectLeader(projectID,schedulingApp.getCurrentUserID());
	}

	@When("Worker creates an activity")
	public void workerCreatesAnActivity() {
	    try {
			schedulingApp.createProjectActivity("act1", projectID);
		} catch (Exception e) {
			msg.setErrorMessage(e.getMessage());
		}
	}

	@Then("An activity is created by the worker")
	public void anActivityIsCreatedByTheWorker() {
	    assertTrue(project.getActivities().size()==1);
	}


	@Given("Activity with same name already exists")
	public void typeOfActivityAlreadyExist() throws Exception {
		schedulingApp.createProjectActivity("act1", projectID);
	}

	@When("Worker creates the activity")
	public void workerCreatesTheActivity() {
		try {
			schedulingApp.createProjectActivity("act1", projectID);
		} catch (Exception e) {
			msg.setErrorMessage(e.getMessage());
		}
	}

	@Then("The activity is not created by the worker")
	public void theActivityIsNotCreatedByTheWorker() {
	    assertFalse(project.getActivities().size()==2);
	}
	
	@When("Worker creates an activity without name")
	public void workerCreatesAnActivityWithoutName() {
	    try {
			schedulingApp.createProjectActivity("", project.getProjectID());
		} catch (Exception e) {
			msg.setErrorMessage(e.getMessage());
		}
	}
	
	@When("the worker sets budgeted time of {int} hours")
	public void theWorkerSetsBudgetedTimeOfHours(Integer hours) {
		try {
			schedulingApp.setBudgetedTime(hours, activity, project);
		} catch(Exception s) {
			msg.setErrorMessage(s.getMessage());
		}
	}

	@Then("the activity has a budgeted time of {int} hours")
	public void theActivityHasABudgetedTimeOfHours(Integer int1) {
	    assertTrue(activity.getBudgetedTime()==int1);
	}

	@Given("There is a project with an activity")
	public void thereIsAProjectWithAnActivity() throws Exception {
		project = new Project("P1");
		projectID = project.getProjectID();
	    schedulingApp.addProject(project);
	    assertTrue(project.getActivities().size()==0);
	    activity = new ProjectActivity("TestAct", project);
	    project.addActivity(activity);
	    assertTrue(project.getActivities().size()==1);
	}
	
	@Given("worker {string} is on projectactivity with start year {int}, start week {int}, endyear {int} and endweek {int}")
	public void workerIsOnProjectactivityWithStartYearStartWeekEndyearAndEndweek(String workerId, Integer startYear, Integer startWeek, Integer endYear, Integer endWeek) throws Exception {
		ProjectActivity tempActivity = new ProjectActivity("test" + activityCount, project);
		tempActivity.setTimeFrame(startYear, startWeek, endYear, endWeek);
	    project.addActivity(tempActivity);
	    schedulingApp.assignActivity(workerId, tempActivity);
	    activityCount++;
	    assertTrue(activityCount==schedulingApp.getWorkersProjectActivities(workerId).size());
	}

	@Given("there is a projectactivity with start year {int}, start week {int}, endyear {int} and endweek {int}")
	public void thereIsAProjectactivityWithStartYearStartWeekEndyearAndEndweek(Integer startYear, Integer startWeek, Integer endYear, Integer endWeek) throws Exception {
	    activity = new ProjectActivity("testact", project);
	    activity.setTimeFrame(startYear, startWeek, endYear, endWeek);
	    project.addActivity(activity);
	}

	@When("the user checks availibility of worker {string} for the projectactivity")
	public void theUserChecksAvailibilityOfWorkerForTheProjectactivity(String workerId) throws WorkerDoesNotExistException {
	    overlaps = schedulingApp.getOverLaps(workerId, activity);
	}

	@Then("{int} projectactivity overlaps and {int} nonprojectactivity overlaps are returned")
	public void projectactivityOverlapsAndNonprojectactivityOverlapsAreReturned(Integer projectOverlaps, Integer nonProjectOverlaps) {
//		System.out.println(overlaps[0]);
//		System.out.println(overlaps[1]);
	    assertTrue(overlaps[0] == projectOverlaps && overlaps[1] == nonProjectOverlaps);
	}
	
	@Given("worker {string} is on nonprojectactivity with start year {int}, start week {int}, endyear {int} and endweek {int}")
	public void workerIsOnNonprojectactivityWithStartYearStartWeekEndyearAndEndweek(String workerId, Integer startYear, Integer startWeek, Integer endYear, Integer endWeek) throws Exception {
		schedulingApp.addNonProjectActivity(workerId, startYear, startWeek, endYear, endWeek);
	    assertTrue(1==schedulingApp.getWorkersNonProjectActivities(workerId).size());
	}
	
	



}
