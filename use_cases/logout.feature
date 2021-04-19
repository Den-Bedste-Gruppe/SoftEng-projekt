Feature: Worker Logout
  Description: A worker logs out
  Actors: Worker

Scenario: Worker logs out
  Given a worker is logged in
  When worker logs out
  User is no longer logged in