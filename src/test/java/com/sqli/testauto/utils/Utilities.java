package com.sqli.testauto.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utilities {
    private static WebDriver driver;
    @FindBy(xpath = "//*[@class = 'loading']")
    private static WebElement loadingPage;

    public static void waitUntilInvisibilityOf(WebElement webElement) {
        new WebDriverWait(driver, Duration.ofSeconds(40, 40)).until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static void waitUntilVisibilityOf(WebElement webElement) {
        new WebDriverWait(driver, Duration.ofSeconds(40, 40)).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void pause(long miliSeconds) {
        try {
            Thread.sleep(miliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void hoverOnElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void waitUntilTextOfElementChange(WebElement element, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(40, 40))
                .until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath(getElementXPath(element)), text)));
    }

    public static void setDriver(WebDriver newDriver) {
        driver = newDriver;
    }

    public static String getElementXPath(WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript(
                "function getElementXPath(elt) {" +
                        "    var path = '';" +
                        "    for (; elt && elt.nodeType == 1; elt = elt.parentNode) {" +
                        "        idx = getElementIdx(elt);" +
                        "        xname = elt.tagName.toLowerCase();" +
                        "        if (idx > 1) xname += '[' + idx + ']';" +
                        "        path = '/' + xname + path;" +
                        "    }" +
                        "    return path;" +
                        "}" +
                        "function getElementIdx(elt) {" +
                        "    var count = 1;" +
                        "    for (var sib = elt.previousSibling; sib ; sib = sib.previousSibling) {" +
                        "        if(sib.nodeType == 1 && sib.tagName == elt.tagName) count++;" +
                        "    }" +
                        "    return count;" +
                        "}" +
                        "return getElementXPath(arguments[0]);", element);
    }

}
