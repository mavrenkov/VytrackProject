package com.vytrack.step_definitions;

import com.vytrack.pages.CalendarEvent;
import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

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

    //TC5
    @Then("difference between end and start time is exactly one hour")
    public void differenceBetweenEndAndStartTimeIsExactlyHour() {
        Assert.assertTrue(calendarEvent.differenceBetweenStartEndTime());
    }

    //TC6
    @And("user select {string} as a start time")
    public void userSelectAsAStartTime(String string) {
        calendarEvent.chooseStartTime(string);
    }
    @Then("end time is equals to {string}")
    public void endTimeIsEqualsTo(String string) {
        Assert.assertTrue(calendarEvent.endTimeAssertion(string));
    }

    //TC7
    @When("select All-Day Event checkbox")
    public void select_all_day_event_checkbox() {
        calendarEvent.allDayEventCheckboxClick();
    }

    @Then("All-Day Event checkbox is selected")
    public void all_day_event_checkbox_is_selected() {
        Assert.assertTrue(calendarEvent.allDayEventCheckboxAssertion());
    }
    @Then("start and end time input boxes are not displayed")
    public void start_and_end_time_input_boxes_are_not_displayed() {
        Assert.assertFalse(calendarEvent.timeInputBoxesAssertion());
    }
    @Then("start and end date input boxes are displayed")
    public void start_and_end_date_input_boxes_are_displayed() {
        Assert.assertTrue(calendarEvent.dateInputBoxesAssertion());
    }

    //TC8
    @When("select Repeat checkbox")
    public void select_repeat_checkbox() {
        calendarEvent.repeatCheckboxClick();
    }
    @Then("Repeat checkbox is selected")
    public void repeat_checkbox_is_selected() {
        Assert.assertTrue(calendarEvent.getRepeatCheckbox().isSelected());
    }
    @Then("{string} option is selected by default and in Repeats dropdown")
    public void optionIsSelectedByDefaultAndInRepeatsDropdown(String str) {
        calendarEvent.repeatsDefaultOptionAssertion(str);
    }
    @Then("other following options are available in Repeats dropdown")
    public void otherFollowingOptionsAreAvailableInRepeatsDropdown(List<String> options) {
        calendarEvent.repeatsDropdownAssertion(options);
    }

    //TC9
    @Then("Repeat Every radio button is selected")
    public void repeatEveryRadioButtonIsSelected() {
        calendarEvent.getRepeatEveryRadioButton().isSelected();
    }
    @Then("Never radio button is selected as an Ends option")
    public void neverRadioButtonIsSelectedAsAnEndsOption() {
        calendarEvent.getEndsNeverRadioButton().isSelected();
        
    }
    @Then("following summary message is displayed: {string}")
    public void followingSummaryMessageIsDisplayed(String string) {
        Assert.assertTrue(calendarEvent.summaryMessageAssertion(string));
    }

    //TC10
    @And("select After {int} occurrences as an Ends option")
    public void selectAfterOccurrencesAsAnEndsOption(int numberOfOccurrences) {
        calendarEvent.setOccurrences(numberOfOccurrences);
    }

    //TC11

    @And("select By {string} {int} {int} as an Ends option")
    public void selectByAsAnEndsOption(String month, int day, int year) {
        calendarEvent.setEndsByDate(month, day, year);
    }

    //TC12
    @And("select Weekly options as a Repeat option")
    public void selectOptionsAsARepeatOption() {
        calendarEvent.selectRepeatsWeekly();
    }

    @And("select {string} and {string} options as a Repeat On options")
    public void selectAndOptionsAsARepeatOnOptions(String str1, String str2) {
        calendarEvent.selectRepeatsOnWeekDays(str1, str2);
    }

    @Then("{string} and {string} options are selected")
    public void andOptionsAreSelected(String str1, String str2) {
        Assert.assertTrue(calendarEvent.repeatsOnWeekdaysAssertion(str1, str2));
    }
}
