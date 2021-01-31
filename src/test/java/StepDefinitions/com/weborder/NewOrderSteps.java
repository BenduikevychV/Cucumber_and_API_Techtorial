package StepDefinitions.com.weborder;

import Pages.WebOrderPages.AllOrdersPage;
import Pages.WebOrderPages.HomePage;
import Pages.WebOrderPages.OrderPage;
import Utils.BrowserUtils;
import Utils.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class NewOrderSteps {


    WebDriver driver = Driver.getDriver();
    HomePage homePage = new HomePage(driver);
    OrderPage orderPage = new OrderPage(driver);
    AllOrdersPage allOrdersPage = new AllOrdersPage(driver);

    @Then("the user enter product info {string} and {string}")
    public void the_user_enter_product_info_and(String product, String quantity) throws InterruptedException {
        homePage.orderButton.click();
        Select select = new Select(orderPage.productName);
        select.selectByVisibleText(product);

        orderPage.quantity.clear();
        Thread.sleep(1000);
        orderPage.quantity.sendKeys(quantity);

    }

    @Then("the user enter address info {string}, {string}, {string}, {string}, {string}")
    public void the_user_enter_address_info(String customerName, String street, String city, String state, String zipCode) {
        orderPage.customerName.sendKeys(customerName);
        orderPage.street.sendKeys(street);
        orderPage.city.sendKeys(city);
        orderPage.state.sendKeys(state);
        orderPage.zipCode.sendKeys(zipCode);
    }

    @Then("the user enter payment info {string}, {string}, {string}")
    public void the_user_enter_payment_info(String card, String cardNum, String expiration) {

        switch (card){
            case "Visa":
                orderPage.visa.click();
                break;
            case "MasterCard":
                orderPage.masterCard.click();
                break;
            case "American Express":
                orderPage.amex.click();
                break;
        }

        orderPage.cardNum.sendKeys(cardNum);
        orderPage.experationDate.sendKeys(expiration);
        orderPage.proccessButton.click();
    }

    @Then("the user validate success message")
    public void the_user_validate_success_message() {

        Assert.assertTrue(orderPage.successMessage.isDisplayed());
    }

    @Then("the user validate new order details {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void the_user_validate_new_order_details(String productName, String quantity, String name, String address, String city, String zipCode, String state, String cardType, String cardNum, String exDate) {

        homePage.viewAllOrders.click();

        List<WebElement> orderDetails = allOrdersPage.newOrderDetails;
        Assert.assertEquals(orderDetails.get(1).getText(),name);
        Assert.assertEquals(orderDetails.get(2).getText(),productName);
        Assert.assertEquals(orderDetails.get(3).getText(),quantity);
        String today = BrowserUtils.todaysDate("MM/dd/yyyy");
        // While we are asserting in JUNIT first Expected and actual
        // In TestNG first actual then expected
        Assert.assertEquals(orderDetails.get(4).getText(),today);
        Assert.assertEquals(orderDetails.get(5).getText(),address);
        Assert.assertEquals(orderDetails.get(6).getText(),city);
        Assert.assertEquals(orderDetails.get(7).getText(),state);
        Assert.assertEquals(orderDetails.get(8).getText(),zipCode);
        Assert.assertEquals(orderDetails.get(9).getText(),cardType);
        Assert.assertEquals(orderDetails.get(10).getText(),cardNum);
        Assert.assertEquals(orderDetails.get(11).getText(),exDate);

    }

    @Then("the user goes the new order page")
    public void the_user_goes_the_new_order_page() {

        homePage.orderButton.click();
    }

    @Then("the user validate product headers")
    public void the_user_validate_product_headers(List<String> productHeaders) {

        List<String> actualProductHeader = BrowserUtils.getTextOfElement(orderPage.productDetails);

        Assert.assertEquals(actualProductHeader,productHeaders);
    }

    @Then("the user validate the address headers")
    public void the_user_validate_the_address_headers(DataTable dataTable) {
        // Since it is coming from the feature file. It is expected
        List<String> expected = dataTable.asList();
        List<String> actual = BrowserUtils.getTextOfElement(orderPage.addressDetails);

        Assert.assertEquals(actual,expected);
    }

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
    }

    @Then("the user clicks the all products button")
    public void the_user_clicks_the_all_products_button() {

        homePage.allProductsButton.click();
    }

    @Then("the user validate the product details")
    public void the_user_validate_the_product_details(DataTable dataTable) {

        // List<String> dataTable
       List<WebElement> pTable = homePage.productTable;

       for (int i=0; i<dataTable.asList().size(); i++){
           Assert.assertEquals("Validation of product table",dataTable.asList().get(i),pTable.get(i).getText().trim());
       }


       /*File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       String destination = System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+"png";
       File des = new File(destination);
       try {
           FileUtils.copyFile(src,des);

       }catch (IOException e){
           e.printStackTrace();
       }*/

    }

}
