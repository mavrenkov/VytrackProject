@login
Feature: user should be able to login under store manager
  @login_store_manager
  Scenario: as a user i should be able to login

    Given user is on the login page
    When user logs in as a "store manager"
    Then user should see dashboard page






