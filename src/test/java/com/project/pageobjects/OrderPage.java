package com.project.pageobjects;

import com.project.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> orderProducts;

    public Boolean verifyOrderDisplay(String productName) {
        return orderProducts.stream().anyMatch(i->i.getText().equalsIgnoreCase(productName));
    }
}
