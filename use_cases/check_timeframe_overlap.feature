#Author: Philip Hviid
Feature: Check timeframe overlap
	Description: A project leader checks timeframe overlap of worker and activity
  of a projectactivity
	Actors: Worker

Background:
	Given that a worker is logged in
  And A project exists

Scenario: User checks timeframe overlap of worker with no overlap
  Given Worker is the project leader of current project
  And worker "ZXCV" is on projectactivity with start year 2021, start week 1, endyear 2021 and endweek 2
  And there is a projectactivity with start year 2021, start week 3, endyear 2021 and endweek 6
  When the user checks availibility of worker "ZXCV" for the projectactivity
  Then 0 projectactivity overlaps and 0 nonprojectactivity overlaps are returned

Scenario: User checks timeframe overlap of worker with full overlap
  Given Worker is the project leader of current project
  And worker "ZXCV" is on projectactivity with start year 2021, start week 1, endyear 2021 and endweek 9
  And there is a projectactivity with start year 2021, start week 3, endyear 2021 and endweek 6
  When the user checks availibility of worker "ZXCV" for the projectactivity
  Then 1 projectactivity overlaps and 0 nonprojectactivity overlaps are returned

Scenario: User checks timeframe overlap of worker with partial overlap
  Given Worker is the project leader of current project
  And worker "ZXCV" is on projectactivity with start year 2021, start week 2, endyear 2021 and endweek 5
  And there is a projectactivity with start year 2021, start week 3, endyear 2021 and endweek 6
  When the user checks availibility of worker "ZXCV" for the projectactivity
  Then 1 projectactivity overlaps and 0 nonprojectactivity overlaps are returned

Scenario: User checks timeframe overlap of worker with nonprojectactivity overlap
  Given Worker is the project leader of current project
  And worker "ZXCV" is on nonprojectactivity with start year 2021, start week 2, endyear 2021 and endweek 5
  And there is a projectactivity with start year 2021, start week 3, endyear 2021 and endweek 6
  When the user checks availibility of worker "ZXCV" for the projectactivity
  Then 0 projectactivity overlaps and 1 nonprojectactivity overlaps are returned

Scenario: User checks timeframe overlap of busy worker and activity
  Given Worker is the project leader of current project
  And worker "ZXCV" is on projectactivity with start year 2021, start week 2, endyear 2021 and endweek 9
  And worker "ZXCV" is on projectactivity with start year 2021, start week 4, endyear 2021 and endweek 5
  And worker "ZXCV" is on projectactivity with start year 2021, start week 1, endyear 2021 and endweek 2
  And worker "ZXCV" is on nonprojectactivity with start year 2021, start week 2, endyear 2021 and endweek 5
  And there is a projectactivity with start year 2021, start week 3, endyear 2021 and endweek 6
  When the user checks availibility of worker "ZXCV" for the projectactivity
  Then 2 projectactivity overlaps and 1 nonprojectactivity overlaps are returned
