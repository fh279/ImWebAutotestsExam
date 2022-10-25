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

    public WebDriver driver;
    public IntermediaMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //* определение локатора меню пользователя

    @FindBy(xpath = "//*[@id=\"primary-nav\"]/li[1]/a")
    private WebElement productsButton;

    @FindBy(xpath = "//*[@id=\"primary-nav\"]/li[1]/div/div/div/ul[1]/li[2]/a")
    private WebElement imUniteButton;



    // * метод для получения имени пользователя из меню пользователя



    // * метод для нажатия кнопки меню пользователя

    public void entryIMUniteProductsPage() {
        imUniteButton.click();
    }

    public void hoverIMPopUpMenu() {

        Actions action = new Actions(driver);
        action.moveToElement(productsButton).perform(); // let move cursor to "products" chapter
    }
}