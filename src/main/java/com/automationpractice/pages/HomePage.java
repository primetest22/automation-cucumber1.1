package com.automationpractice.pages;

import com.automationpractice.utility.Utility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Utility {
    private static final Logger log = LogManager.getLogger(HomePage.class.getName());

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(xpath = "//a[normalize-space()='Sign in']")
    WebElement clickSignInLink;

    @CacheLookup
    @FindBy(xpath = "//a[@title='Contact Us']")
    WebElement contactUsLink;

    public void signInLink() {
        clickOnElement(clickSignInLink);
        log.info("Clicking on SignIn Link : " + clickSignInLink.toString() + "<br>");
    }

    public void clickContactUs() {
        clickOnElement(contactUsLink);
        log.info("Clicking on ContactUs Link : " + contactUsLink.toString() + "<br>");
    }


}
