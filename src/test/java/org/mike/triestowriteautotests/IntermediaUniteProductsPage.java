package org.mike.triestowriteautotests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;

import java.util.List;

public class IntermediaUniteProductsPage {

    public WebDriver driver;
    public IntermediaUniteProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    //WebElement chatBotFrame = driver.findElement(By.xpath("//*[@id=\"drift-frame-controller\"]/iframe"));
      //  driver.switchTo().frame(chatBotFrame);

    @FindBy(xpath = "//*[@id=\"drift-frame-controller\"]/iframe")
    WebElement supportChatBotFrame;

    @FindBy(xpath = "//*[@id=\"product\"]/section[7]/div/div/div[1]/div[1]/h3") //*[@id="product"]/section[7]/div/div/div[1]/div[1]/h3
    private WebElement proPlanArea;   //

    @FindBy(xpath = "//*[@id=\"product\"]/section[7]/div/div/div[2]/div[1]/h3")
    private WebElement enterprisePlanArea;

    private By supportChatButtonLocator = By.xpath("//*[@id=\"root\"]/main/div[2]/div"); // сейчас тут локатор аватарка + текст. большое поле.

    @FindBy(css = ".drift-widget-button")
    private List<WebElement> buttons;

    private By userIsAPartnerCustomerButton = By.xpath("//*[@id=\"root\"]/main/div[2]/div[2]/div/div[2]/div[1]/div/ul[2]/li[4]/button");

    private By supportChatButtonFrameLocator = By.xpath("//*[@id=\"drift-frame-chat\"]/iframe");

    public void clickOnSupportChatButton(){
        driver.switchTo().frame(supportChatBotFrame);
        WebElement supportChatButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(supportChatButtonLocator));

        supportChatButton.click();

        driver.switchTo().defaultContent();
    }

    public WebElement getIsUserACustomerButton(){
        WebElement supportChatFrame = driver.findElement(By.xpath("//*[@id=\"drift-frame-chat\"]/iframe"));
        driver.switchTo().frame(supportChatFrame); // переключаемся на фрейм уже самого чата.
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(userIsAPartnerCustomerButton)); // в чате мы уже видим..

    }

    public String getProPlanText() {
        return proPlanArea.getText();
    }

    public String getEnterprisePlanText() {
        return enterprisePlanArea.getText();
    }


}
