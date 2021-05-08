#Author: Philip Hviid
Feature: Schedule nonproject activity
	Description: Worker schedules a nonproject activity
	Actors: Worker

Background:
  Given that a worker is logged in
  And that the worker has 0 nonproject registrations
	And that the worker has 0 nonproject activities

Scenario: Worker schedules nonproject activity
  When worker schedules nonproject activity
  Then the nonproject activity is added to the workers activities
  And the nonproject registration is added

Scenario: Worker schedules nonproject activity without name
	When worker schedules nonproject activity without name
	Then no nonproject activity is added to the workers activities
	And no nonproject registration is added
	And the error message "activity cannot be created without name" is given

Scenario: Worker registers Sick Leave activity
	When worker schedules "Sick Leave" activity
  Then the nonproject activity is added to the workers activities
  And the nonproject registration is added

Scenario: Worker registers multiple Sick Leave activities
	Given worker already has 5 "Sick Leave" activities
	When worker schedules "Sick Leave" activity
  Then the nonproject activity is added to the workers activities
  And the nonproject registration is added

Scenario: Worker registers multiple Vacation activities
	Given worker already has 5 "Vacation" activities
	When worker schedules "Vacation" activity
  Then the nonproject activity is added to the workers activities
  And the nonproject registration is added

Scenario: Worker registers multiple Course activities
	Given worker already has 5 "Course" activities
	When worker schedules "Course" activity
  Then the nonproject activity is added to the workers activities
  And the nonproject registration is added
