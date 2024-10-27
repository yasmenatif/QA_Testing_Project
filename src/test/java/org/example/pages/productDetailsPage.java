package org.example.pages;

import org.example.stepDefs.Hooks;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class productDetailsPage {
    public productDetailsPage(){
        PageFactory.initElements(Hooks.driver,this);

    }


    @FindBy(xpath = "//button[@class=\"add-to-cart\"]")
    public WebElement addToCart;
}
