package org.mike.triestowriteautotests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IntermediaMainPage {

    public WebDriver driver;

    public IntermediaMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"primary-nav\"]/li[1]/a")
    private WebElement productsButton;

    @FindBy(xpath = "//*[@id=\"primary-nav\"]/li[1]/div/div/div/ul[1]/li[2]/a")
    private WebElement imUniteButton;

    @FindBy(xpath = "//*[@id=\"social\"]/div[2]/a[1]")
    private WebElement ytButton;

    @FindBy(xpath = "//*[@id=\"social\"]/div[2]/a[2]")
    private WebElement fbButton;

    @FindBy(xpath = "//*[@id=\"social\"]/div[2]/a[3]")
    private WebElement twButton;

    @FindBy(xpath = "//*[@id=\"social\"]/div[2]/a[4]")
    private WebElement lInButton;

    @FindBy(xpath = "//*[@id=\"primary-nav\"]/li[3]/a")
    private WebElement aboutUsButton;

    @FindBy(xpath = "//*[@id=\"primary-nav\"]/li[3]/div/div/div/ul[1]/li[2]/a")
    private WebElement whoWeAreButton;

    public void hoverIMPopUpMenu() {
        Actions action = new Actions(driver);
        action.moveToElement(productsButton).perform();
    }

    public void entryIMUniteProductsPage() {
        imUniteButton.click();
    }

    public String getYouTubeLink() {
        return ytButton.getAttribute("href");
    }

    public String getFaceBookLink() {
        return fbButton.getAttribute("href");
    }

    public String getTwitterLink() {
        return twButton.getAttribute("href");
    }

    public String getLinkedInLink() {
        return lInButton.getAttribute("href");
    }

    public void hoverAboutUsButton() {
        Actions action = new Actions(driver);
        action.moveToElement(aboutUsButton).perform();
    }

    public void goToWhoWeArePage() {
        whoWeAreButton.click();
    }
}