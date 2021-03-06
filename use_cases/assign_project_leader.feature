# Mads Harder s204473
Feature: Assign project leader
	Description: A worker assign a project leader to a project
	Actor: Worker

Scenario: Assign project leader
	Given that a worker is logged in with id "ASDF"
	And there exists a project with name "P1"
	When a worker assigns the project "P1" a project leader
	Then the project "P1" has a project leader with id "ASDF"


Scenario: Change project leader
	Given that a worker is logged in with id "ASDF"
	And there is a project with name "P1"
	And the project "P1" has a project leader
	When a worker changes the project leader on project "P1"
	Then the project "P1" has a project leader with id "TEST"