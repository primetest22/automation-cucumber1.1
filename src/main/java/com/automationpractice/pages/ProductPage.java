package com.automationpractice.pages;

import com.automationpractice.utility.Utility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends Utility {
    private static final Logger log = LogManager.getLogger(ProductPage.class.getName());

    public ProductPage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(id = "quantity_wanted")
    WebElement qtyInput;

    @CacheLookup
    @FindBy(xpath = "//a[@title='Proceed to checkout']//span[1]")
    WebElement proceedToCheckout;

    @CacheLookup
    @FindBy(css = "a.product-name")
    List<WebElement> products;

    @CacheLookup
    @FindBy(xpath = "//span[text()='Add to cart']")
    WebElement addToCart;

    @CacheLookup
    @FindBy(xpath = "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']//li//a")
    List<WebElement> menuList;

    @CacheLookup
    @FindBy(xpath = "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']//li//ul//li//a")
    List<WebElement> innerMenuList;

    @CacheLookup
    @FindBy(xpath = "//span[@title='Continue shopping']//span[1]")
    WebElement continueShoppingBtn;

    public void selectFromCategory(String select) {
        for (WebElement menu : menuList) {
            if (menu.getText().equalsIgnoreCase(select)) {
                mouseHoverToElement(menu);
                log.info("Select Item from main menu : " + select + "<br>");
                break;
            }
        }
    }

    public void selectFromSubCategory(String subMenu) {
        for (WebElement item : innerMenuList)
            if (item.getText().equalsIgnoreCase(subMenu)) {
                mouseHoverToElementAndClick(item);
                log.info("Select Item from sub menu : " + subMenu + "<br>");
                break;
            }
    }

    public void addProductToCart(List<String> data, boolean isLast) {
        String category = data.get(0);
        String subCategory = data.get(1);
        String name = data.get(2);
        String quantity = data.get(4);

        selectFromCategory(category.toUpperCase());
        selectFromSubCategory(subCategory);
        int i = 0;
        while (i < products.size()) {
            WebElement product = products.get(i);
            if (product.getText().equalsIgnoreCase(name)) {
                product.click();
                break;
            }
            i++;
        }
        qtyInput.sendKeys(Keys.BACK_SPACE + quantity);
        addToCart.click();
        if (isLast) {
            continueShoppingBtn.click();
        } else {
            proceedToCheckout.click();
        }
    }
}
