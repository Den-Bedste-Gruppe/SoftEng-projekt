#Author: Kristian Knudsen s183779
Feature: Register Hours
	Description: The worker registers hours spent on an activity
	Actor: Worker

Background:
	Given that a worker is logged in
	And that the worker has 0 hours spent that week

Scenario: The worker registers some hours spent on an activity
	Given that there is a project activity
	And that the activity has 0 hours spent
	When the worker registers 5 hours on the activity
	Then the activity has a total of 5 hours spent
	And the worker has a total of 5 hours spent that week

Scenario: The worker registers a negative amount of hours on an activity
	Given that there is a project activity
	When the worker registers -2 hours on the activity
	Then the error message "Invalid amount of hours" is given

Scenario: The worker registers zero hours on an activity
	Given that there is a project activity
	When the worker registers 0 hours on the activity
	Then the error message "Invalid amount of hours" is given

Scenario: The worker registers more than 24 hours on an activity
	Given that there is a project activity
	When the worker registers 25 hours on the activity
	Then the error message "Invalid amount of hours" is given