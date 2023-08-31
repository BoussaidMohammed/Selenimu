package com.sqli.testauto.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sqli.testauto.components.BaseComponent;
import com.sqli.testauto.listners.PagesListener;
import com.sqli.testauto.pages.*;
import com.sqli.testauto.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

@SuppressWarnings({"deprecation"})
public class BaseTest {
    public static WebDriver driver;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/chromedriver/chromedriver");
        driver = setupDriverAndPages();
        driver.get("https://ma.buynespresso.com/ma_fr/");
        driver.manage().window().maximize();
    }

    private WebDriver setupDriverAndPages() {
        WebDriver webDriver = new ChromeDriver(/*options*/);
        setupPages(webDriver);
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(webDriver);
        PagesListener pagesListener = new PagesListener(new BasePage());
        eventFiringWebDriver.register(pagesListener);
        return eventFiringWebDriver;
    }

    private void setupPages(WebDriver driver) {
        BasePage.setDriver(driver);
        BaseComponent.setDriver(driver);
        Utilities.setDriver(driver);
    }

    @AfterTest
    public void tearDown(){
        Utilities.pause(10000);
        driver.quit();
    }

    protected ExtentSparkReporter sparkReporter;
    protected ExtentReports extent;
    public static ExtentTest logger;

    @BeforeTest
    public void beforeTestMethod(){
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports"
        + File.separator + "example.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        sparkReporter.config().setTheme(Theme.DARK);
        extent.setSystemInfo("HostName", "BSD");
        extent.setSystemInfo("UserName", "root");
        sparkReporter.config().setDocumentTitle("Automatiion report");
        sparkReporter.config().setReportName("nesspresson autamation tests");
    }

    @BeforeMethod
    public void beforeMethod(Method method){
        logger = extent.createTest(method.getName());
        BasePage.setLogger(logger);
        BaseComponent.setLogger(logger);
    }
    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Test case failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + "Test case failed", ExtentColor.RED));
        }
        if(result.getStatus() == ITestResult.SKIP){
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "Test case skipped", ExtentColor.ORANGE));
        }
        if(result.getStatus() == ITestResult.SUCCESS){
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Test case pass", ExtentColor.GREEN));
        }
    }

    @AfterTest
    public void  afterTest(){
        extent.flush();
    }

}


