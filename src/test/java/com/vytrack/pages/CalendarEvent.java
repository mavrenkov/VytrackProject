package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.List;

public class CalendarEvent extends BasePage {

    @FindBy(xpath = "//button[contains(text(),'contains')]/../..//input[@type='text']")
    private WebElement inputFilterText;
    @FindBy(xpath = "//button[contains(text(),'contains')]/../..//button[@type='button']")
    private WebElement updateFilterButton;
    @FindBy(xpath = "//a[.='...']")
    private WebElement threeDotHover;
    @FindBy(xpath = "//tr[@class='renderable']/td/label")
    private List<WebElement> columnsTitlesGridSettings;
    @FindBy(xpath = "//tbody[@class='ui-sortable']//input")
    private List<WebElement> checkBoxGridSettings;

    protected String gridButtonsXpath = "(//a[@title='%s'])[1]";
    protected String filtersOptionsXpath = "//div[contains(text(),'%s')]";
    protected String threeDotOptionsXpath = "//ul[@class='nav nav-pills icons-holder launchers-list']//a[@title='%s']";
    protected String rowFilterSettings = "//label[.='%s']/../following-sibling::td[@class='visibility-cell']/input";


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

    public void gridSettingsClick(String gridSettingsTitle){
        BrowserUtils.wait(3);
        BrowserUtils.clickOnElement(driver.findElement(By.xpath((String.format(gridButtonsXpath, gridSettingsTitle)))));
    }

    public void uncheckAllColumnsExceptTitle(String titleOption){

        for(int i = 0; i <=checkBoxGridSettings.size()-1; i++){
            if(!columnsTitlesGridSettings.get(i).getText().equalsIgnoreCase(titleOption)){

                Select select = new Select(checkBoxGridSettings.get(i));
                select.selectByIndex(i);

            }


        }

    }



}
