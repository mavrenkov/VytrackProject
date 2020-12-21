package com.vytrack.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private Driver(){}

    public synchronized static WebDriver getDriver(){
        if (driverPool.get() == null) {
            //it will make sure that 2 threats cannot access this piece of code

                String browser = ConfigurationReader.getProperty("browser");
                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driverPool.set(new ChromeDriver());
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driverPool.set(new FirefoxDriver());
                        break;
                    case "remote_chrome":
                        try{

                        URL url = new URL("http://54.157.186.89:4444/wd/hub");

                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName(BrowserType.CHROME);
                        desiredCapabilities.setPlatform(Platform.ANY);
                        driverPool.set(new RemoteWebDriver(url,desiredCapabilities));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "remote_firefox":
                        try{
                            URL url = new URL("http://ec2-3-82-38-154.compute-1.amazonaws.com:4444/wd/hub");

                            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                            desiredCapabilities.setBrowserName(BrowserType.FIREFOX);
                            desiredCapabilities.setPlatform(Platform.ANY);
                            driverPool.set(new RemoteWebDriver(url,desiredCapabilities));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        throw new RuntimeException("Wrong Browser name!");
                }
            }

        return driverPool.get();
    }

    public static void  closeDriver(){
        if(driverPool.get() != null){
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
