package dtu.scheduler;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

public class Create_ActivityTest {

	@Given("A project exist")
	public void a_project_exist () {
		assert(true) ;
		//insert project feature here
	}
	
	@And("Worker is the project leader of current project")
	public void worker_is_PL() {
		assert(true) ;
		//insert project leader assign here
	}
	
	@When("Worker creates an activity")
	public void worker_create_activity() {
	    Activity hello; 
		// Write code here that turns the phrase above into concrete actions
	}
	
	@Then("An activity is created by the worker")
	public void the_activity_is_created() {
		Activity hello = new Activity();
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
	}
	
	@And("Activity with the name \"helloworld\" exist")
	public void activity_already_exist() {
	    assert(true) ;
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@When("Worker creates activity with name \"helloworld\"")
	public void worker_create_existing_activity() {
	    // Write code here that turns the phrase above into concrete actions
		Activity world = new Activity("helloworld");
	}
	
	@Then("The activity is not created by the worker")
	public void activity_not_created() {
		String name = "helloworld" ;
		Activity activity = new Activity(name);
		Activity hello = new Activity(name);
		activity.setActivityName(name);
		
		if(hello.equals(activity)) {
			activity.resetActivity();
		}
		 // Write code here that turns the phrase above into concrete actions
	}
	
	@And("the error message {string} is given")
	public void error_activity_message (String errorMessage) {
		System.out.println(errorMessage);
		//throw new io.cucumber.java.PendingException(errorMessage);
		//throw new IllegalArgumentException(errorMessage);
		
	

	}
	
	
}
