package com.vytrack.pages;

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.security.Key;

public class LogInPage extends BasePage{

    @FindBy(id = "prependedInput")
    private WebElement username;

    @FindBy(id = "prependedInput2")
    private WebElement password;

    public void login(String userType){

        String userNameValue = "";
        if (userType.equalsIgnoreCase("store manager")) {
            userNameValue = ConfigurationReader.getProperty("storemanager.username");
        }
        else if(userType.equalsIgnoreCase("driver")){
            userNameValue = ConfigurationReader.getProperty("driver.username");
        }

        username. sendKeys(userNameValue);
        password.sendKeys(ConfigurationReader.getProperty("password")+ Keys.RETURN);

    }

    public boolean loginConfirmation(){

        String expectedTitle = "Dashboard";
        wait.until(ExpectedConditions.titleIs(expectedTitle));

        return driver.getTitle().equals(expectedTitle);
    }

    public void loginURL(){
        driver.get(ConfigurationReader.getProperty("url"));
    }

}
