package com.vytrack.pages;

import com.vytrack.utilities.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

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

    public static void waitForLoad(){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
        By mask =By.xpath("//body/div[4]");
        wait.until(ExpectedConditions.presenceOfElementLocated(mask));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(mask));
    }

    public void navigationGlobal(String globalMenu,String globalSubMenu) {
        waitForLoad();
        WebElement globalClick = fluentWait.until(driver -> driver.findElement(By.xpath(String.format(globalMenuXpath, globalMenu))));
        WebElement subGlobalClick = fluentWait.until(driver -> driver.findElement(By.xpath(String.format(globalSubMenuXpath, globalSubMenu))));
        BrowserUtils.clickWithJS(globalClick);
        BrowserUtils.clickWithJS(subGlobalClick);
    }


}
