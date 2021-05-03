Feature: Request assistance:
  Description: A worker request assistance on an activity from another worker
  Actor: worker

Scenario: Worker requests assistance:
  Given a worker is logged in
  And the worker is on an activity
  And there is another worker
  When the user requests assistance
  Then the other worker has a request for assistance

Scenario: Worker requests assistance from worker not in system
  Given a worker is logged in
  And the worker is on an activity
  When the worker requests assistance from invalid signature
  Then "Signature not in system" Error is thrown