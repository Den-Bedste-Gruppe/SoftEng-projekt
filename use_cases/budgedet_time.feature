# Philip Hviid
Feature: Worker Login
  Description: A woker sets budgeted time on activity
  Actors: Worker

Background:
  	Given that a worker is logged in
    And There is a project with an activity


Scenario: Worker sets budgeted time on activity
    Given Worker is the project leader of current project
    When the worker sets budgeted time of 8 hours
    Then the activity has a budgeted time of 8 hours

Scenario: Worker who is not project leader tries to set budgeted time on activity
    When the worker sets budgeted time of 8 hours
    Then the error message "only project leader can assign budgeted time" is given

Scenario: Worker sets negative budgeted time on activity
    Given Worker is the project leader of current project
    When the worker sets budgeted time of -8 hours
    Then the error message "budgeted time must be positive integer" is given
