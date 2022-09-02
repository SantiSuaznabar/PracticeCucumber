package stepDefinitions;

import io.cucumber.java.After;
import utilities.DriverManager;

public class Hooks {

    @After
    public void afterScenario(){
        DriverManager.getDriver().driver.close();
    }
}
