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