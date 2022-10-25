package org.mike.triestowriteautotests;
//import org.checkerframework.checker.units.qual.C;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class IntermediaTests {
    public static WebDriver driver;
    public static IntermediaMainPage mainPage;
    public static IntermediaUniteProductsPage uniteProductsPage;
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
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ImPagesData.getProperty("mainIMPage"));
    }

    @Test
    public void intermediaPricesTest() throws InterruptedException {
        mainPage.hoverIMPopUpMenu();
        mainPage.entryIMUniteProductsPage();
        Assert.assertEquals(ImPagesData.getProperty("proPlanText"), uniteProductsPage.getProPlanText());
        Assert.assertEquals(ImPagesData.getProperty("enterprisePlanText"), uniteProductsPage.getEnterprisePlanText());
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
        mainPage.hoverIMPopUpMenu();
        mainPage.entryIMUniteProductsPage();
        uniteProductsPage.clickOnSupportChatButton();
        uniteProductsPage.checkIsUserACustomer();
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
        mainPage.checkYouTubeLink();
        mainPage.checkFaceBookLink();
        mainPage.checkTwitterLink();
        mainPage.checkLinkedInLink();
    }

    @Test
    public void dataValidation_AndrewG_Position() {
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
        driver.get(ImPagesData.getProperty("whoWeAreIMPage"));
        WebElement name_AndrewG = driver.findElement(By.xpath("//*[@id=\"about_overview\"]/section[3]/div[1]/div/div[1]/div[5]/a/div/p[1]"));
        String expectedAndrewG_name = "Andrew Gachechiladze";
        Assert.assertEquals(expectedAndrewG_name, name_AndrewG.getText());
        WebElement duty_AndrewG = driver.findElement(By.xpath("//*[@id=\"about_overview\"]/section[3]/div[1]/div/div[1]/div[5]/a/div/p[2]"));
        String expectedAndrewG_duty = "EVP of Product Development and Engineering";
        Assert.assertEquals(expectedAndrewG_duty, duty_AndrewG.getText());

        WebElement allBioOfAndrew = driver.findElement(By.xpath("//*[@id=\"leader_Andrew_Gachechiladze\"]/div[2]/div[2]"));
        String[] expectedResultsAndrewBio =
                {"Andrew Gachechiladze leads Intermedia’s product development and engineering groups, overseeing all software development for Intermedia’s cloud platform.",
                        "With the company since 2001, Gachechiladze has set the foundation for Intermedia’s cloud management and partner portal platforms. He’s been directly involved in scaling the broad suite of integrated cloud applications Intermedia offers today, including third-party products such as Microsoft® Office 365® and proprietary offerings like unified communications, SecuriSync all-in-one backup and file sharing, and our security services portfolio. Further strengthening Intermedia’s solution portfolio, Gachechiladze has also played an integral role in two successful acquisitions.",
                        "Under Gachechiladze’s leadership, the development team has grown from a group of less than 10 individuals to 100+; from a single office to four different development centers using multiple technologies, including Mountain View, CA, Bellevue, WA, St. Petersburg, Russia and Bristol, UK.",
                        "During his tenure with the company, Gachechiladze has previously been a senior software developer and a product manager for cloud-based Exchange. Prior to Intermedia, Gachechiladze has held technical and software development roles with a range of companies, including ThinkWave, a cloud-based school administration system. Gachechiladze has a Masters degree in Physics and Computer Science from Tbilisi State University."
                };

        WebElement andrewGphotoButton = driver.findElement(By.cssSelector("#about_overview > section.about-leadership.bg-white.section_p > div.leaders-list > div > div.lead-board > div:nth-child(5) > a > div"));
        andrewGphotoButton.click();
        WebElement paragraphsOfAndrewBio = driver.findElement(By.xpath("//*[@id=\"leader_Andrew_Gachechiladze\"]/div[2]/div[2]/p[4]"));
        System.out.println(paragraphsOfAndrewBio.getText());
    }

    @Test
    public void dataValidation_SupportData_Position() {
        /* Создаем драйвер
         *  браузер в полноэкранный режим
         *  Неявное ожидание 1 секунда
         *  Ищем элемент с номером телефона, xPath вот:
         *       "//*[@id="support"]/section[5]/div/div/div[2]/div[2]/p[2]/span[2]/a"
         *       пихаем его в WebElement supportCallNumber
         *  Пишем сверку найденного в supportCallNumber текста и номера тех поддержки: 800-379-7729
         * */
        driver.get(ImPagesData.getProperty("supportIMPage"));
        WebElement supportCallNumber = driver.findElement(By.xpath("//*[@id=\"support\"]/section[5]/div/div/div[2]/div[2]/p[2]/span[2]/a"));
        Assert.assertEquals("800-379-7729", supportCallNumber.getText());
    }
}