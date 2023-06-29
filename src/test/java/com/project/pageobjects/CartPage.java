package com.project.pageobjects;

import com.project.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    //PAGE OBJECT CLASS
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver); //Toda classe child deve ter o super da classe mãe
        this.driver = driver;
        PageFactory.initElements(driver, this); // necessário para triggar os @FindBy
    }


    @FindBy (css = ".totalRow button")
    WebElement checkoutElement;

    @FindBy (css = ".cartSection h3")
    private List<WebElement> productTitles;



    public Boolean verifyProductDisplay(String productName) {
        return productTitles.stream().anyMatch(i->i.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout() {
        checkoutElement.click();
        return new CheckoutPage(driver);
    }

}
