#Philip Hviid
Feature: Request assistance:
  Description: A worker request assistance on an activity from another worker
  Actor: worker

Background:
  Given a worker is logged in


Scenario: Worker requests assistance:
  And the worker is on an activity
  And there is another worker
  When the user requests assistance
  Then the other worker has a request for assistance

Scenario: Worker requests assistance from worker not in system
  And the worker is on an activity
  When the worker requests assistance from invalid signature
  Then "Signature not in system" Error is thrown

Scenario: Worker accepts request for assistrance
  Given the worker has a request for assistance
  When the worker accepts the requests
  Then the user is assigned to the activity

Scenario: Worker requests assistance:
  And the worker is on an activity
  When the user requests assistance to himself
  Then the error message "cannot request assistance from yourself" is given
