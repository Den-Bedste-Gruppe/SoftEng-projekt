#Author: Emil Krarup
Feature: Create Activity
	Description: The project leader creates an activity
	Actors: Worker
	
Background: 
	Given that a worker is logged in

Scenario: Worker creates activity
	Given A project exists
	And Worker is the project leader of current project
	When Worker creates an activity
	Then An activity is created by the worker

Scenario: Worker creates existing activity
	Given A project exists
	And Worker is the project leader of current project
	And Activity with same name already exists
	When Worker creates the activity
	Then The activity is not created by the worker
	And the error message "Project with same name already exists in project" is given