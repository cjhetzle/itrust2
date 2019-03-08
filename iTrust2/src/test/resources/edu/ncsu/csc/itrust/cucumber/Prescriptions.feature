#Author Bryan Gonzalez

Feature: Prescription
	As an iTrust2 Admin
	I want to add a prescription to the system
	So that HCP's can use it when documenting office visits

Scenario Outline: Valid- Admin adds drug
Given I am logged into iTrust2 as an Admin
When I navigate to the Add Drug page
And enter Prescription: <Prescription> and ID: <id> click submit
Then the prescription has successfully been added

Examples:
	| Prescription                  | id             |
	| Atomoxetine hydrochloride     | 0002-3229-99   |
	| Ibuprofen                     | 1111-8989-99   |
	| Vicodin                       | 1221-6738-23   |
	| Caeffine                      | 1566-6662-99   |
	| Steriods                      | 1166-7662-99   |
	| Pregabalin                    | 2679-2732-99   |	

Scenario Outline: Valid- HCP adds drug to patient
Given I am logged into iTrust2 as an HCP
When I navigate to the Assign Prescription page
And enter Drug: <Drug> and Patient: <Patient> and Dosage: <Dosage> and Renewals: <Renewals> and Start Date: <Start Date> and End Date: <End Date> and Notes: <Notes> click Submit Prescription
Then the prescription has been added to the patient

Examples:
	| Drug                                      | Patient                    | Dosage    | Renewals  | Start Date  | End Date   | Notes                     |
	| Atomoxetine hydrochloride \| 0002-3229-99 | antti \| Antti Walhelm     | 2         | 2         | 02/11/2007  | 04/11/2007 | Drink with orange juice   |

Scenario Outline: Valid- HCP deletes prescription
Given I am logged into iTrust2 as an HCP
When I navigate to the Delete Prescription page
And find Prescription: <Drug> click delete
Then the prescription has successfully been deleted

Examples:
	| Drug                                           |
	| antti \| 0002-3229-99 \| 2/11/2007 - 4/11/2007 |

Scenario Outline: Invalid- HCP adds prescription to patient
Given I am logged into iTrust2 as an HCP
When I navigate to the Assign Prescription page
And enter Drug: <Drug> and Patient: <Patient> and Dosage: <Dosage> and Renewals: <Renewals> and Start Date: <Start Date> and End Date: <End Date> and Notes: <Notes> click Submit Prescription
Then the Prescription was not added

Examples:
	| Drug                                        | Patient                    | Dosage    | Renewals  | Start Date       | End Date   | Notes                     |
	| Atomoxetine hydrochloride \| 0002-3229-99   | antti \| Antti Walhelm     | 2         | 2         | milk             | 04/11/2007 | Drink with orange juice   |
	| Vicodin \| 1221-6738-23                     | antti \| Antti Walhelm     | 2         | 2         | 02/11/2007       | milk       | Drink with orange juice   |

Scenario Outline: Valid- Admin deletes prescription
Given I am logged into iTrust2 as an Admin
When I navigate to the Delete Drug page
And find Prescription: <Drug> click delete
Then the drug has successfully been deleted

Examples:
	| Drug                          |
	| Ibuprofen \| 1111-8989-99     |
	| Steriods \| 1166-7662-99      |
