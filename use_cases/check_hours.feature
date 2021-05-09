#Author: Lucas Maack s204439
Feature: Check hours
	Description: The project leader checks the registered amount of hours of project to get an overview
	Actor: Worker

Background:
	Given there is a worker with ID "LMAA"
	And that the worker with ID "LMAA" is logged in
	And a project with ID 150905

Scenario: Check total registered time for a project
	Given there is a project activity with 10 hours spent
	And there is another project activity with 15 hours spent
	When the user checks the project overview for project with ID 150905
	Then the user is informed that total project time spent is 25 hours over 2 projects


## These became redundant after register_hours.feature
#Scenario: Check registered hours on a project activity
#	Given there is a project activity with 7 hours spent
#	When the user checks the project activity
#	Then the user is informed that 7 hours have been spent on the activity
#
#Scenario: Check registered hours on a non-project activity
#	Given there is a non-project activity with 5 hours spent
#	When the user checks the non-project activity
#	Then the user is informed that 5 hours have been spent on the activity
#
#Scenario: Check registered hours of a worker
#	Given the worker has a total of 30 hours spent that week
#	When the user checks the worker's hours
#	Then the user is informed that the worker has worked 30 hours have been spent on the activity
#
