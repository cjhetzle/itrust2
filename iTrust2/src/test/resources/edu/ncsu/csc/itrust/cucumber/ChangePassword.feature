#Author jmphipps
Feature: Password Change and Reset
	As a user I want to be able to change my password
	So that I can protect my account Security
	
Scenario: Patient changes their password 
	Given patient is logged in and clicks the link to change their password
	When they fill in their old password and their new one twice and submit
	Then their password is successfully changed
	
Scenario: Patient changes their password improperly
	Given patient is logged in and clicks the link to change their password
	When they fill in their old password improperly and their new one twice and submit
	Then their password is not successfully changed