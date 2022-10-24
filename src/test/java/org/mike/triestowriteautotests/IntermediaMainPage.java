package org.mike.triestowriteautotests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IntermediaMainPage {

   //  * конструктор класса, занимающийся инициализацией полей класса


    public WebDriver driver;
    public IntermediaMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

     //* определение локатора меню пользователя

    //WebElement productsButton = driver.findElement(By.xpath("//*[@id=\"primary-nav\"]/li[1]/a"));
    @FindBy(xpath = "\"//*[@id=\\\"primary-nav\\\"]/li[1]/a\"")
    private WebElement productsButton;
    // WebElement imUniteButton = driver.findElement(By.xpath("//*[@id=\"primary-nav\"]/li[1]/div/div/div/ul[1]/li[2]/a"));
    @FindBy(xpath = "//*[@id=\\\"primary-nav\\\"]/li[1]/div/div/div/ul[1]/li[2]/a")
    private WebElement imUniteButton;

    @FindBy(xpath = "//*[@id=\"product\"]/section[7]/div/div/div[1]/div[1]/h3")
    private WebElement proPlanArea;
    @FindBy(xpath = "//*[@id=\"product\"]/section[7]/div/div/div[2]/div[1]/h3")
    private WebElement enterprisePlanArea;

    // * метод для получения имени пользователя из меню пользователя

    public String getProPlanText() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/form/div[1]/a")));
        String proPlanText = proPlanArea.getText();
        return proPlanText;
    }

    public String getEnterprisePlanText() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/form/div[1]/a")));
        String enterprisePlanText = enterprisePlanArea.getText();
        return enterprisePlanText;
    }

    // * метод для нажатия кнопки меню пользователя

    public void entryIMUniteProductsPage() {
        imUniteButton.click(); }

    public void hoverIMPopUpMenu(){
        Actions action = new Actions(driver);
        action.moveToElement(productsButton).perform(); // let move cursor to "products" chapter
    }

    // * метод для нажатия кнопки выхода из аккаунта

    /*public void userLogout() {
        logoutBtn.click(); }*/
    //
}
