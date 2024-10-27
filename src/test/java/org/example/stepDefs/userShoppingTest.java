package org.example.stepDefs;

import com.aventstack.extentreports.ExtentReports;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.cartPage;
import org.example.pages.homePage;
import org.example.pages.loginPage;
import org.example.pages.productDetailsPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.example.stepDefs.Hooks.*;

public class userShoppingTest {
    WebDriver driver = Hooks.driver;

    cartPage cart = new cartPage();
    homePage home = new homePage();
    loginPage logIn = new loginPage();
    productDetailsPage prodDetails = new productDetailsPage();

    SoftAssert softAssert = new SoftAssert();



    @Given("I open the eShop website")
    public void openUrl(){

        driver.get("https://eshop.vodafone.com.eg/shop/home");
        home.initialPage.click();
        home.closeCookiesSettings.click();




    }

    @And("I login to my account")
    public void iLoginToMyAccount() throws InterruptedException {
        logIn.goToLoginPage.click();

        Thread.sleep(2000);

        logIn.mobileNoInput.clear();
        logIn.mobileNoInput.sendKeys("01099791462");
        logIn.passInput.clear();
        logIn.passInput.sendKeys("Asad@1959");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logIn.goToYourAccount));


        logIn.goToYourAccount.click();



    }

//    @When("I scroll down to see the items")
//    public void iScrollDownToSeeTheItems() throws InterruptedException {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0, 1444);");
////        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
//
//
//    }

    @When("I select and add items to the cart")
    public void iSelectAndAddItemsToTheCart() throws InterruptedException {



        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(home.selectFirstItem));
        scrollToElement(home.selectFirstItem);
        home.selectFirstItem.click();
        prodDetails.addToCart.click();


        Thread.sleep(2000);

        driver.navigate().back();
//        iScrollDownToSeeTheItems();

        wait.until(ExpectedConditions.elementToBeClickable(home.selectSecondItem));

        scrollToElement(home.selectSecondItem);
        home.selectSecondItem.click();
        prodDetails.addToCart.click();

        Thread.sleep(2000);

        home.searchBarTVChoice.click();
        wait.until(ExpectedConditions.elementToBeClickable(home.selectThirdItem));

        home.selectThirdItem.click();

        Thread.sleep(2000);

        prodDetails.addToCart.click();



    }

    @Then("I should see the items in my cart")
    public void iShouldSeeTheItemsInMyCart() {
        cart.goToCartPage.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement firstCartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//div[@class=\"cart-card-container\"][1]")));
        WebElement secondCartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//div[@class=\"cart-card-container\"][2]")));
        WebElement thirdCartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//div[@class=\"cart-card-container\"][3]")));

        softAssert.assertNotNull(firstCartItem, "First item is not present in the cart.");
        softAssert.assertNotNull(secondCartItem, "Second item is not present in the cart.");
        softAssert.assertNotNull(thirdCartItem, "Third item is not present in the cart.");

        softAssert.assertAll();



    }

    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500); // Add a short delay to ensure the element is in view
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
