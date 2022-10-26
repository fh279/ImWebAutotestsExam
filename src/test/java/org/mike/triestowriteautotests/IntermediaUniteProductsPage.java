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

    @FindBy(xpath = "//*[@id=\"product\"]/section[7]/div/div/div[1]/div[1]/h3") //*[@id="product"]/section[7]/div/div/div[1]/div[1]/h3
    private WebElement proPlanArea;   //

    @FindBy(xpath = "//*[@id=\"product\"]/section[7]/div/div/div[2]/div[1]/h3")
    private WebElement enterprisePlanArea;





    // Это фрейм уже открытого чата. В нём уже видна искомая кнопка.
    /*@FindBy(xpath = "//*[@id=\"drift-frame-chat\"]/iframe")
    private WebElement supportChatFrame;*/

    private By supportChatButtonLocator = By.xpath("//*[@id=\"root\"]/main/div[2]/div"); // сейчас тут локатор аватарка + текст. большое поле.
    // #root > main > div.drift-widget-chat-wrapper.drift-widget-chat-wrapper__CONVERSATION.drift-widget-chat-wrapper__open.drift-widget-chat-wrapper__active-conversation.drift-widget-chat-wrapper__no-footer-content > div.drift-widget-message-history.drift-widget-message-history--no-composer > div > div:nth-child(2) > div.drift-widget-message-group-list > div > ul.drift-widget-message-group.drift-widget-message-group-type--END_USER.drift-bot-buttons--set.drift-bot-buttons--visible > li:nth-child(4) > button
    //.drift-controller-icon--avatar-avatar - вот такой css selector работал, но теперь вообще через раз.
    // //*[@id="root"]/main/div[2]/div[2]/div/div[2]/div[1]/div/ul[2]/li[4]/button - это держим в запасе, может верный локатор. хз.
    //*[@id="root"]/main/div[3]/div[1]/div[1]/div/div
    //*[@id="root"]/main/div[2]/div - общее поле аватарки и текста. по клику сюда все равно открывается бот. может так прокатит?

    // это фрейм кнопки открытия чат бота. без него не открыть чат. Нужен.
    /*@FindBy(xpath = "//*[@id=\"drift-frame-controller\"]/iframe")
    private WebElement supportChatButtonFrame;*/

    // А это - локатор кнопки чата
    //private By buttonFrameLocator = By.xpath("//*[@id=\"drift-frame-chat\"]/iframe");

    //вот нахрена мне список этих кнопок, если я к кнопке обращаюсь на прямую? или не обращаюсь?
    @FindBy(css = ".drift-widget-button")
    private List<WebElement> buttons;

    private By userIsAPartnerCustomerButton = By.cssSelector(".drift-widget-button");

    private By supportChatButtonFrameLocator = By.xpath("//*[@id=\"drift-frame-chat\"]/iframe");

    public void clickOnSupportChatButton(){
        /*driver.switchTo().frame(chatBotFrame);
        WebElement supportChatButton = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(button1));

        supportChatButton.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(supportChatFrame);*/


        /*WebElement supportChatButtonFrame = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(supportChatButtonFrameLocator)); */ //elementToBeClickable(supportChatButtonFrameLocator));
        WebElement supportChatButtonFrame = driver.findElement(supportChatButtonFrameLocator);


        driver.switchTo().frame(supportChatButtonFrame);  // перешли во фрейм кнопки с Эшлей.

        /*WebElement supportChatButton = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(supportChatButtonLocator));*/        // presenceOfElementLocated(supportChatButtonLocator));    //elementToBeClickable(supportChatButtonLocator));  // нашли кнопку. АХТУНГ! ТУТ ЛОКАТОР СПОРНЫЙ! ПРОВЕРЬ!!

        WebElement supportChatButton = driver.findElement(supportChatButtonLocator);

        supportChatButton.click(); // кликнули на кнопку чата
        driver.switchTo().defaultContent(); // вышли в головной фрейм. Работа метода на этом момента закончена.
    }

    public WebElement getIsUserACustomerButton(){
        //WebElement supportChatFrame = driver.findElement(By.xpath("//*[@id=\"drift-frame-chat\"]/iframe"));

        /*WebElement supportChatFrame = new WebDriverWait(driver, 10).  // находим фрейм чата (считаем что
                until(ExpectedConditions.elementToBeClickable(buttonFrameLocator)); // мы уже нажали на кнопку открытия чата)*/

        WebElement supportChatFrame = driver.findElement(By.xpath("//*[@id=\"drift-frame-chat\"]/iframe"));
        driver.switchTo().frame(supportChatFrame); // переключаемся на фрейм уже самого чата.
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(userIsAPartnerCustomerButton)); // в чате мы уже видим..
        // ... эту злосчастную кнопку, так что сразу считываем ее. Локатор есть. Опыт успешного обранужения тоже.

        /*public void checkIsUserACustomer(){
            WebElement chatFrame = driver.findElement(By.xpath("//*[@id=\"drift-frame-chat\"]/iframe"));
                                                                //*[@id="drift-frame-chat"]/iframe
            driver.switchTo().frame(chatFrame);
            WebElement imACustomerChatButton = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath("//*[@id=\"root\"]/main/div[2]/div[2]/div/div[2]/div[1]/div/ul[2]/li[4]/button")));
            Assert.assertEquals(ImPagesData.getProperty("customerPartnerButton"), imACustomerChatButton.getText());
        }*/





         //.xpath("//*[@id="root"]/main/div[2]/div[2]/div/div[2]/div[1]/div/ul[2]/li[4]/button   -   я хз что это и как оно тут оказалось.
         //.xpath("//*[@id="root"]/main/div[2]/div[2]/div/div[2]/div[1]/div/ul[2]/li[4]/button")));

    }

    public String getProPlanText() {
        return proPlanArea.getText();
    }

    public String getEnterprisePlanText() {
        return enterprisePlanArea.getText();
    }


}
