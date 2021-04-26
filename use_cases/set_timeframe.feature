# Author: Emil Krarup
#Feature: Set Timeframe
#	Description: The project leader sets a timeframe for a given activity
#	Actor: Worker
#	
#Background:
#	Given There is a worker who is project leader for a given project
#
#Scenario: The project leader sets a time frame for a project activity
#	Given There is a project activity
#	When The project leader sets a time frame with start and end date for an activty
#	Then The activity is given a time frame with the dated start and end time.
#
#Scenario: The project leader sets an illegible date as time frame for a project activity
#	Given There is a project activity
#	And The project does not have a time frame
#	And The given date is illegible
#	When The project leader sets a time frame with the given date
#	Then The project should not recieve the given time frame
#	And The error message "The given date is not eligible to set time frame"