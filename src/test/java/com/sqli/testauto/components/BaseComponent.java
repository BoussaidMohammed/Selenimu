package com.sqli.testauto.components;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseComponent {
    protected static WebDriver driver;
    protected static ExtentTest logger;

    public static void setDriver(WebDriver driver) {
        BaseComponent.driver = driver;
    }

    public static void setLogger(ExtentTest logger) {
        BaseComponent.logger = logger;
    }

    public BaseComponent() {
        PageFactory.initElements(driver, this);
    }
}
