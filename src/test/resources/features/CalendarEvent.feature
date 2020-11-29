@calendarEvents
Feature: Calendar Events test cases

  Background: Given user is on the Calendar events page
    Given user is on the login page
    And user logs in as a "store manager"
    And user navigates to "Activities" and "Calendar Events"

  @TC1
  Scenario: TC1 three dot check
    When user hovers on three dots for "Testers meeting"
    Then "View", "Edit" and "Delete" options are available


  @TC2
  Scenario: TC2 verify that Title column is displayed
    When user clicks on "Grid Settings"
    And unchecks all columns except "Title"
    Then "Title" column is displayed


  @TC3
  Scenario: TC3 verify that options "Save And Close", "Save And New"
  and "Save" options are available
    When user clicks on Create Calendar Event button
    And expand Save and close menu
    Then following option are available
        |Save And Close|
        |Save And New  |
        |Save          |

  @TC4
  Scenario: TC4 verify Cancel buttont in Create calendar Event window
    When user clicks on Create Calendar Event button
    And clicks on Cancel button
    Then All Calendar Event page subtitle is displayed

  @TC5
  Scenario: TC5 Verify that difference between end and start time is exactly 1 hour in Create Calendar event
    When user clicks on Create Calendar Event button
    Then difference between end and start time is exactly one hour