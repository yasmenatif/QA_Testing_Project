package org.example.stepDefs;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.*;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Hooks {

    public static WebDriver driver;
    public static ExtentReports report;
    public static ExtentTest reportTest;



    @Before
    public static void setUp(Scenario scenario){
        ExtentManager.setUp();
        ExtentManager.createTest(scenario.getName(), scenario.getId());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
        driver.get("https://www.google.com/");


    }


    @After
    public static void shutDown(Scenario scenario) throws InterruptedException {
        if (scenario.isFailed()) {

            reportTest.log(Status.FAIL,"Test Failed: " + scenario.getName());

        } else {
            reportTest.log(Status.PASS,"Test passed: " + scenario.getName());

        }
        Thread.sleep(5000);

        driver.quit();
    }


}
