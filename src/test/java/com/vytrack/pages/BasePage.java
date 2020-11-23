package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {

    @FindBy(css = "loader-mask")
    protected List<WebElement> mask;

    protected String globalMenuXpath ="//span[contains(text(),'%s')][@class='title title-level-1']";
    protected String globalSubMenuXpath  = "//span[contains(text(),'%s')][@class='title title-level-2']";

    public void navigationGlobal(String globalMenu, String globalSubMenu){
        //wait.until(ExpectedConditions.invisibilityOfAllElements(mask));
        BrowserUtils.wait(2);
        BrowserUtils.clickOnElement( driver.findElement(By.xpath(String.format(globalMenuXpath,globalMenu))));
        //wait.until(ExpectedConditions.invisibilityOfAllElements(mask));
        BrowserUtils.clickOnElement( driver.findElement(By.xpath(String.format(globalSubMenuXpath,globalSubMenu))));


    }




    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, 25);

    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }



}
