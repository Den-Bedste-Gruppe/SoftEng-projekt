#Author: Philip Hviid
Feature: Create nonproject activity
	Description: Worker creates an activity
	Actors: Worker

Background:
	Given that a worker is logged in

Scenario: Worker creates nonproject activity
	Given that a nonproject activity exists
	When the worker creates a nonproject activity
	Then the nonproject activity is added to the workers activities

Scenario: Worker registers time on nonproject activity
	Given that worker is on a nonproject activity
	And that the worker has 0 nonproject time registrations
	And that the worker has 0 hours spent that week
	When the worker registers 10 hours on the nonproject activity
	Then the worker has a total of 0 hours spent that week
	And the nonproject timeregistration is added

Scenario: Worker registers negative amounth of hours on nonproject Activity
	Given that worker is on a nonproject activity
	And that the worker has 0 nonproject time registrations
	When the worker registers -10 hours on the nonproject activity
	Then the error message "Invalid amount of hours" is given
	And no project is added

Scenario: Worker registers more than 24 amounth of hours on nonproject Activity
	Given that worker is on a nonproject activity
	And that the worker has 0 nonproject time registrations
	When the worker registers 30 hours on the nonproject activity
	Then the nonproject timeregistration is added
