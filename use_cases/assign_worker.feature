#Author: Lucas Maack s204439
#Feature: Assign Worker
#	Description: The Project leader assigns a worker to a project activity or worker assigns itself to activity.
#	Actors: Worker
#	
#Background: 
#	Given there is a worker with ID "LMAA"
#
#Scenario: Add worker to a project activity
#	Given that there is a project activity
#	When the Project leader assigns the worker with ID "LMAA" to the activity
#	Then the worker with ID "LMAA" is assigned to the activity
#	
#	
#Scenario: Worker adds itself to a non-project activity
#	Given that there is a non-project activity
#	When the worker assigns itself to the activity
#	Then the worker with ID "LMAA" is assigned to the activity
#
#
#Scenario: Add a worker who has over 20 activities the given week
#	Given that there is a project activity
#	And the worker has 20 activities this week
#	When the Project leader assigns the worker to the activity
#	Then the Project leader is informed that the worker with ID "LMAA" is busy
#
#
#Scenario: Add a worker who does not exist to a project activity
#	Given that there is a project activity
#	When the Project leader assigns the worker with ID "MLJE" to the activity
#	Then the Project leader is informed that no worker with the initials "MLJE" exists