# Mads Harder s204473
Feature: Assign project leader
	Description: A worker assign a project leader to a project
	Actor: Worker

Scenario: Assign project leader
	Given that a worker is logged in
	And there is a project with the name "P1"
	When a worker assign the project "P1" a project leader
	Then the project "p1" and a project leader

Scenario: Change project leader
	Given that a worker is logged in
	And there is a project with the name "P1"
	And the project "p1" has a project leader
	When a worker change the project leader on project "p1"
	Then the project "p1" has a new project leader