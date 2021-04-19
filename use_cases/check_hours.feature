#Author: Lucas Maack s204439
Feature: Check hours
	Description: The project leader checks the registered amount of hours of project to get an overview 
	Actor: Worker
	
Background:
	Given there is a worker with ID "LMAA"
	And a project with ID 150905
	And "LMAA" is assigned to the project
	And there is a project activity with 10 hours spent
	And there is a non-project activity with 5 hours spent
	And the worker has a total of 30 hours spent that week


Scenario: Check registered hours on a project activity
	When the user checks the project activity
	Then the user is informed that 10 hours have been spent on the activity
	
Scenario: Check registered hours on a non-project activity
	When the user checks the non-project activity
	Then the user is informed that 5 hours have been spent on the activity
	
Scenario: Check registered hours of a worker
	When the user checks the worker's hours
	Then the user is informed that the worker has worked 30 hours have been spent on the activity

Scenario: Check total registered time for a project
	Given there is another project activity with 15 hours spent
	When the user checks the project overview for project with ID 150905
	Then the user is informed that total project time spent is 25 hours over 2 projects