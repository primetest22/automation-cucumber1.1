package com.automationpractice.pages;

import com.automationpractice.utility.Utility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MyAccountPage extends Utility {
    private static final Logger log = LogManager.getLogger(MyAccountPage.class.getName());

    public MyAccountPage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(id = "customer_firstname")
    WebElement firstName;

    @CacheLookup
    @FindBy(id = "customer_lastname")
    WebElement lastName;

    @CacheLookup
    @FindBy(id = "passwd")
    WebElement password;
    @CacheLookup
    @FindBy(id = "address1")
    WebElement address;

    @CacheLookup
    @FindBy(id = "city")
    WebElement city;

    @CacheLookup
    @FindBy(id = "id_state")
    WebElement state;

    @CacheLookup
    @FindBy(id = "postcode")
    WebElement zipcode;

    @CacheLookup
    @FindBy(id = "phone_mobile")
    WebElement phoneNumber;

    @CacheLookup
    @FindBy(id = "alias")
    WebElement reference;

    @CacheLookup
    @FindBy(xpath = "//span[normalize-space()='Register']")
    WebElement registerBtn;

    @CacheLookup
    @FindBy(xpath = "//a[@class='account']//span[1]")
    WebElement accountName;

    @CacheLookup
    @FindBy(xpath = "//span[contains(text(),'My addresses')]")
    WebElement myAddressLink;

    @CacheLookup
    @FindBy(xpath = "//ul[@class='last_item item box']")
    WebElement myAddress;

    public void enterFirstName(String text) {
        sendTextToElement(firstName, text);
        log.info("Enter Firstname : " + text + firstName.toString() + "<br>" );
    }

    public void enterLastName(String text) {
        sendTextToElement(lastName, text);
        log.info("Enter Lastname : " + text + lastName.toString()+ "<br>");
    }

    public void enterPassword(String text) {
        sendTextToElement(password, text);
        log.info("Send text to Password : " + text + password.toString()+ "<br>");
    }

    public void enterAddress(String text) {
        sendTextToElement(address, text);
        log.info("Enter Address  : " + text + address.toString()+ "<br>");
    }

    public void enterCity(String text) {
        sendTextToElement(city, text);
        log.info("Enter City : " + text + city.toString()+ "<br>");
    }

    public void selectState(String text) {
        sendTextToElement(state, text);
        log.info("Select state : " + text + state.toString()+ "<br>");
    }

    public void enterZipCode(String text) {
        sendTextToElement(zipcode, text);
        log.info("Enter Zipcode : " + text + zipcode.toString()+ "<br>");
    }

    public void enterPhoneNumber(String text) {
        sendTextToElement(phoneNumber, text);
        log.info("Enter PhoneNumber : " + text + phoneNumber.toString()+ "<br>");
    }

    public void enterReference(String text) {
        sendTextToElement(reference, text);
        log.info("Select Reference : " + text + reference.toString()+ "<br>");
    }

    public void clickOnRegisterBtn() {
        clickOnElement(registerBtn);
        log.info("Click register : " + registerBtn.toString()+ "<br>");
    }


    public void verifyAccountName(String randomString) {
        Assert.assertTrue(accountName.getText().contains(randomString));
        log.info("Verify Account name  : " + accountName.toString()+ "<br>");
    }

    public void clickMyAddressButton(){
        clickOnElement(myAddressLink);
    }
    public void verifyMyAddress(String address, String address1){
        Assert.assertTrue(myAddress.getText().contains(address));
        Assert.assertTrue(myAddress.getText().contains(address1));
        System.out.println(myAddress.getText());
        log.info("Verify My Address  : " + address + myAddress.toString()+ "<br>");
    }
}
