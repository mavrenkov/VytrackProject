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
  Scenario: TC4 verify Cancel button in Create calendar Event window
    When user clicks on Create Calendar Event button
    And clicks on Cancel button
    Then All Calendar Event page subtitle is displayed

  @TC5
  Scenario: TC5 verify that difference between end and start time is exactly 1 hour in Create Calendar event
    When user clicks on Create Calendar Event button
    Then difference between end and start time is exactly one hour

  @TC6
  Scenario: TC6 verify that end time equals to 10:00pm when 9:00pm start time is selected
    When user clicks on Create Calendar Event button
    And user select "9:00 PM" as a start time
    Then end time is equals to "10:00 PM"

  @TC7
  Scenario: TC7 All-Day Event Checkbox verification
    When user clicks on Create Calendar Event button
    And  select All-Day Event checkbox
    Then All-Day Event checkbox is selected
    Then start and end time input boxes are not displayed
    Then start and end date input boxes are displayed


  @TC8
  Scenario: TC8 Repeat checkbox and Repeats dropdown verification
    When user clicks on Create Calendar Event button
    And select Repeat checkbox
    Then Repeat checkbox is selected
    Then "Daily" option is selected by default and in Repeats dropdown
    Then other following options are available in Repeats dropdown
        | Weekly |
        | Monthly |
        | Yearly |

  @TC9
  Scenario: TC9 Summary message in repeats verification
    When user clicks on Create Calendar Event button
    And select Repeat checkbox
    Then Repeat checkbox is selected
    Then Repeat Every radio button is selected
    Then Never radio button is selected as an Ends option
    Then following summary message is displayed: "Summary: Daily every 1 day"

  @TC10
  Scenario: TC10 Summary message verification when After 10 occurrences selected
    When user clicks on Create Calendar Event button
    And select Repeat checkbox
    And select After 10 occurrences as an Ends option
    Then following summary message is displayed: "Summary: Daily every 1 day, end after 10 occurrences"

  @TC11
  Scenario: TC11 Summary message verification when Ends By date is selected
    When user clicks on Create Calendar Event button
    And select Repeat checkbox
    And select By "Nov" 18 2021 as an Ends option
    Then following summary message is displayed: "Summary: Daily every 1 day, end by Nov 18, 2021"

  @TC12
  Scenario: TC12 Summary message verification when Repeats On specific weekdays selected
    When user clicks on Create Calendar Event button
    And select Repeat checkbox
    And select Weekly options as a Repeat option
    And select "Monday" and "Friday" options as a Repeat On options
    Then "Monday" and "Friday" options are selected
    Then following summary message is displayed: "Summary: Weekly every 1 week on Monday, Friday"