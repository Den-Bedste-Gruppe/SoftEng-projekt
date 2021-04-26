#Feature: Request assistance:
#  Description: A worker request assistance on an activity from another worker
#  Actor: worker
#
#Scenario: Worker requests assistance:
#  Given 2 workers are in the system
#  And the first worker is logged in
#  And the first worker is on an activity
#  When the first worker requests assistance
#  Then the second worker has a request for assistance
#
#Scenario: Worker requests assistance from worker not in system
#  Given worker is logged in
#  And the worker is in the system
#  And the worker is on an activity
#  When the worker requests assistance
#  Then "Signature not in system" Error is thrown