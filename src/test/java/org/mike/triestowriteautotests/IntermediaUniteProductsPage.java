package org.mike.triestowriteautotests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class IntermediaUniteProductsPage {

    public WebDriver driver;
    public IntermediaUniteProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    //WebElement chatBotFrame = driver.findElement(By.xpath("//*[@id=\"drift-frame-controller\"]/iframe"));
      //  driver.switchTo().frame(chatBotFrame);



    @FindBy(xpath = "//*[@id=\"product\"]/section[7]/div/div/div[1]/div[1]/h3") //*[@id="product"]/section[7]/div/div/div[1]/div[1]/h3
    private WebElement proPlanArea;   //

    @FindBy(xpath = "//*[@id=\"product\"]/section[7]/div/div/div[2]/div[1]/h3")
    private WebElement enterprisePlanArea;


    //WebElement chatBotFrame = driver.findElement(By.xpath("//*[@id=\"drift-frame-controller\"]/iframe"));
    //  driver.switchTo().frame(chatBotFrame);



    /*@FindBy(css = ".drift-controller-icon--avatar-avatar")
    private WebElement supportChatButton;*/
    private By supportChatButtonLocator = By.cssSelector(".drift-controller-icon--avatar-avatar");

    @FindBy(xpath = "//*[@id=\"drift-frame-chat\"]/iframe")
    private WebElement supportChatFrame;

    @FindBy(xpath = "//*[@id=\"drift-frame-controller\"]/iframe")
    private WebElement supportChatBotFrame;

    @FindBy(css = ".drift-widget-button")
    private List<WebElement> buttons;

    private By button1 = By.cssSelector(".drift-widget-button");

    public void clickOnSupportChatButton(){
        /*driver.switchTo().frame(chatBotFrame);
        WebElement supportChatButton = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(button1));


        supportChatButton.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(supportChatFrame);*/
        //WebElement supportChatBotFrame = driver.findElement(By.xpath("//*[@id=\"drift-frame-controller\"]/iframe"));
        driver.switchTo().frame(supportChatBotFrame);
        WebElement supportChatButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(supportChatButtonLocator));
        supportChatButton.click();
        driver.switchTo().defaultContent();
    }

    /*public String getCustomerPartnerButton(){              вот это кажись теперь вообще не надо, потому что берем прямой доступ.
        return buttons.get(buttons.size() - 1).getText();
    }*/
    public void checkIsUserACustomer(){
        WebElement chatFrame = driver.findElement(By.xpath("//*[@id=\"drift-frame-chat\"]/iframe"));
        driver.switchTo().frame(chatFrame);
        WebElement imACustomerChatButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//*[@id=\"root\"]/main/div[2]/div[2]/div/div[2]/div[1]/div/ul[2]/li[4]/button")));
        Assert.assertEquals(ImPagesData.getProperty("customerPartnerButton"), imACustomerChatButton.getText());
    }

    public String getProPlanText() {
        return proPlanArea.getText();
    }

    public String getEnterprisePlanText() {
        return enterprisePlanArea.getText();
    }


}
