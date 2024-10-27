package org.example.stepDefs;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static void setUp() {
        ExtentSparkReporter spark = new ExtentSparkReporter("Reports/extentReport.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Test Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static void createTest(String testName, String description) {
        test = extent.createTest(testName, description);
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void tearDown() {
        if (extent != null) {
            extent.flush();
        }
    }
}
