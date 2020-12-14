package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.ro.Si;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarEvent extends BasePage {

    @FindBy(xpath = "//button[contains(text(),'contains')]/../..//input[@type='text']")
    private WebElement inputFilterText;
    @FindBy(xpath = "//button[contains(text(),'contains')]/../..//button[@type='button']")
    private WebElement updateFilterButton;
    @FindBy(xpath = "//a[.='...']")
    private WebElement threeDotHover;
    @FindBy(xpath = "//td[@class ='title-cell']/label")
    private List<WebElement> columnsTitlesGridSettings;
    @FindBy(xpath = "//tbody[@class='ui-sortable']//input")
    private List<WebElement> checkBoxGridSettings;
    @FindBy (xpath = "//a[@title='Create Calendar event']")
    private WebElement createCalendarEventButton;
    @FindBy (xpath = "//div[@class='btn-group pull-right']/a")
    private WebElement saveAndCloseDropdown;
    @FindBy (xpath = "//a[@title='Cancel']")
    private WebElement cancelButton;
    @FindBy (xpath = "//h1[@class='oro-subtitle']")
    private WebElement allCalendarEventsPageSubtitle;
    @FindBy (xpath = "(//input[@type='datetime'])[1]")
    private WebElement startRow;
    @FindBy (xpath = "(//input[@type='datetime'])[2]")
    private WebElement endRow;
    @FindBy (xpath = "//li/button[contains(text(), 'Save')]")
    private List<WebElement> saveAndCloseDropdownOptions;
    @FindBy (xpath = " (//input[@placeholder='time'])[1]")
    private WebElement startTimeDropdown;
    @FindBy (xpath = " (//input[@placeholder='time'])[2]")
    private WebElement endTimeDropdown;
    @FindBy (xpath = "(//li[@class='ui-timepicker-pm ui-timepicker-selected'])[2]")
    private WebElement selectedEndTime;
    @FindBy (xpath = "//input[@name='oro_calendar_event_form[allDay]']")
    private WebElement allDayEventCheckbox;
    @FindBy (xpath = "(//input[@placeholder='Choose a date'])[1]")
    private WebElement startDateInput;
    @FindBy (xpath = "(//input[@placeholder='Choose a date'])[2]")
    private WebElement endDateInput;
    @FindBy(xpath = "//input[@data-name='recurrence-repeat']")
    private WebElement repeatCheckbox;
    @FindBy(xpath = "//select[@data-name='recurrence-repeats']")
    private WebElement repeatsDropdown;
    @FindBy(xpath = "//label[@class='fields-row']/input[@checked='checked']")
    private WebElement repeatEveryRadioButton;
    @FindBy(xpath = "//span[text() = 'Never']/preceding-sibling::input[@type='radio']")
    private WebElement endsNeverRadioButton;
    @FindBy(xpath = "//span[text() = 'After']/preceding-sibling::input[@type='radio']")
    private WebElement endsAfterRadioButton;
    @FindBy(xpath = "//span[text() = 'By']/preceding-sibling::input[@type='radio']")
    private WebElement endsByRadioButton;
    @FindBy(xpath = "//span[text() = 'After']/following-sibling::input[@type='text']")
    private WebElement occurrencesInputField;
    @FindBy(xpath = "//input[@class='datepicker-input hasDatepicker']")
    private WebElement chooseDateInputField;
    @FindBy(xpath = "//select[@class='ui-datepicker-year']")
    private WebElement chooseYearDropdown;
    @FindBy(xpath = "//select[@class='ui-datepicker-month']")
    private WebElement chooseMonthDropdown;
    @FindBy(xpath = "//div[@class='control-group recurrence-summary alert-info']/div/label")
    private WebElement summary;
    @FindBy(xpath = "//div[@data-name='recurrence-summary']/div/span")
    private List<WebElement> summaryMessage;



    protected String gridButtonsXpath = "(//a[@title='%s'])[1]";
    protected String filtersOptionsXpath = "//div[contains(text(),'%s')]";
    protected String threeDotOptionsXpath = "//ul[@class='nav nav-pills icons-holder launchers-list']//a[@title='%s']";
    protected String calEventsGridFields = "//span[.='%s']";
    protected String startTimeOption = "(//li[. = '%s'])[1]";
    protected String chooseDateInTable = "//tbody/tr/td[.='%s']";
    protected String repeatOnWeekDayCheckbox = "//input[@value='%s']";


    //TC1
    public void gridButtonsClick(String buttonName){
        BrowserUtils.clickOnElement(driver.findElement(By.xpath(String.format(gridButtonsXpath,buttonName))));

    }
    public void filtersOptionsClick(String filterOption){
        BrowserUtils.clickOnElement(driver.findElement(By.xpath(String.format(filtersOptionsXpath,filterOption))));
    }
    public void filterByTitleName(String filterValue){
        BrowserUtils.enterText(inputFilterText,filterValue);
        BrowserUtils.clickOnElement(updateFilterButton);
    }
    public boolean threeDotButtonsConfirmation(String view, String edit, String delete){
        waitForLoad();
        wait.until(ExpectedConditions.visibilityOf(threeDotHover));
        Actions action = new Actions(driver);
        action.moveToElement(threeDotHover).perform();
        boolean viewIsVisible = driver.findElement(By.xpath(String.format(threeDotOptionsXpath,view))).isDisplayed();
        boolean editIsVisible = driver.findElement(By.xpath(String.format(threeDotOptionsXpath,edit))).isDisplayed();
        boolean deleteIsVisible = driver.findElement(By.xpath(String.format(threeDotOptionsXpath,delete))).isDisplayed();

        return viewIsVisible&&editIsVisible&&deleteIsVisible;
    }

    //TC2
    public void gridSettingsClick(String gridSettingsTitle){
        BrowserUtils.wait(3);
        //wait.until(ExpectedConditions.invisibilityOfAllElements(mask));
        BrowserUtils.clickOnElement(driver.findElement(By.xpath((String.format(gridButtonsXpath, gridSettingsTitle)))));
    }
    public void uncheckAllColumnsExceptTitle(String titleOption){
        BrowserUtils.wait(3);
       // wait.until(ExpectedConditions.invisibilityOfAllElements(mask));
        for(int i = 0; i <=checkBoxGridSettings.size()-1; i++){
            if(columnsTitlesGridSettings.get(i).getText().equalsIgnoreCase(titleOption)){
                continue;
            }else{

                checkBoxGridSettings.get(i).click();
            }
        }
    }
    public boolean titleAssertion(String title){
        return driver.findElement(By.xpath(String.format(calEventsGridFields,title))).isDisplayed();
    }

    //TC3
    public List<WebElement> getSaveAndCloseDropdownOptions() {
        return saveAndCloseDropdownOptions;
    }
    public void createCalendarEventButtonClick(){
        waitForLoad();
        wait.until(ExpectedConditions.visibilityOf(createCalendarEventButton));
        BrowserUtils.clickOnElement(createCalendarEventButton);
    }
    public void saveAndCloseDropdownClick(){
        BrowserUtils.wait(2);
        BrowserUtils.clickOnElement(saveAndCloseDropdown);
    }

    //TC4
    public void cancelButtonClick(){
        waitForLoad();
        BrowserUtils.clickOnElement(cancelButton);
    }
    public boolean allCalendarEventsPageSubtitleAssertion(){
        return allCalendarEventsPageSubtitle.isDisplayed();
    }

    //TC5

    public boolean differenceBetweenStartEndTime(){

        String startDateAndTime = startRow.getAttribute("value").replaceAll("T", " ").replaceAll("Z", "");
        //System.out.println(startDateAndTime);
        String endDateAndTime = endRow.getAttribute("value").replaceAll("T", " ").replaceAll("Z", "");
        //System.out.println(endDateAndTime);

        int timeZone = -8;
        int startHour = getHourFromTheString(startDateAndTime) + timeZone;
        int endHour = getHourFromTheString(endDateAndTime) + timeZone;
        //System.out.println("Current start hour = " + startHour + " and end hour = " + endHour);
        int differenceBetweenEndStartHour = endHour - startHour;

        return differenceBetweenEndStartHour == 1;
    }

    public int getHourFromTheString(String string){

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date current = null;
        try {
            current = date.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int hour = current.getHours();
        return hour;
    }

    //TC6

    public void chooseStartTime(String time){
        BrowserUtils.clickOnElement(startTimeDropdown);
        BrowserUtils.clickOnElement(driver.findElement(By.xpath(String.format(startTimeOption, time))));
    }

    public boolean endTimeAssertion(String string){

        BrowserUtils.clickOnElement(endTimeDropdown);
        String chosenEndTime = selectedEndTime.getText();
        return chosenEndTime.equalsIgnoreCase(string);
    }

    //TC7
    public void allDayEventCheckboxClick(){
       waitForLoad();
       BrowserUtils.clickOnElement(allDayEventCheckbox);
    }
    public boolean allDayEventCheckboxAssertion(){
        return allDayEventCheckbox.isSelected();
    }
    public boolean timeInputBoxesAssertion(){
        wait.until(ExpectedConditions.invisibilityOfAllElements(startTimeDropdown, endTimeDropdown));
        return startTimeDropdown.isDisplayed() || endTimeDropdown.isDisplayed();
    }
    public boolean dateInputBoxesAssertion(){
        return startDateInput.isDisplayed() && endDateInput.isDisplayed();
    }

    //TC8
    public void repeatCheckboxClick(){
        BrowserUtils.clickOnElement(repeatCheckbox);
    }
    public WebElement getRepeatCheckbox(){
        return repeatCheckbox;
    }
    public boolean repeatsDefaultOptionAssertion(String string){
        Select repeats = new Select(repeatsDropdown);
        String defaultOption = repeats.getFirstSelectedOption().getText();
        return defaultOption.equalsIgnoreCase(string);
    }

    public boolean repeatsDropdownAssertion(List<String> options){

        Select repeats = new Select(repeatsDropdown);

        List<WebElement> actualOptions = repeats.getOptions();
        List<String> actualStringOptions = new ArrayList<String>();
        for (WebElement each : actualOptions){
            if(!each.getText().equalsIgnoreCase("Daily")){
                String currentOptionText = each.getText();
                actualStringOptions.add(currentOptionText);
            }
        }

        return actualStringOptions.equals(options);
    }

    //TC9
    public WebElement getRepeatEveryRadioButton() {
        return repeatEveryRadioButton;
    }

    public WebElement getEndsNeverRadioButton() {
        return endsNeverRadioButton;
    }
    public boolean summaryMessageAssertion(String string){

        String actualSummaryMessage = summary.getText() + " ";

        for(WebElement each : summaryMessage){
            actualSummaryMessage += each.getText();
        }
        return actualSummaryMessage.equals(string) && summaryMessageIsDisplayed();
    }
    public boolean summaryMessageIsDisplayed(){
        boolean check = summary.isDisplayed();
        for(WebElement each : summaryMessage){
            check = check && each.isDisplayed();
        }
        return check;
    }

    //TC10
    public void setOccurrences(int occurrences){
        BrowserUtils.clickOnElement(endsAfterRadioButton);
        String occurrencesString = "" + occurrences;
        BrowserUtils.enterTextAndClickEnter(occurrencesInputField, occurrencesString);
    }

    //TC11
    public void setEndsByDate(String month, int day, int year){
        BrowserUtils.clickOnElement(endsByRadioButton);
        BrowserUtils.clickOnElement(chooseDateInputField);

        Select selectYear = new Select(chooseYearDropdown);
        String yearString = "" + year;
        selectYear.selectByVisibleText(yearString);

        Select selectMonth = new Select(chooseMonthDropdown);
        selectMonth.selectByVisibleText(month);

        String dayString = "" + day;
        BrowserUtils.clickOnElement(driver.findElement(By.xpath(String.format(chooseDateInTable, dayString))));

    }

    //TC12
    public void selectRepeatsWeekly(){
        Select selectRepeats = new Select(repeatsDropdown);
        selectRepeats.selectByVisibleText("Weekly");
    }
    public void selectRepeatsOnWeekDays(String str1, String str2){
        BrowserUtils.clickOnElement(driver.findElement(By.xpath(String.format(repeatOnWeekDayCheckbox, str1.toLowerCase()))));
        BrowserUtils.clickOnElement(driver.findElement(By.xpath(String.format(repeatOnWeekDayCheckbox, str2.toLowerCase()))));
    }
    public boolean repeatsOnWeekdaysAssertion(String str1, String str2){
        WebElement checkbox1 = driver.findElement(By.xpath(String.format(repeatOnWeekDayCheckbox, str1.toLowerCase())));
        WebElement checkbox2 = driver.findElement(By.xpath(String.format(repeatOnWeekDayCheckbox, str2.toLowerCase())));
        return checkbox1.isDisplayed() && checkbox2.isDisplayed();
    }


}
