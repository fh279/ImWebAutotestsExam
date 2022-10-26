package org.mike.triestowriteautotests;
//import org.checkerframework.checker.units.qual.C;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class IntermediaTests {
    public static WebDriver driver;
    public static IntermediaMainPage mainPage;
    public static IntermediaUniteProductsPage uniteProductsPage;
    public static IntermediaWhoWeArePage whoWeArePage;
    public static IntermediaSupportPage intermediaSupportPage;
/*Задача
Open:
https://www.intermedia.com/
1. Validate price for UNITE PRO is $27.99, UNITE ENTERPRISE $32.99 (Products → Intermedia Unite)
2. Check chatBot contains replay “I’m a current customer/partner”.
3. Validate links on social media(youtube, facebook, twitter, inst)
4. Vaildate that Andrew G has the proper position at About US page (Andrew Gachechiladze EVP of Product Development and Engineering).Validate that after click on the name, detailed information is displayed
5. Validate Contact US page contains proper details: Intermedia Support 800-379-7729 */
    // 1. метод сравнения строк в проверки Андрюхи вынеси в отдельный класс вспомогательных методов.
    //         может быть туда еще какие то методы можно будет вынести
    // 2. page object
    // 3. что то там про пропертис.
    // 4. вынеси настройки, хардкод и прочее в imPagesData.properties и назови фавйл по человечьи.

    //описывать имя тестового метода так: Что_условия_результат()

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ImPagesData.getProperty("chromedriver"));
        driver = new ChromeDriver();
        mainPage = new IntermediaMainPage(driver);
        uniteProductsPage = new IntermediaUniteProductsPage(driver);
        whoWeArePage = new IntermediaWhoWeArePage(driver);
        intermediaSupportPage = new IntermediaSupportPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @Test
    public void intermediaPricesTest() throws InterruptedException {

        System.out.println("intermediaPricesTest - погнали");
        driver.get(ImPagesData.getProperty("mainIMPage"));
        mainPage.hoverIMPopUpMenu();
        mainPage.entryIMUniteProductsPage();
        Assert.assertEquals(ImPagesData.getProperty("proPlanText"), uniteProductsPage.getProPlanText());
        Assert.assertEquals(ImPagesData.getProperty("enterprisePlanText"), uniteProductsPage.getEnterprisePlanText());
        //driver.quit();
        System.out.println("intermediaPricesTest - Пройдено");
    }

    @Test
    public void chatBotIsUserACustomer() throws InterruptedException {
        driver.get(ImPagesData.getProperty("mainIMPage"));
        mainPage.hoverIMPopUpMenu();
        mainPage.entryIMUniteProductsPage();
        uniteProductsPage.clickOnSupportChatButton(); // да бля!!!!
        String erCustomerPartnerButton = ImPagesData.getProperty("customerPartnerButton");
        String arCustomerPartnerButton = uniteProductsPage.getIsUserACustomerButton().getText(); // да сука!!!
        Assert.assertEquals(erCustomerPartnerButton, arCustomerPartnerButton);
    }

    @Test
    public void linksYTFBTwitrLinkdInValidation() {
        driver.get(ImPagesData.getProperty("mainIMPage"));

        String erYouTubeLink = ImPagesData.getProperty("YouTubeLink");
        String arYouTubeLink = mainPage.getYouTubeLink();
        Assert.assertEquals(erYouTubeLink, arYouTubeLink);

        String erFaceBookLink = ImPagesData.getProperty("FaceBookLink");
        String arFaceBookLink = mainPage.getFaceBookLink();;
        Assert.assertEquals(erFaceBookLink, arFaceBookLink);

        String erTwitterLink = ImPagesData.getProperty("TwitterLink");
        String arTwitterLink = mainPage.getTwitterLink();
        Assert.assertEquals(erTwitterLink, arTwitterLink);

        String erLinkedInLink = ImPagesData.getProperty("LinkedInLink");
        String arLinkedInLink = mainPage.getLinkedInLink();
        Assert.assertEquals(erLinkedInLink, arLinkedInLink);

        System.out.println("linksYTFBTwitrLinkdInValidation - Пройдено");
    }

    @Test
    public void dataValidationAndrewG() {
        System.out.println("dataValidationAndrewG - погнали");
        driver.get(ImPagesData.getProperty("mainIMPage"));
        mainPage.hoverAboutUsButton();
        mainPage.goToWhoWeArePage();
        String erAndrewGName = ImPagesData.getProperty("expectedAndrewG_name");
        Assert.assertEquals(erAndrewGName, whoWeArePage.getAndrewGName());
        String erAndrewGDuty = ImPagesData.getProperty("expectedAndrewG_duty");
        Assert.assertEquals(erAndrewGDuty, erAndrewGDuty);
        whoWeArePage.getButtonAndrewGPhoto().click();
        for (int i = 0; i < whoWeArePage.getAllAndrewGBio().size(); i++) {
            String arAndrewGDetailedInfo = whoWeArePage.getAllAndrewGBio().get(i).getText();
            String erAndrewGDetailedInfo = whoWeArePage.getExpectedResultsAndrewGDetailedInfo()[i];
            Assert.assertEquals(erAndrewGDetailedInfo, arAndrewGDetailedInfo);
        }
        //driver.quit();
        System.out.println("dataValidationAndrewG - Пройдено");
    }

    @Test

    public void supportCallNumberValidation() {

        /* Создаем драйвер
         *  браузер в полноэкранный режим
         *  Неявное ожидание 1 секунда
         *  Ищем элемент с номером телефона, xPath вот:
         *       "//*[@id="support"]/section[5]/div/div/div[2]/div[2]/p[2]/span[2]/a"
         *       пихаем его в WebElement supportCallNumber
         *  Пишем сверку найденного в supportCallNumber текста и номера тех поддержки: 800-379-7729
         * */
        driver.get(ImPagesData.getProperty("supportIMPage"));

        String erSupportCallNumber = ImPagesData.getProperty("erSupportCallNumber");
        String arSupportCallNumber = intermediaSupportPage.arSupportCallNumber();
        Assert.assertEquals(erSupportCallNumber, arSupportCallNumber);
    }
}
/*
* 1. вытащить ассерты в тестовый класс. в pageObject
* 2. обернуть ER / AR в ассертах в красивые переменные. Все прям в классе.
* 3.
* */