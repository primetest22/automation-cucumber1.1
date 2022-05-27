package com.automationpractice.cucumber.steps;

import com.automationpractice.pages.*;
import com.automationpractice.utility.Utility;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class OnlineShoppingTestSteps extends Utility {
    String randomEmail;
    String randomUsername;
    String password = "Abc1234";

    @Given("^I am on home page$")
    public void iAmOnHomePage() throws IOException {
        File file = new File("data.txt"); // References file in root directory called "data.txt", which is where the random email and username are stored
        file.createNewFile(); // Creates the file if it doesn't exist
        FileWriter writer = new FileWriter(file); // Allows writing to file
        randomEmail = getRandomEmail();
        randomUsername = getRandomUserName();
        writer.write(randomEmail + " & " + randomUsername); // Write new data to file
        writer.close();
    }

    @Then("^Navigate to SignUp page$")
    public void navigateToSignUpPage() {
        new HomePage().signInLink();
        new AuthPage().verifySignUpPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    @And("^create an account with random username$")
    public void createAnAccountWithRandomUsername() {
        new AuthPage().enterEmail(randomEmail);
        new AuthPage().clickCreateAccountButton();
        new MyAccountPage().enterFirstName(randomUsername);
        new MyAccountPage().enterLastName("Peter");
        new MyAccountPage().enterPassword(password);
        new MyAccountPage().enterAddress("1, Bond Street");
        new MyAccountPage().enterCity("london");
        new MyAccountPage().selectState("Alaska");
        new MyAccountPage().enterZipCode("11111");
        new MyAccountPage().enterPhoneNumber("0123456789");
        new MyAccountPage().enterReference("Home");
        new MyAccountPage().clickOnRegisterBtn();
        new MyAccountPage().verifyAccountName(randomUsername);
    }

    @Given("^I am on the Sign In Page$")
    public void iAmOnTheSignInPage() throws IOException {
        new HomePage().signInLink();
        File file = new File("data.txt"); // Reference "data.txt", where email and username are stored
        Scanner reader = new Scanner(file); // Scanner class allows for reading the file
        if (reader.hasNextLine()) {
            String data = reader.nextLine(); // Gets data from file
            randomEmail = Arrays.stream(data.split("&")).collect(Collectors.toList()).get(0);
            randomUsername = Arrays.stream(data.split("&")).collect(Collectors.toList()).get(1);
        }
        reader.close();
    }

    @When("^Login using newly create credentials$")
    public void loginUsingNewlyCreateCredentials() {
        new AuthPage().enterEmailAddress(randomEmail);
        new AuthPage().enterPassword(password);
        new AuthPage().clickSignIn();
    }

    @Then("^I shall verify the address information in my address section$")
    public void iShallVerifyTheAddressInformationInMyAddressSection() {
        new MyAccountPage().clickMyAddressButton();
        new MyAccountPage().verifyMyAddress("1, Bond Street", "london, Alaska 11111");
    }

    @And("^I add below product to cart$")
    public void iAddBelowProductToCart(DataTable table) {
        AtomicInteger rowCount = new AtomicInteger(); // AtomicInteger is an integer that is updated atomically, i.e. in one thread instead of two
        table.getGherkinRows().forEach((DataTableRow row) -> {
            rowCount.getAndIncrement(); // Increments rowCount
            if (rowCount.get() != 1) { // Don't want first row, as it is the column headings rather than the column data
                new ProductPage().addProductToCart(row.getCells(), rowCount.get() == table.getGherkinRows().size() - 1);
            }
        });
    }

    @Then("^I shall validate shopping cart as below$")
    public void iShallValidateShoppingCartAsBelow(DataTable table) {
        AtomicInteger rowCount = new AtomicInteger(0);
        table.getGherkinRows().forEach((DataTableRow row) -> {
            rowCount.getAndIncrement();
            if (rowCount.get() != 1) {
                new ShoppingCartPage().verifyShoppingCart(row.getCells());
            }
        });
    }

    @Then("^I shall be able to Buy the product$")
    public void iShallBeAbleToBuyTheProduct() {
        new ShoppingCartPage().clickProceedToCheckOut();
        new ShoppingCartPage().clickProceedToCheckOutAddressPage();
        new ShoppingCartPage().selectTerms();
        new ShoppingCartPage().clickProceedToCheckoutShippingPage();
        new ShoppingCartPage().selectPayByCheck();
        new ShoppingCartPage().iConfirmMyOrder();
    }

    @And("^I shall be able to Buy using cheque payment$")
    public void iShallBeAbleToBuyUsingChequePayment() {
        new ShoppingCartPage().verifyCheckPaymentOptionUsedToCompleteShopping("Your order on My Store is complete.");
    }

    @And("^I am on the contact page$")
    public void iAmOnTheContactPage() {
        new HomePage().clickContactUs();
        new ContactPage().verifyContactPageText("CUSTOMER SERVICE - CONTACT US");

    }

    @Then("^I send refund request to customer care for previous order$")
    public void iSendRefundRequestToCustomerCareForPreviousOrder() {
        new ContactPage().chooseASubjectHeading("Customer service");
        new ContactPage().chooseAnOrderReference();
        new ContactPage().chooseAProductFromDropdown();
        new ContactPage().inputMessage("Please arrange refund of requested product");
        new ContactPage().clickOnSendButton();
    }
}
