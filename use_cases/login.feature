Feature: Worker Login
  Description: A worker logs in with signature
  Actors: Worker

Scenario: Worker logs in
  Given a workerID exists
  And the workerID is in the system
  When worker logs in
  Then worker is logged in

Scenario: Worker logs in with invalid signature
  Given a workerID exists with signature "ASBR"
  And the workerID is not in the system
  When worker logs in
  Then worker with ID "ASBR" is not logged in
  And "Signature not in system" Error is thrown
  
