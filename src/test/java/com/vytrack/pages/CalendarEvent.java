package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.ArrayList;
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


    @FindBy (xpath = "//li/button[contains(text(), 'Save')]")
    private List<WebElement> saveAndCloseDropdownOptions;

    protected String gridButtonsXpath = "(//a[@title='%s'])[1]";
    protected String filtersOptionsXpath = "//div[contains(text(),'%s')]";
    protected String threeDotOptionsXpath = "//ul[@class='nav nav-pills icons-holder launchers-list']//a[@title='%s']";
    protected String calEventsGridFields = "//span[.='%s']";
    //protected String saveAndCloseDropdownOptions = "//li/button[contains(text(), '%s')]";
    //protected String saveAndCloseDropdownOptions = "(//li/button[contains(text(), 'Save')])[%s]";

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




}
