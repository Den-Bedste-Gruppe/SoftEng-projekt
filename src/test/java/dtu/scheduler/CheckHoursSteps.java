package dtu.scheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import dtu.errors.ProjectAlreadyExistsException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckHoursSteps {
	
	private SchedulingApp schedulingApp;
	private Project project;
	private String projectID;
	private ProjectActivity activity1;
	private ProjectActivity activity2;
	private double totalProjectHours;
	private int numOfActivities;
	
	public CheckHoursSteps(SchedulingApp schedulingApp) {
		this.schedulingApp = schedulingApp;
	}
	
	
	@Given("a project with ID {int} and project leader {string}")
	public void aProjectWithIDAndProjectLeader(Integer int1, String workerId) throws Exception {
		projectID = Integer.toString(int1);
		Worker worker = schedulingApp.getWorkerById(workerId);
		project = new Project(projectID, worker);
	    schedulingApp.addProject(project);
	}


	@Given("there is a project activity with {double} hours spent")
	public void thereIsAProjectActivityWithHoursSpent(double hours1) throws Exception {
		activity1 = new ProjectActivity("Clean up Kanban Board", project);
		double diff1 = hours1 - activity1.getTotalHoursSpent();
	    project.addActivity(activity1);
	    schedulingApp.registerHours(hours1, activity1);
	}
	
	@Given("there is another project activity with {double} hours spent")
	public void thereIsAnotherProjectActivityWithHoursSpent(double hours2) throws Exception {
		activity2 = new ProjectActivity("Finish GUI", project);
		double diff2 = hours2 - activity2.getTotalHoursSpent();
	    project.addActivity(activity2);
	    schedulingApp.registerHours(hours2, activity2);
	}

	@When("the user checks the project overview for project with ID {int}")
	public void theUserChecksTheProjectOverviewForProjectWithID(Integer int1) {
	    totalProjectHours = project.getProjectHours();
	    numOfActivities = project.getNumOfProjectActivities();
	}

	@Then("the user is informed that total project time spent is {double} hours over {int} projects")
	public void theUserIsInformedThatTotalProjectTimeSpentIsHoursOverProjects(double totalHours, Integer int1) {
		assertTrue(totalHours == totalProjectHours);
		assertTrue(int1 == numOfActivities);
	}

	// Mads Harder
	@Given("there is a project activity with the name {string} with {int} hours timebudget and {int} hours spent")
	public void thereIsAProjectActivityWithTheNameWithHoursTimebudgetAndHoursSpent(String activityName, Integer TimebudgetHours, Integer hours) throws Exception {
		activity1 = new ProjectActivity(activityName, project);
		project.addActivity(activity1);

		schedulingApp.setBudgetedTime(TimebudgetHours, activity1, project);
		schedulingApp.registerHours(hours, activity1);
	}

	// Mads Harder
	@Given("there is another project activity with the name {string} with {int} hours timebudget and {int} hours spent")
	public void thereIsAnotherProjectActivityWithTheNameWithHoursTimebudgetAndHoursSpent(String activityName, Integer TimebudgetHours, Integer hours) throws Exception {
		activity2 = new ProjectActivity(activityName, project);
		project.addActivity(activity2);

		schedulingApp.setBudgetedTime(TimebudgetHours, activity2, project);
		schedulingApp.registerHours(hours, activity2);
	}

	// Mads Harder
	@Then("the user activity with name {string} has {int} hours spent out of {int}, and activity with name {string} has {int} hours spent out of {int}")
	public void theUserActivityWithNameHasHoursSpentOutOfAndActivityWithNameHasHoursSpentOutOf(String act1, Integer hours1, Integer timebudgeHours1, String act2, Integer hours2, Integer timebudgeHours2) {
		double hoursSpend1 = activity1.getTotalHoursSpent();
		double hoursSpend2 = activity2.getTotalHoursSpent();
		
		double budgettimeSpend1 = activity1.getBudgetedTime();
		double budgettimeSpend2 = activity2.getBudgetedTime();
		
		assertTrue(hoursSpend1 == hours1);
		assertTrue(hoursSpend2 == hours2);
		assertTrue(budgettimeSpend1 == timebudgeHours1);
		assertTrue(budgettimeSpend2 == timebudgeHours2);
	}
}