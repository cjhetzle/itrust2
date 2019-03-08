#Author kpresle
#Author ncluther
Feature: Edit demographics
	As an HCP
	I want to edit a patient's demographics
	So that the patient's information is updated

Scenario: Add patient demographics
Given patient exists
When I log in as an hcp
When I navigate to the Edit Patient Demographics page
When I select a patient
When I fill in the patients new, updated demographics
Then The patients demographics are updated