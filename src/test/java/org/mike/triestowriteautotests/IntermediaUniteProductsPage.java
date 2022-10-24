package org.mike.triestowriteautotests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IntermediaUniteProductsPage {

    public WebDriver driver;
    public IntermediaUniteProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }


    //WebElement textProPlan = driver.findElement(By.xpath("//*[@id=\"product\"]/section[7]/div/div/div[1]/div[1]/h3"));
    //WebElement textEnterprisePlan = driver.findElement(By.xpath("//*[@id=\"product\"]/section[7]/div/div/div[2]/div[1]/h3"));

    @FindBy(xpath = "//*[@id=\"product\"]/section[7]/div/div/div[1]/div[1]/h3")
    private WebElement textProPlan;

    @FindBy(xpath = "//*[@id=\"product\"]/section[7]/div/div/div[2]/div[1]/h3")
    private WebElement textEnterprisePlan;


    /*А теперь напишем методы для взаимодействия с элементами.


Метод ввода логина:


public void inputLogin(String login) {
        loginField.sendKeys(login); }

Метод ввода пароля:


public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd); }

Метод нажатия кнопки входа:


public void clickLoginBtn() {
        loginBtn.click(); }*/

}
