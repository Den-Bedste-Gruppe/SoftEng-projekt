#Author: Lucas Maack s204439
Feature: Assign Worker
	Description: The Project leader assigns a worker to a project activity or worker assigns itself to activity.
	Actors: Worker

Background:
	Given there is a worker with ID "LMAA"
	And that the worker with ID "LMAA" is logged in

Scenario: Add worker to an activity
	Given that there is an activity
	When the user assigns themselves to the activity
	Then the user is assigned to the activity

Scenario: Add a worker who does not exist to an activity
	Given that there is an activity
	When the user assigns a user with ID "MLJE" to the activity
	Then the user is informed that no worker with the initials "MLJE" exists
