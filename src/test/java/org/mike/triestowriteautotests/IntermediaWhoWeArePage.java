package org.mike.triestowriteautotests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class IntermediaWhoWeArePage {
    public WebDriver driver;
    String[] expectedResultsAndrewGDetailedInfo =
            {
                    "Andrew Gachechiladze leads Intermedia’s product development and engineering groups, overseeing all software development for Intermedia’s cloud platform.",
                    "With the company since 2001, Gachechiladze has set the foundation for Intermedia’s cloud management and partner portal platforms. He’s been directly involved in scaling the broad suite of integrated cloud applications Intermedia offers today, including third-party products such as Microsoft® Office 365® and proprietary offerings like unified communications, SecuriSync all-in-one backup and file sharing, and our security services portfolio. Further strengthening Intermedia’s solution portfolio, Gachechiladze has also played an integral role in two successful acquisitions.",
                    "Under Gachechiladze’s leadership, the development team has grown from a group of less than 10 individuals to 100+; from a single office to four different development centers using multiple technologies, including Mountain View, CA, Bellevue, WA, St. Petersburg, Russia and Bristol, UK.",
                    "During his tenure with the company, Gachechiladze has previously been a senior software developer and a product manager for cloud-based Exchange. Prior to Intermedia, Gachechiladze has held technical and software development roles with a range of companies, including ThinkWave, a cloud-based school administration system. Gachechiladze has a Masters degree in Physics and Computer Science from Tbilisi State University."
            };

    public IntermediaWhoWeArePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "#about_overview > section.about-leadership.bg-white.section_p > div.leaders-list > div > div.lead-board > div:nth-child(5) > a > div")
    private WebElement buttonAndrewGPhoto;

    @FindBy(xpath = "//*[@id=\"about_overview\"]/section[3]/div[1]/div/div[1]/div[5]/a/div/p[1]")
    private WebElement nameAndrewG;

    @FindBy(xpath = "//*[@id=\"about_overview\"]/section[3]/div[1]/div/div[1]/div[5]/a/div/p[2]")
    private WebElement dutyAndrewG;

    @FindBy(xpath = "//*[@id=\"leader_Andrew_Gachechiladze\"]/div[2]/div[2]")
    private WebElement allBioOfAndrew;

    public String getAndrewGName() {
        return nameAndrewG.getText();
    }

    public WebElement getButtonAndrewGPhoto() {
        return buttonAndrewGPhoto;
    }

    public String[] getExpectedResultsAndrewGDetailedInfo() {
        return expectedResultsAndrewGDetailedInfo;
    }

    public List<WebElement> getAllAndrewGBio() {
        return allBioOfAndrew.findElements(By.tagName("p"));

    }
}