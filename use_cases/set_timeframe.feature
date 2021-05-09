# Author: Emil Krarup
Feature: Set Timeframe
	Description: The project leader sets a timeframe for a given activity
	Actor: Worker
	
Background:
	Given There is a worker who is project leader for a given project
	
Scenario: The project is given a time frame
	# Mads Harder
	When the project is given the time frame start year 2021 start week 1 end year 2021 end week 5
	Then the project have a time frame with start year 2021 start week 1 end year 2021 end week 5

Scenario: The project is given an illegible  time frame
	# Mads Harder
	When the project is given an illegible date as a time frame
	Then The error message "The given date is not eligible to set time frame"
	
Scenario: The project is given a end date before the start date
	# Mads Harder
	When the project is given a end date before the start date
	Then The error message "The end date before the start date"

Scenario: The project leader sets a time frame for a project activity
	Given There is a project activity
	When The project leader sets a time with current year, start week 1 and end week 5 for an activity
	Then The activity is given a time frame with current year, start week 1 and end week 5

Scenario: The project leader sets an illegible date as time frame for a project activity
	Given There is a project activity
	When The project leader sets an illegible time frame
	Then The error message "The given date is not eligible to set time frame"

Scenario: The project activity is given a end date before the start date
	# Mads Harder
	Given There is a project activity
	When the project activity is given a end date before the start date
	Then The error message "The end date before the start date"