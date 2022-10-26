package org.mike.triestowriteautotests.tests;

import org.junit.*;
import org.mike.triestowriteautotests.pages.IntermediaMainPage;
import org.mike.triestowriteautotests.pages.IntermediaSupportPage;
import org.mike.triestowriteautotests.pages.IntermediaUniteProductsPage;
import org.mike.triestowriteautotests.pages.IntermediaWhoWeArePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.mike.triestowriteautotests.dataprovider.ImPagesData.getProperty;

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
        System.setProperty("webdriver.chrome.driver", getProperty("chromedriver"));
        driver = new ChromeDriver();
        mainPage = new IntermediaMainPage(driver);
        uniteProductsPage = new IntermediaUniteProductsPage(driver);
        whoWeArePage = new IntermediaWhoWeArePage(driver);
        intermediaSupportPage = new IntermediaSupportPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void intermediaPricesTest() {
        driver.get(getProperty("mainIMPage"));
        mainPage.hoverIMPopUpMenu();
        mainPage.entryIMUniteProductsPage();
        assertEquals(getProperty("proPlanText"), uniteProductsPage.getProPlanText());
        assertEquals(getProperty("enterprisePlanText"), uniteProductsPage.getEnterprisePlanText());
    }

    @Test
    public void chatBotIsUserACustomer() {
        driver.get(getProperty("mainIMPage"));
        mainPage.hoverIMPopUpMenu();
        mainPage.entryIMUniteProductsPage();
        uniteProductsPage.clickOnSupportChatButton();
        String erCustomerPartnerButton = getProperty("customerPartnerButton");
        String arCustomerPartnerButton = uniteProductsPage.getIsUserACustomerButton().getText();
        assertEquals(erCustomerPartnerButton, arCustomerPartnerButton);
    }

    @Test
    public void linksSocialMediaValidation() {
        driver.get(getProperty("mainIMPage"));

        String erYouTubeLink = getProperty("YouTubeLink");
        String arYouTubeLink = mainPage.getYouTubeLink();
        assertEquals(erYouTubeLink, arYouTubeLink);

        String erFaceBookLink = getProperty("FaceBookLink");
        String arFaceBookLink = mainPage.getFaceBookLink();
        assertEquals(erFaceBookLink, arFaceBookLink);

        String erTwitterLink = getProperty("TwitterLink");
        String arTwitterLink = mainPage.getTwitterLink();
        assertEquals(erTwitterLink, arTwitterLink);

        String erLinkedInLink = getProperty("LinkedInLink");
        String arLinkedInLink = mainPage.getLinkedInLink();
        assertEquals(erLinkedInLink, arLinkedInLink);
    }

    @Test
    public void dataValidationAndrewG() {
        driver.get(getProperty("mainIMPage"));
        mainPage.hoverAboutUsButton();
        mainPage.goToWhoWeArePage();

        String erAndrewGName = getProperty("expectedAndrewG_name");
        String arAndrewGName = whoWeArePage.getAndrewGName();
        assertEquals(erAndrewGName, arAndrewGName);

        String erAndrewGDuty = getProperty("expectedAndrewG_duty");
        String arAndrewGDuty = whoWeArePage.getAndrewGDuty();
        assertEquals(erAndrewGDuty, arAndrewGDuty);

        whoWeArePage.getButtonAndrewGPhoto().click();
        for (int i = 0; i < whoWeArePage.getAllAndrewGBio().size(); i++) {
            String arAndrewGDetailedInfo = whoWeArePage.getAllAndrewGBio().get(i).getText();
            String erAndrewGDetailedInfo = whoWeArePage.getExpectedResultsAndrewGDetailedInfo()[i];
            assertEquals(erAndrewGDetailedInfo, arAndrewGDetailedInfo);
            // выполни сравнение 2 массивов, а не проход, будет лаконичнее.
            // либо вверни сюда SoftAssert.
        }
    }

    @Test
    public void supportCallNumberValidation() {
        driver.get(getProperty("supportIMPage"));
        String erSupportCallNumber = getProperty("erSupportCallNumber");
        String arSupportCallNumber = intermediaSupportPage.arSupportCallNumber();
        assertEquals(erSupportCallNumber, arSupportCallNumber);
    }
}
// переделай метод с Андреем Г.
// разберись с ожиданиями.