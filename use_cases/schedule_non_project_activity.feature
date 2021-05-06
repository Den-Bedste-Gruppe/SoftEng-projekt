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
