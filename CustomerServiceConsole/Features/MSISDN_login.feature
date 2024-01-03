@CSC
Feature: MSISDN Login page

Background: Below are the common steps for each scenarios
	Given User Launch Chrome browser
	When User Opens URL "http://135.115.146.57:8080/csc/"
	And User enters Username as "consoleuser" and Password as "consoleuser1"
	And Click on SIGNIN

@TMOSMP-38911
Scenario: Login to CSC with Valid MSISDN and navigates page with list of FAPs with serial numbers under that MSISDN under different states of provisioning
	When Subscriber enters MSISDN "9998888126"
	And click on Create Session button
	Then Page Title should be "MSISDN - Customer Service Console"
	And List all the Serial Numbers
	And Close Browser

@TMOSMP-38904
Scenario Outline: Verify with invalid inputs like invalid MSISDN, more digits, other alphanumeric characters for appropriate error message thrown upon clicking CREATE SESSION
	When Subscriber enters Invalid MSISDN as "<MSISDN>"
	And click on Create Session button
	Then User should see the appropriate error message 
	And Close Browser
	
Examples: 
| MSISDN |
| msisdn |
| 99988881261 |
| 999888812A |
| X99888812A |
| !999888812 |
| |

@TMOSMP-38903
Scenario: Verify user is allowed to enter the 10 digit MSISDN in the box and click on CREATE SESSION
	When Subscriber enters MSISDN "9998888126"
	And click on Create Session button
	Then Page Title should be "MSISDN - Customer Service Console"
	And List all the Serial Numbers
	And Close Browser

@TMOSMP-38900
Scenario: Verify CSC login page takes you to Subscriber Id Page.
Then Page Title should be "MSISDN - Customer Service Console"
And Close Browser

@TMOSMP-38908
Scenario: Login to CSC with MSISDN and Verify User name and LOGOFF buttons are visible on top right on on Subscriber Id Page
	And User name and Logoff button are visible at top-right corner
	And Close Browser
	
@TMOSMP-38907
Scenario: Login to CSC with MSISDN and Verify on Subscriber Id Page has the T Mobile Logo, CSC expansion and build info on top
	And TMO Logo, CSC text and build info are visible at top
	And Close Browser