package org.mike.triestowriteautotests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IntermediaSupportPage {
    public WebDriver driver;

    public IntermediaSupportPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"support\"]/section[5]/div/div/div[2]/div[2]/p[2]/span[2]/a")
    WebElement supportCallNumber;

    public String arSupportCallNumber() {
        return supportCallNumber.getText();
    }
}