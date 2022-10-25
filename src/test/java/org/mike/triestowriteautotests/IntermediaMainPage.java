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

    // checking youtube link
    //WebElement ytButton = driver.findElement(By.xpath("//*[@id=\"social\"]/div[2]/a[1]"));
    //String ytActualURL = ytButton.getAttribute("href");

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

    public void entryIMUniteProductsPage() {
        imUniteButton.click();
    }

    public void hoverIMPopUpMenu() {
        Actions action = new Actions(driver);
        action.moveToElement(productsButton).perform(); // let move cursor to "products" chapter
    }

    public void checkYouTubeLink(){
        String ytActualURL = ytButton.getAttribute("href");
        Assert.assertEquals(ImPagesData.getProperty("YouTubeLink"), ytActualURL);
    }

    public void checkFaceBookLink(){
        String fbActualURL = fbButton.getAttribute("href");
        Assert.assertEquals(ImPagesData.getProperty("FaceBookLink"), fbActualURL);
    }

    public void checkTwitterLink(){
        String twActualURL = twButton.getAttribute("href");
        Assert.assertEquals(ImPagesData.getProperty("TwitterLink"), twActualURL);
    }

    public void checkLinkedInLink(){
        String lInActualURL = lInButton.getAttribute("href");
        Assert.assertEquals(ImPagesData.getProperty("LinkedInLink"), lInActualURL);
    }

    public void hoverAboutUsButton(){
        Actions action = new Actions(driver);
        action.moveToElement(aboutUsButton).perform();
    }

    public void goToWhoWeArePage(){
        whoWeAreButton.click();
    }
}