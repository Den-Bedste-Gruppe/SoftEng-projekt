#Philip Hviid
Feature: Request assistance:
  Description: A worker request assistance on an activity from another worker
  Actor: worker

Background:
  Given a worker is logged in


Scenario: Worker requests assistance:
  Given the worker is on an activity
  And there is another worker
  When the user requests assistance
  Then the other worker has a request for assistance

Scenario: Worker requests assistance from worker not in system
  Given the worker is on an activity
  When the worker requests assistance from invalid signature
  Then "Signature not in system" Error is thrown

Scenario: Worker accepts request for assistrance
  Given the worker has a request for assistance
  When the worker accepts the requests
  Then the user is assigned to the activity

Scenario: Worker requests assistance:
  Given the worker is on an activity
  When the user requests assistance to himself
  Then the error message "cannot request assistance from yourself" is given

Scenario: Worker gets 2 requests for same activity:
  Given the worker has a request for assistance
  When another worker sends assist request for same activity
  Then the error message "Worker already has request for assistance on this activity" is given

Scenario: Worker is assigned to an activity, that he has a assist request for
  Given the worker has a request for assistance
  When worker is assigned to the on the same activity as he has a request for
  Then the request is removed
