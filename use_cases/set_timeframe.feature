# Author: Emil Krarup
Feature: Set Timeframe
	Description: The project leader sets a timeframe for a given activity
	Actor: Worker
	
Background:
	Given There is a worker who is project leader for a given project

Scenario: The project leader sets a time frame for a project activity
	Given There is a project activity
	When The project leader sets a time frame with start week 1 and end week 5 for an activity
	Then The activity is given a time frame with start week 1 and end week 5

Scenario: The project leader sets an illegible date as time frame for a project activity
	Given There is a project activity
	When The project leader sets an illegible time frame
	Then The error message "The given date is not eligible to set time frame"