# Mads Harder s204473
#Feature: Create project
#	Description: The worker creates a new project
#	Actor: Worker
#
#Scenario: Create a new project
#	Given that a worker is logged in
#	When the worker creates a new project
#	Then the project is created
#
#Scenario: Create a new project with name
#	Given that a worker is logged in
#	When the worker creates a new project with name "P1"
#	Then there is a project with the name "P1"
#
#Scenario: Create a project with the same name as an existing project
#	Given that a worker is logged in
#	And there is a project with the name "P1"
#	When the worker creates a new project with name "P1"
#	Then the error message "Project already exist" is given
#	