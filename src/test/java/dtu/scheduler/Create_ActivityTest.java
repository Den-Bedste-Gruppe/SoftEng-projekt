package dtu.scheduler;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;

public class Create_ActivityTest {
	private Activity hello, world;
	private List<Activity> activities = new ArrayList<>();
	
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
		hello= new Activity(); 
		// Write code here that turns the phrase above into concrete actions
	}
	
	@Then("An activity is created by the worker")
	public void the_activity_is_created() {
	    activities.add(hello);
		
	}
	
	@And("Activity with the name {string} exist")
	public void activity_already_exist(String name) {

		assert(true) ;
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@When("Worker creates activity with name {string}")
	public void worker_create_existing_activity(String name) {
	    // Write code here that turns the phrase above into concrete actions
		//world = new Activity(name);
		activities.add(world= new Activity(name));
		
	}
	
	@Then("The activity is not created by the worker")
	public void activity_not_created() {
		
		if(world.equals(hello)) {
			activities.remove(world);
		}
		// change code so it removes activity instead of resetting it. 

	}
	
	@And("the error message {string} is given")
	public void error_activity_message (String errorMessage) {
		System.out.println(errorMessage);
		//throw new io.cucumber.java.PendingException(errorMessage);
		//throw new IllegalArgumentException(errorMessage);
		
	

	}
	
	
}
