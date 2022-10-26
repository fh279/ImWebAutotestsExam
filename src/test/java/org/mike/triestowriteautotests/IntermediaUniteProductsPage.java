package org.mike.triestowriteautotests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class IntermediaUniteProductsPage {

    public WebDriver driver;

    public IntermediaUniteProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"drift-frame-controller\"]/iframe")
    WebElement supportChatBotFrame;

    @FindBy(xpath = "//*[@id=\"product\"]/section[7]/div/div/div[1]/div[1]/h3")
    private WebElement proPlanArea;

    @FindBy(xpath = "//*[@id=\"product\"]/section[7]/div/div/div[2]/div[1]/h3")
    private WebElement enterprisePlanArea;

    private By supportChatButtonLocator = By.cssSelector("#root > main > div.drift-widget-message-preview-wrapper > div > div > span"); //By.xpath("//*[@id=\"root\"]/main/div[2]/div"); // This is a locator not only of the avatar itself, but also of the entire area, clicking on which opens a chat with support

    private By userIsAPartnerCustomerButton = By.xpath("//*[@id=\"root\"]/main/div[2]/div[2]/div/div[2]/div[1]/div/ul[2]/li[4]/button");

    private By supportChatButtonFrameLocator = By.xpath("//*[@id=\"drift-frame-chat\"]/iframe");

    public void clickOnSupportChatButton() throws InterruptedException {
        driver.switchTo().frame(supportChatBotFrame);
        WebElement supportChatButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(supportChatButtonLocator));
        Thread.sleep(3000);  // РАЗОБРАТЬСЯ С ОЖИДАНИЯМИ И УБРАТЬ ЭТО!!! ПРОБЛЕМА В НЕПРОГРУЗЕ КНОПКИ ЭШЛИ!!!
        supportChatButton.click();
        /*long time = System.currentTimeMillis();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(driver -> driver.findElement(supportChatButtonLocator));*/

        driver.switchTo().defaultContent();
    }

    public WebElement getIsUserACustomerButton() throws InterruptedException {
        WebElement supportChatFrame = driver.findElement(By.xpath("//*[@id=\"drift-frame-chat\"]/iframe"));
        driver.switchTo().frame(supportChatFrame);
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(userIsAPartnerCustomerButton));
    }

    public String getProPlanText() {
        return proPlanArea.getText();
    }

    public String getEnterprisePlanText() {
        return enterprisePlanArea.getText();
    }
}