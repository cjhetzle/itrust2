Feature: Add Diagnosis
	As an iTrust2 Admin
	I want to add a diagnosis to the system
	So that HCP's can use it when documenting office visits

Scenario Outline: Valid Admin adds a diagnosis to the list
Given that admin is logged in
When I navigate to the Add Diagnosis page
When I enter name: <name> and ICD code: <icd> and click submit
Then the diagnosis is added successfully

Examples:
	| name             | icd    |
	| flu              | J10    |
	| Struck by duck   | W61.62 |
	| Bipolar Disorder | F31    |
	
Scenario: Admin deletes diagnosis
Given that admin is logged in
When I navigate to the Add Diagnosis page
When I add a diagnosis and go to the delete page
When I select the diagnosis I added and click delete
Then the diagnosis is deleted

Scenario Outline: Invalid Admin adds a diagnosis to the list
Given that admin is logged in
When I navigate to the Add Diagnosis page
When I enter name: <name> and ICD code: <icd> and click submit
Then the diagnosis is not added

Examples:
	| name             | icd          |
	| flu              | 10J          |
	| Struck by duck   | J4           |
	| no letters       | 59           |
	| no numbers       | B            |
	| Bipolar Disorder | !@#$^#%#%#%# |

Scenario: Patient views diagnosis
Given patient is logged in and has an office visit with a diagnosis on record
When I navigate to the View Diagnosis page
When I select an office visit in the list of visits with diagnoses
Then the diagnosis information should be visible

Scenario: HCP edits/views a diagnosis
Given HCP is logged in and who has documented an office visit
When I navigate to the Edit Office Visit page
When I select an office visit in the list and change the diagnosis and submit
Then the diagnosis for the office visit is updated