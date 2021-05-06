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

Scenario: Worker registers nonproject activity
	Given that worker is on a nonproject activity
	And that the worker has 0 nonproject registrations
	And that the worker has 0 hours spent that week
	When the worker registers nonproject activity
	Then the worker has a total of 0 hours spent that week
	And the nonproject registration is added
