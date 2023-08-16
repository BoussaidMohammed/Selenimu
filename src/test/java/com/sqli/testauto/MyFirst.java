package com.sqli.testauto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyFirst{
    WebDriver driver;
    @BeforeTest
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://selenium.dev");
        driver.manage().window().maximize();
    }
    @Test
    public void test(){

        System.out.println(driver.getTitle());
       // WebElement e = driver.findElement(By.xpath("//*/a[@class=\"dropdown-item\"]"));
        WebElement e = driver.findElement(By.xpath("//*[contains(text(),\"Other\")]"));
        System.out.println(e.getTagName());
        System.out.println(e.getAttribute("href"));
        e.click();
        System.out.println(e.getText());

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