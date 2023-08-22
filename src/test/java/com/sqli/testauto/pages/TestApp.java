package com.sqli.testauto.pages;

import com.sqli.testauto.listners.WaitLoadingPageAfterClickonLinks;
import com.sqli.testauto.utils.PagesCommonActions;
import com.sqli.testauto.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestApp {
    private WebDriver driver;
    private CapsuleOriginaleCatalogue capsuleOriginaleCatalogue;
    private MachinesOriginaleCatalogue machinesOriginaleCatalogue;
    private ProductDetailPage pdp;
    private LandingPage landingPage;
    private NavBar navBar;
    private MiniCart miniCartPage;
    private PagesCommonActions pagesCommonActions;
    private Header header;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://ma.buynespresso.com/ma_fr/");
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(14));
        driver.manage().window().maximize();
        setupPages(driver);
        //setupListners(driver);
        pagesCommonActions.waitLoadingPage();
        pagesCommonActions.clickCookiesButton();
    }

    /*private void setupListners(WebDriver driver) {
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        WaitLoadingPageAfterClickonLinks waitLoadingPageAfterClickonLinks = new WaitLoadingPageAfterClickonLinks(pagesCommonActions);
        eventDriver.register(waitLoadingPageAfterClickonLinks);
    }*/

    private void setupPages(WebDriver driver) {
        pagesCommonActions = new PagesCommonActions(driver);
        navBar = new NavBar(driver);
        miniCartPage = new MiniCart(driver);
        header = new Header(driver);
        Utilities.setDriver(driver);
    }

    @Test(priority = 0)
    public void navigateToCapsuleOriginalesCatalogue(){
        landingPage = new LandingPage(driver);
        navBar.cafesMenu();
        navBar.capsulesOriginalCataloguePage();
    }

   @Parameters({"capsule", "capsuleQty"})
   @Test(priority = 1)
    public void addcapsuleOriginalProductToCart(String product, int qty){
       System.out.println(product + "::" + qty);
        pagesCommonActions.waitLoadingPage();
        capsuleOriginaleCatalogue = new CapsuleOriginaleCatalogue(driver);
        capsuleOriginaleCatalogue.clickAddToCartButton(product, qty);
        miniCartPage.waitUntilIconCounterShows();
    }



    @Test(priority = 2)
    public void navigateToMachineOriginalCatalogue(){
        navBar.machinesMenu();
        navBar.machinesOriginalCataloguePage();
    }
    @Parameters({"machine", "machineQty"})
    @Test(priority = 3)
    public void addMachineOriginalProductToCart(String product, int qty){
        pagesCommonActions.waitLoadingPage();
        machinesOriginaleCatalogue = new MachinesOriginaleCatalogue(driver);
        machinesOriginaleCatalogue.pdpOfProduct(product);
        pagesCommonActions.waitLoadingPage();
        pdp = new ProductDetailPage(driver);
        pdp.addProductToCart(qty);
        miniCartPage.waitUntilIconCounterChangeItsValue();
    }

    @Test(priority = 4)
    @Parameters({"capsule", "capsuleQty","machine", "machineQty"})
    public void checkProductInMinicart(String capsule, int capsuleQty,
                                       String machine, int machineQty){
        pagesCommonActions.waitLoadingPage();
        header.goToMiniCart();
        Assert.assertTrue(miniCartPage.isProductExistInCart(capsule, capsuleQty));
        Assert.assertTrue(miniCartPage.isProductExistInCart(machine, machineQty));
        pagesCommonActions.waitLoadingPage();
        miniCartPage.clickCheckoutButton();
        pagesCommonActions.waitLoadingPage();
    }

    @AfterTest
    public void tearDown(){
        Utilities.pause(15000);
        driver.quit();
    }

}


