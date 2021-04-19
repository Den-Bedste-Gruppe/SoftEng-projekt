Feature: Worker Login
  Description: A worker logs in with signature
  Actors: Worker

Scenario: Worker logs in
  Given a worker exists
  And the worker is in the system
  When worker logs in
  Then worker is logged in

Scenario: Worker logs in with invalid signature
  Given a worker exists
  And the worker is not in the system
  When worker logs in
  Then worker is not logged in
  And "Signature not in system" Error is thrown