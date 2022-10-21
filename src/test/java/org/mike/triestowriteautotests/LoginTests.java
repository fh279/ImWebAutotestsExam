package org.mike.triestowriteautotests;
//import org.checkerframework.checker.units.qual.C;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class LoginTests {
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
    }

    @Test
    public void priceTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Actions action = new Actions(driver);

        driver.get("https://www.intermedia.com/");
        Thread.sleep(2000);

        // Going to Products chapter (hover on it)
        WebElement productsButton = driver.findElement(By.xpath("//*[@id=\"primary-nav\"]/li[1]/a"));
        action.moveToElement(productsButton).perform(); // let move cursor to "products" chapter
        Thread.sleep(2000);

        // Going to "Intermedia Unite" button
        WebElement imUniteButton = driver.findElement(By.xpath("//*[@id=\"primary-nav\"]/li[1]/div/div/div/ul[1]/li[2]/a"));
        action.click(imUniteButton).perform();

        // 1. Validate price for UNITE PRO is $27.99, UNITE ENTERPRISE $32.99
        WebElement text_ProPlan = driver.findElement(By.xpath("//*[@id=\"product\"]/section[7]/div/div/div[1]/div[1]/h3"));
        WebElement proPrice = driver.findElement(By.xpath("//*[@id=\"product\"]/section[7]/div/div/div[1]/div[1]/h3/span"));
        Assert.assertEquals("$27.99", proPrice.getText());
        WebElement enterpricePrice = driver.findElement(By.xpath("//*[@id=\"product\"]/section[7]/div/div/div[2]/div[1]/h3/span"));
        Assert.assertEquals("$32.99", enterpricePrice.getText());
        // а вторую цену ты проверить не хочешь?
        driver.quit();
    }

    //Что_условия_результат()

    @Test
    public void answersFromSupportBot_NewSession_IsCustomerAnswerExist() throws InterruptedException {
        /*
         * Создаем экземпляр драйвера, присваиваиваем его переменной
         * выставляем настройку размера экрана - максимальный (полный)
         * Выставляем неявлую задержку выбрасывания исключений == 5 сек.
         * Заходим на страницу https://www.intermedia.com/products/unite
         * Нахожу iframe, где находится кнопка открытия чата (xpath вот: "//*[@id="drift-frame-controller"]/iframe" ).
         *   Сохраняем его в переменную chatBotFrame.
         * Переключаемся на frame chatBotFrame,
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
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.intermedia.com/products/unite");
        WebElement chatBotFrame = driver.findElement(By.xpath("//*[@id=\"drift-frame-controller\"]/iframe"));
        driver.switchTo().frame(chatBotFrame);
        WebElement chatButtonWithAshley = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div[3]/div[1]/div[1]/div/div"));
        chatButtonWithAshley.click();
        driver.switchTo().defaultContent();
        WebElement supportChatFrame = driver.findElement(By.xpath("//*[@id=\"drift-frame-chat\"]/iframe"));
        driver.switchTo().frame(supportChatFrame);
        WebElement imCurrentCustomerButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div[2]/div[2]/div/div[2]/div[1]/div/ul[2]/li[4]"));
        imCurrentCustomerButton.click();
        Assert.assertEquals("I'm a current customer/partner", imCurrentCustomerButton.getText());
    }

    @Test
    public void linksValidation_NewSession_YT_FB_Twitr_LinkdIn() {
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
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.get("https://www.intermedia.com/");
        // checking youtube link
        WebElement ytButton = driver.findElement(By.xpath("//*[@id=\"social\"]/div[2]/a[1]"));
        String ytActualURL = ytButton.getAttribute("href");
        Assert.assertEquals("https://www.youtube.com/channel/UCU-qlUSGWdMC95HVO5hHPrg", ytActualURL);
        // checking facebook link
        WebElement fbButton = driver.findElement(By.xpath("//*[@id=\"social\"]/div[2]/a[2]"));
        String fbActualURL = fbButton.getAttribute("href");
        Assert.assertEquals("https://www.facebook.com/intermedia.inc", fbActualURL);
        // checking twitter link
        WebElement twButton = driver.findElement(By.xpath("//*[@id=\"social\"]/div[2]/a[3]"));
        String twActualURL = twButton.getAttribute("href");
        Assert.assertEquals("https://twitter.com/intermedia_net", twActualURL);
        // checking linkedin link
        WebElement lInButton = driver.findElement(By.xpath("//*[@id=\"social\"]/div[2]/a[4]"));
        String lInActualURL = lInButton.getAttribute("href");
        Assert.assertEquals("https://www.linkedin.com/company/intermedia", lInActualURL);
    }

    @Test
    public void check_AndrewG_Position() {
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
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("https://www.intermedia.com/about-us/who-we-are");

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

        List<WebElement> paragraphsOfAndrewBio = allBioOfAndrew.findElements(By.tagName("p"));
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        for (int i = 0; i < paragraphsOfAndrewBio.size(); i++) {
            String actualText = jsDriver.executeScript("return arguments[0].innerText", paragraphsOfAndrewBio.get(i)).toString();
            Assert.assertEquals(expectedResultsAndrewBio[i], actualText);
        }
    }
/*Задача
Open:
https://www.intermedia.com/
1. Validate price for UNITE PRO is $27.99, UNITE ENTERPRISE $32.99 (Products → Intermedia Unite)
2. Check chatBot contains replay “I’m a current customer/partner”.
3. Validate links on social media(youtube, facebook, twitter, inst)
4. Vaildate that Andrew G has the proper position at About US page (Andrew Gachechiladze EVP of Product Development and Engineering).Validate that after click on the name, detailed information is displayed
5. Validate Contact US page contains proper details: Intermedia Support 800-379-7729 (edited) */

/*public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe"); //dfdf
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);*/
        /*//WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); // ход теста отображается в полностью открытом окне
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // если элемент не найден, то драйвер будет ждать его появления в течении заданного времени (10 секунд) и шагом в 500 мс.
        driver.get(ConfProperties.getProperty("loginpage"));//"https://passport.yandex.ru/auth"));//     // Передача урла драйверу
}

    @Test
    public void loginTest() throws InterruptedException {
        loginPage.inputLogin("FH279");     //(ConfProperties.getProperty("login"));
        loginPage.clickLoginBtn(); //нажимаем кнопку входа
        TimeUnit.SECONDS.sleep(2);
        loginPage.inputPasswd("Kalaus279n13");     //(ConfProperties.getProperty("password")); //вводим пароль
        loginPage.clickLoginBtn(); //нажимаем кнопку входа
        String user = profilePage.getUserName(); //получаем отображаемый логин
        Assert.assertEquals(ConfProperties.getProperty("login"), user); }         //и сравниваем его с логином из файла настроек


    @AfterClass
    public static void tearDown() {
        profilePage.entryMenu();
        profilePage.userLogout();
        driver.quit();
    }*/

}