package com.project.pageobjects;

import com.project.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver); //Toda classe child deve ter o super da classe mãe
        this.driver = driver;
        PageFactory.initElements(driver, this); // necessário para triggar os @FindBy
    }

    @FindBy (css = "h1")
    WebElement confirmOrderText;

    public String confirmOrderText() {
        return confirmOrderText.getText();
    }

}
