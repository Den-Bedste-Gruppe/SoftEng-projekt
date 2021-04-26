#Author: Kristian Knudsen s183779
#Feature: Change Hours
#	Description: The worker changes their registered amount of hours on an activity
#	Actor: Worker
#
#Background:
#	Given that a worker is logged in
#	And there is a project activity
#	And the worker registers 5 hours on the activity
#	And the activity adds 5 hours to the total time spent
#	And the worker adds 5 hours to the time spent that week
#
#Scenario: The worker changes their hours on the activity to a valid amount
#	Given the activity has a total of 5 hours spent
#	And the worker has a total of 5 hours spent that week
#	When the worker changes the amount of hours on the activity to 2 hours
#	Then the activity has a total of 2 hours spent
#	And the worker has a total of 2 hours spent that week
#
#Scenario: The worker changes their hours on the activity to a negative amount
#	When the worker changes the amount of hours on the activity to -2 hours
#	Then the error message "Invalid amount of hours" is given
#
#Scenario: The worker changes their hours on the activity to zero hours
#	When the worker changes the amount of hours on the activity to 0 hours
#	Then the error message "Invalid amount of hours" is given
#
#Scenario: The worker changes their hours on the activity to more than 24 hours
#	When the worker changes the amount of hours on the activity to 25 hours
#	Then the error message "Invalid amount of hours" is given