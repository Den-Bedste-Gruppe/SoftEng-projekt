#Author: Philip Hviid
Feature: Schedule nonproject activity
	Description: Worker schedules a nonproject activity
	Actors: Worker

Background:
  Given that a worker is logged in
  And that the worker has 0 nonproject registrations

Scenario: Worker schedules nonproject activity
  When worker schedules nonproject activity
  Then the nonproject activity is added to the workers activities
  And the nonproject registration is added

Scenario: Worker registers Sick Leave activity
	When worker schedules Sick Leave activity
	Then the Sick Leave activity is added to the workers activities
  And the nonproject registration is added

Scenario: Worker registers multiple Sick Leave activities
	Given worker has already has 5 Sick Lave activity
	When worker schedules Sick Leave activity
	Then the Sick Leave activity is added to the workers activities
  And the nonproject registration is added

Scenario: Worker registers multiple Sick Leave activities
	Given worker has already has 5 Sick Lave activity
	When worker schedules Sick Leave activity
	Then the Sick Leave activity is added to the workers activities
  And the nonproject registration is added

Scenario: Worker registers multiple Sick Leave activities
	Given worker has already has 5 Sick Lave activity
	When worker schedules Sick Leave activity
	Then the Sick Leave activity is added to the workers activities
  And the nonproject registration is added
