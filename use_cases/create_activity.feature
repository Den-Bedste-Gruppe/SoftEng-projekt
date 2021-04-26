#
# Author: Emil Krarup
#Feature: Create Activity
#	Description: The project leader creates an activity
#	Actors: Worker
#
#Scenario: Worker creates activity
#	Given A project exist
#	And Worker is the project leader of current project
#	When Worker creates an activity
#	Then An activity is created by the worker
#
#Scenario: Worker creates existing activity
#	Given A project exist
#	And Worker is the project leader of current project
#	And Type of activity already exist
#	When Worker creates the activity
#	Then The activity is not created by the worker
#	And the error message "The form of activity already exist" is given