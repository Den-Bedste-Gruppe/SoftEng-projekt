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
	private int projectID;
	private ProjectActivity activity1;
	private ProjectActivity activity2;
	private double totalProjectHours;
	private int numOfActivities;
	
	public CheckHoursSteps(SchedulingApp schedulingApp) {
		this.schedulingApp = schedulingApp;
	}
	
	
	@Given("a project with ID {int}")
	public void aProjectWithID(Integer int1) throws Exception {
		projectID = int1;
		project = new Project(Integer.toString(projectID));
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

	
}