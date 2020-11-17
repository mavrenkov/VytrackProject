package com.vytrack.step_definitions;

import com.vytrack.pages.LogInPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LogIn_StepDefinition {
    LogInPage logInPage = new LogInPage();
    @Given("user is on the login page")
    public void user_is_on_the_login_page() {

        logInPage.loginURL();
    }

    @When("user logs in as a {string}")
    public void user_logs_in_as_a(String string) {

        logInPage.login(string);

    }

    @Then("user should see dashboard page")
    public void user_should_see_dashboard_page() {

        Assert.assertTrue(logInPage.loginConfirmation());
    }


}
