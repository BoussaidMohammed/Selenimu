package com.sqli.testauto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Second {
    WebDriver driver;
    @BeforeTest
    public void init() {
       // System.setProperty("webdriver.gecko.driver", "src/chromedriver/geckodriver");
      /*  ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");*/
        System.setProperty("webdriver.chrome.driver", "src/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://ma.buynespresso.com/ma_fr/cafe/original.html");
        //driver.manage().window().maximize();
        new WebDriverWait(driver, Duration.ofSeconds(40, 40)).until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@class = 'loading']"))));
    }
    @Test
    public void test(){

        System.out.println(driver.getTitle());
        //WebElement e = driver.findElement(By.xpath("//*/a[@class=\"dropdown-item\"]"));
        WebElement e = driver.findElement(By.xpath("//*/a[contains(@href,'filter-style-mild')]/../..//div/form/button"));
        e.click();
        System.out.println(e.getTagName());

    }

    @AfterTest
    public void closeSession(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}