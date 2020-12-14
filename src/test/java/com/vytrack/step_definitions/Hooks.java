package com.vytrack.step_definitions;

import com.vytrack.pages.CalendarEvent;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before
    public void setUp(Scenario scenario){
        System.out.println(scenario.getSourceTagNames());
        System.out.println("Starting Automation");
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS).pageLoadTimeout(40, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        BrowserUtils.wait(2);
        Driver.closeDriver();
        System.out.println("End of the test execution!");
    }

}
