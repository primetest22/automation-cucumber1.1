package com.automationpractice.pages;

import com.automationpractice.utility.Utility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ContactPage extends Utility {
    private static final Logger log = LogManager.getLogger(ContactPage.class.getName());
    public ContactPage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(xpath = "//h1[normalize-space()='Customer service - Contact us']")
    WebElement contactUsPageVerifyText;

    @CacheLookup
    @FindBy (id = "id_contact")
    WebElement subjectHeadingDropdown;

    @CacheLookup
    @FindBy (name = "id_order")
    WebElement orderReferenceDropdown;

    @CacheLookup
    @FindBy (xpath = "(//select[@name='id_product'])[1]")
    WebElement productDropdown;

    @CacheLookup
    @FindBy (id = "message")
    WebElement messageBox;

    @CacheLookup
    @FindBy (xpath = "//span[text()='Send']")
    WebElement sendButton;

    public void verifyContactPageText(String text){
        log.info("verifying Customer Service Page text : " + text + "<br>");
        Assert.assertTrue(contactUsPageVerifyText.getText().contains(text));
    }
    public void chooseASubjectHeading(String subject){
        log.info("choose a subject heading : " + subject + "<br>");
        selectByVisibleTextFromDropDown(subjectHeadingDropdown, subject);
    }

    public void chooseAnOrderReference(){
        log.info("choose a order reference : "  + "<br>");
        selectByIndexFromDropDown(orderReferenceDropdown, 1);
    }

    public void chooseAProductFromDropdown(){
        log.info("choose a product : " + "<br>");
        selectByIndexFromDropDown(productDropdown, 1);
    }

    public void inputMessage(String message){
        log.info("input refund message : " + message + "<br>");
        sendTextToElement(messageBox, message );
    }

    public void clickOnSendButton(){
        log.info("click on send button : " + sendButton.toString() + "<br>");
        clickOnElement(sendButton);
    }

}
