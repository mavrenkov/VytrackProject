package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.ro.Si;
import org.openqa.selenium.By;
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



    protected String gridButtonsXpath = "(//a[@title='%s'])[1]";
    protected String filtersOptionsXpath = "//div[contains(text(),'%s')]";
    protected String threeDotOptionsXpath = "//ul[@class='nav nav-pills icons-holder launchers-list']//a[@title='%s']";
    protected String calEventsGridFields = "//span[.='%s']";
    protected String startTimeOption = "(//li[. = '%s'])[1]";


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
        wait.until(ExpectedConditions.visibilityOf(createCalendarEventButton));
        BrowserUtils.clickOnElement(createCalendarEventButton);
    }
    public void saveAndCloseDropdownClick(){
        BrowserUtils.wait(2);
        BrowserUtils.clickOnElement(saveAndCloseDropdown);
    }

    //TC4
    public void cancelButtonClick(){
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

}
