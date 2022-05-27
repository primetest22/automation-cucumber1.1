package com.automationpractice.pages;


import com.automationpractice.utility.Utility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AuthPage extends Utility {
    private static final Logger log = LogManager.getLogger(AuthPage.class.getName());

    public AuthPage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(xpath = "//input[@id='email_create']")
    WebElement emailCreate;

    @CacheLookup
    @FindBy(xpath = "//span[normalize-space()='Create an account']")
    WebElement createAccountButton;

    @CacheLookup
    @FindBy(id = "email")
    WebElement emailAddressField;

    @CacheLookup
    @FindBy(id="passwd")
    WebElement passwordField;

    @CacheLookup
    @FindBy(xpath = "//span[text()[normalize-space()='Sign in']]")
    WebElement signInBtn;

    public void verifySignUpPage(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Condition true");
        log.info("Verify page : " + expectedUrl + "<br>");
    }
    public void enterEmail(String text) {
        sendTextToElement(emailCreate, text);
        log.info("Enter Email : " +text+ emailCreate.toString() + "<br>");
    }
    public void clickCreateAccountButton() {
        clickOnElement(createAccountButton);
        log.info("Clicking on create account : " + createAccountButton.toString() + "<br>");
    }

    public void enterEmailAddress(String randomEmail){
        sendTextToElement(emailAddressField, randomEmail);
        log.info("Enter Random Email : " +randomEmail+ emailAddressField.toString() + "<br>");
    }
    public void enterPassword(String password){
        sendTextToElement(passwordField, password);
        log.info("Enter Password : " +password+ passwordField.toString() + "<br>");
    }
    public void clickSignIn(){
        clickOnElement(signInBtn);
        log.info("Clicking on SignIn button : " + signInBtn.toString() + "<br>");
    }


}
