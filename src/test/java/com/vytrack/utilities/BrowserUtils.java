package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {

    private static WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);
    /**
     * Wait that takes 2 parameters:
     * - driver The WebDriver instance to pass to the expected conditions(in our case: driver from singleton class)
     * - timeOutInSeconds The timeout in seconds when an expectation is called ( duration in seconds to sleep between polls)
     * we declare this wait for multiple reasons:
     * - it is static, because we don't want to create an object to call it;
     * - we create a wait object, so we can use explicit wait(possible only with instances)
     * - we will use it in methods below
     */
    public static void wait (int seconds){
        try{
            Thread.sleep(seconds*1000);
        }catch(
                InterruptedException e
        ){
            e.printStackTrace();
        }
    }
    /**
     * Wait method - used to provide a default waiting time between each consecutive test step/command across the entire test script.
     * Thus, the subsequent test step would only execute when the specified amount of time has elapsed after executing the previous test step/command.
     * e.printStackTrace(); - used to handle exceptions and errors.
     * It is a method of Java’s throwable class which prints the throwable along with other details like the line number and class name where the exception occurred.
     */

    public static void scrollTo(WebElement element){
        ((JavascriptExecutor)Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",element);
    }
    /**
     * Method that will imitate page scrolling.
     * JavascriptExecutor - is an interface, used to execute JavaScript
     * executeScript(): Executes JavaScript in the context of the currently selected frame or window. The script fragment provided will be executed as the body of an anonymous function.
     *                  If the script has a return value - you cannot use primitives!!Arguments must be a number, a boolean, a String, WebElement, or a List of any combination of the above.
     *                  An exception will be thrown if the arguments do not meet these criteria.
     *                  The arguments will be made available to the JavaScript via the "arguments" magic variable, as if the function were called via "Function.apply"
     *                  It takes 2 parameters: script - the JavaScript to execute and args - the arguments to the script, it may be empty;
     * script parameter: "arguments[0]" means first index of page starting at 0. It is the element which should be scrolled into the view.
     *                   If true, the top of the element will be aligned to the top of the visible area of the scrollable ancestor.
     *                   If false, the bottom of the element will be aligned to the bottom of the visible area of the scrollable ancestor.
     * Please note: this method will use viewport element from <meta> tag in your page. Based on this element and its value, you will have the element
     * (which you will put as a parameter in this method) centered on the top of the page(this is very useful if you need to record a video for example)
     * If the scrollIntoView(false) - then the element will be centered on the bottom on the page
     *
     */

    public static void scrollBy(){
        ((JavascriptExecutor)Driver.getDriver()).executeScript("scroll(0,100)");
    }

    /**
     * The scroll() method of the Element interface scrolls the element to a particular set of coordinates inside a given element.
     * To get coordinates of the WebElement: 1 step - create a Point: Point location = element.getLocation():
     *                                       2 step - use .getX(); && getY();
     */

    public static void clickWithJS(WebElement element){
        ((JavascriptExecutor) (Driver.getDriver())).executeScript("arguments[0].click()",element);}
    /**
     *  to work you need to have the webElement defined.
     *  executeScript() method will take the reference of the element as arguments[0]
     * along with the method to be performed [in this case click()] and the reference should be provided thereafter.
     * Use this method when there is any issues to perform regular .click
     */
    public static void clickOnElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    /**
     * This condition has a web element locator as a parameter.
     * An explicit wait is applied to the condition that tries to find the web element.
     * If the condition finds the element, it returns the element as the result.
     * If not, the wait command tries the condition again after a short delay.
     * static ExpectedCondition < WebElement > elementToBeClickable(By locator)
     * This condition is used to instruct a command to wait until the element is clickable by the locator.
     */
    public static void enterText(WebElement element, String text){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public static void enterTextAndClickEnter(WebElement element, String text){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text + Keys.ENTER);
    }

    /**
     * static ExpectedCondition < WebElement > visibilityOfElementLocated(By locator)
     * This condition is used to instruct a command to wait until the element becomes visible.
     * than it will clean the input-box and .sendKeys()
     */

    public static List<String> getTextFromWebElements(List<WebElement> elements){
        List<String> textValues = new ArrayList<>();
        for (WebElement element: elements){
            if (!element.getText().isEmpty()) {
                textValues.add(element.getText());
            }
        }
        return textValues;
    }




}
