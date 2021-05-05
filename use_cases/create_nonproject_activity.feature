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
