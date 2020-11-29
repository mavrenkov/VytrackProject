package com.vytrack.step_definitions;

import com.vytrack.pages.CalendarEvent;
import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CalendarEvent_StepDefinitions {

    CalendarEvent calendarEvent = new CalendarEvent();

    //Background
    @Given("user navigates to {string} and {string}")
    public void user_navigates_to_and(String string, String string2) {
        calendarEvent.navigationGlobal(string, string2);
    }

    //TC1
    @When("user hovers on three dots for {string}")
    public void user_hovers_on_three_dots_for(String string) {

        calendarEvent.gridButtonsClick("Filters");
        calendarEvent.filtersOptionsClick("Title");
        calendarEvent.filterByTitleName(string);

    }
    @Then("{string}, {string} and {string} options are available")
    public void and_options_are_available(String string, String string2, String string3) {
            calendarEvent.threeDotButtonsConfirmation(string, string2, string3);
    }

    //TC2
    @When("user clicks on {string}")
    public void user_clicks_on(String string) {
        calendarEvent.gridSettingsClick(string);
    }
    @When("unchecks all columns except {string}")
    public void unchecks_all_columns_except(String string) {
        calendarEvent.uncheckAllColumnsExceptTitle(string);
    }
    @Then("{string} column is displayed")
    public void column_is_displayed(String string) {
        Assert.assertTrue(calendarEvent.titleAssertion(string));
    }

    //TC3
    @When("user clicks on Create Calendar Event button")
    public void user_clicks_on_calendar_event_button() {
        calendarEvent.createCalendarEventButtonClick();
    }
    @When("expand Save and close menu")
    public void expand_save_and_close_menu() {

        calendarEvent.saveAndCloseDropdownClick();
    }
    @Then("following option are available")
    public void followingOptionAreAvailable(List<String> options) {
        for(WebElement each : calendarEvent.getSaveAndCloseDropdownOptions()){
            if(options.contains(each.getText())){
                Assert.assertTrue(each.isDisplayed());
            }
        }
    }

    //TC4
    @When("clicks on Cancel button")
    public void clicks_on_cancel_button() {
        calendarEvent.cancelButtonClick();
    }

    @Then("All Calendar Event page subtitle is displayed")
    public void all_calendar_event_page_subtitle_is_displayed() {
        Assert.assertTrue(calendarEvent.allCalendarEventsPageSubtitleAssertion());
    }

    @Then("difference between end and start time is exactly one hour")
    public void differenceBetweenEndAndStartTimeIsExactlyHour() {
        Assert.assertTrue(calendarEvent.differenceBetweenStartEndTime());
    }
}
