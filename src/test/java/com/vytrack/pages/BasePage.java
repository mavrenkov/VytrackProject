package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public abstract class BasePage {

    @FindBy(css = "loader-mask")
    protected List<WebElement> mask;

    protected String globalMenuXpath ="//span[contains(text(),'%s')][@class='title title-level-1']";
    protected String globalSubMenuXpath  = "//span[contains(text(),'%s')][@class='title title-level-2']";
    
    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, 25);



    //Fluent wait declaration

    Wait<WebDriver> fluentWait = new FluentWait<>(driver)
            .withTimeout(50, TimeUnit.SECONDS)
            .pollingEvery(3, TimeUnit.SECONDS)
            .ignoring(NoSuchElementException.class)
            .ignoring(ElementClickInterceptedException.class)
            .ignoring(ElementNotInteractableException.class)
            ;

    public void navigationGlobal(String globalMenu,String globalSubMenu) {
          WebElement globalClick = fluentWait.until(driver -> driver.findElement(By.xpath(String.format(globalMenuXpath, globalMenu))));
         WebElement subGlobalClick = fluentWait.until(driver -> driver.findElement(By.xpath(String.format(globalSubMenuXpath, globalSubMenu))));
        BrowserUtils.clickWithJS(globalClick);
        BrowserUtils.clickWithJS(subGlobalClick);
    }

/*
    public void navigationGlobal(String globalMenu, String globalSubMenu){
      // wait.until(ExpectedConditions.invisibilityOfAllElements(mask));
      //  BrowserUtils.wait(2);
        BrowserUtils.clickWithJS( driver.findElement(By.xpath(String.format(globalMenuXpath,globalMenu))));
      // wait.until(ExpectedConditions.invisibilityOfAllElements(mask));
        BrowserUtils.clickWithJS( driver.findElement(By.xpath(String.format(globalSubMenuXpath,globalSubMenu))));
      //  BrowserUtils.wait(1);

 */








}
