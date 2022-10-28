package org.mike.triestowriteautotests.tests;
import org.assertj.core.api.*;
import org.junit.*;
import org.mike.triestowriteautotests.pages.IntermediaMainPage;
import org.mike.triestowriteautotests.pages.IntermediaSupportPage;
import org.mike.triestowriteautotests.pages.IntermediaUniteProductsPage;
import org.mike.triestowriteautotests.pages.IntermediaWhoWeArePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
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

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", getProperty("chromedriver"));
        driver = new ChromeDriver();
        mainPage = new IntermediaMainPage(driver);
        uniteProductsPage = new IntermediaUniteProductsPage(driver);
        whoWeArePage = new IntermediaWhoWeArePage(driver);
        intermediaSupportPage = new IntermediaSupportPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
    }

    @Test
    public void intermediaPrices() {
        driver.get(getProperty("mainIMPage"));
        mainPage.hoverIMPopUpMenu();
        mainPage.entryIMUniteProductsPage();

        String erProPlanText = getProperty("proPlanText");
        String arProPlanText = uniteProductsPage.getProPlanText();
        assertEquals(erProPlanText, arProPlanText);

        String erEnterprisePlanText = getProperty("enterprisePlanText");
        String arEnterprisePlanText = uniteProductsPage.getEnterprisePlanText();
        assertEquals(erEnterprisePlanText, arEnterprisePlanText);
    }

    @Test
    public void chatBotIsUserACustomerButtonValidation() {
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
        SoftAssertions softAssertions = new SoftAssertions();
        driver.get(getProperty("mainIMPage"));
        mainPage.hoverAboutUsButton();
        mainPage.goToWhoWeArePage();
        whoWeArePage.getButtonAndrewGPhoto().click();

        String erAndrewGName = getProperty("expectedAndrewG_name");
        String arAndrewGName = whoWeArePage.getAndrewGName();
        assertEquals(erAndrewGName, arAndrewGName);

        String erAndrewGDuty = getProperty("expectedAndrewG_duty");
        String arAndrewGDuty = whoWeArePage.getAndrewGDuty();
        assertEquals(erAndrewGDuty, arAndrewGDuty);

        List<String> erAndrewGDetailedInfo = whoWeArePage.getExpectedResultsAndrewGDetailedInfo();
        List<String> arAndrewGDetailedInfo = whoWeArePage.getAllAndrewGBio();
        softAssertions.assertThat(erAndrewGDetailedInfo).hasSameElementsAs(arAndrewGDetailedInfo);

        softAssertions.assertAll();

        }

    @Test
    public void supportCallNumberValidation() {
        driver.get(getProperty("supportIMPage"));
        String erSupportCallNumber = getProperty("erSupportCallNumber");
        String arSupportCallNumber = intermediaSupportPage.arSupportCallNumber();
        assertEquals(erSupportCallNumber, arSupportCallNumber);
    }
}
// разберись с ожиданиями.