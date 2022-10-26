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
        /*
         * Создаем экземпляр драйвера, присваиваиваем его переменной
         * выставляем настройку размера экрана - максимальный (полный)
         * Выставляем неявлую задержку выбрасывания исключений == 5 сек.
         * Заходим на страницу https://www.intermedia.com/products/unite
         * Нахожу iframe, где находится кнопка открытия чата (xpath вот: "//*[@id="drift-frame-controller"]/iframe" ).
         *   Сохраняем его в переменную chatBotFrame2.
         * Переключаемся на frame chatBotFrame2,
         * Нахожу кнопку открытия чата с поддержкой xPaht вот: "//*[@id="root"]/main/div[3]/div[1]/div[1]/div/div" и
         *   сохраняем его в переменную WebElement chatButtonWithAshley.
         * Кликаем на chatButtonWithAshley.
         * Переключиться обратно в корневой frame.
         * Находим iframe, в котором содержится искомый чат (xpath вот: "//*[@id="drift-frame-chat"]/iframe") и сохраняем
         *   его в переменную WebElement supportChatFrame
         * Переключаемся на frame supportChatFrame
         * !!!НЕПРОВЕРЕНО!!! Нахожу кнопку с искомым текстом.
         *   (xPath: "//*[@id="root"]/main/div[2]/div[2]/div/div[2]/div[1]/div/ul[2]/li[4]") и сохраняю ее в переменную
         *   WebElement i'mCurrentCustomerButton.
         * Кликаю на i'mCurrentCustomerButton
         * */
        System.out.println("chatBotIsUserACustomer - погнали");
        //driver = new ChromeDriver();
        driver.get(ImPagesData.getProperty("mainIMPage"));
        mainPage.hoverIMPopUpMenu();
        mainPage.entryIMUniteProductsPage();
        uniteProductsPage.clickOnSupportChatButton(); // да бля!!!!
        uniteProductsPage.getIsUserACustomerButton();
        String erCustomerPartnerButton = ImPagesData.getProperty("customerPartnerButton");
        String arCustomerPartnerButton = uniteProductsPage.getIsUserACustomerButton().getText(); // да сука!!!
        Assert.assertEquals(erCustomerPartnerButton, arCustomerPartnerButton);
        //driver.quit();
        System.out.println("chatBotIsUserACustomer - Пройдено");
    }

    @Test
    public void linksYTFBTwitrLinkdInValidation() {
        /* 3. Validate links on social media(youtube, facebook, twitter, inst)
         * создать драйвер
         * Браузер на полный экран
         * Неявная задержка 8 секунд
         * Послать драйверу ссылку на сайт IM: "https://www.intermedia.com/"
         * Найти кнопку со ссылкой на ютуб страничку. xPath = "//*[@id="social"]/div[2]/a[1]/img". Создать WebElement
         *   ytButton, запихать в него найденную кнопку (внимание чтоб там была ссылка!)
         * Вытащить из кнопки URL через getAttribute("href")
         * Сравнить данную ссылку и что получено в предыдущем шаге
         * Сделать то же для всех остальных ссылок
         */
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
        /*
         * Создаем драйвер, браузер на полный экран, неявное ожидание 8 секунд
         * Заходим на https://www.intermedia.com/about-us/who-we-are
         * Найти область, где размещено имя Андрея Г.
         *      (xPath тут: "//*[@id="about_overview"]/section[3]/div[1]/div/div[1]/div[5]") , - блин, это фигня какая-то
         *       //*[@id="about_overview"]/section[3]/div[1]/div/div[1]/div[5]/a/div/p[1]   -   вот тут имя Андрея.
         * Заводим ожидаемое Имя в переменную.
         * Проверяем соответствие ожидаемого и действительного
         * То же делаем для должности Андрея.
         * Найдем класс, в котором хранятся параграфы (<p>) с биографией Андрея, положим его в WebElement info_AndrewG_pt1
         * Создадим строковый массив, куда запишем ожидаемые результаты по описанию биографии Андрея Г. (длинные, да, а что делать...)
         * Считаем параграфы, где франится биография Андрея и положим её в List<WebElement> paragraphsOfAndrewBio
         * Сконвертируем вебдрайвер в JSвебдрайвер для того чтобы из параграфа вытащить innerText.
         * Пройдемся циклом по элементам paragraphsOfAndrewBio и сопоставим их с ожидаемым результатом.
         * */
        /*driver.get(ImPagesData.getProperty("whoWeAreIMPage"));
        WebElement name_AndrewG = driver.findElement(By.xpath("//*[@id=\"about_overview\"]/section[3]/div[1]/div/div[1]/div[5]/a/div/p[1]"));
        String expectedAndrewG_name = "Andrew Gachechiladze";
        Assert.assertEquals(expectedAndrewG_name, name_AndrewG.getText());
        WebElement duty_AndrewG = driver.findElement(By.xpath("//*[@id=\"about_overview\"]/section[3]/div[1]/div/div[1]/div[5]/a/div/p[2]"));
        String expectedAndrewG_duty = "EVP of Product Development and Engineering";
        Assert.assertEquals(expectedAndrewG_duty, duty_AndrewG.getText());*/
        //WebElement allBioOfAndrew = driver.findElement(By.xpath("//*[@id=\"leader_Andrew_Gachechiladze\"]/div[2]/div[2]")); // оно вообще зачем? мы это вообще юзаем?


        //WebElement andrewGphotoButton = driver.findElement(By.cssSelector("#about_overview > section.about-leadership.bg-white.section_p > div.leaders-list > div > div.lead-board > div:nth-child(5) > a > div"));
        //andrewGphotoButton.click();
        //WebElement paragraphsOfAndrewBio = driver.findElement(By.xpath("//*[@id=\"leader_Andrew_Gachechiladze\"]/div[2]/div[2]/p[4]"));
        //List<WebElement> paragraphsOfAndrewBio = allBioOfAndrew.findElements(By.tagName("p"));
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
        System.out.println("dataValidationSupportDataPosition - погнали");
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