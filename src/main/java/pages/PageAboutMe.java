package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PageAboutMe extends AbsBasePage {

    private static final String PATH = "/lk/biography/personal/";

    public PageAboutMe(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPath() {
        return PATH;
    }



    public void personalInfo(String fName, String fname_Latin, String lname, String lname_latin, String blogName, String birthday) {
        enterToTextArea(driver.findElement(By.id("id_fname")), fName);
        enterToTextArea(driver.findElement(By.id("id_fname_latin")), fname_Latin);
        enterToTextArea(driver.findElement(By.id("id_lname")), lname);
        enterToTextArea(driver.findElement(By.id("id_lname_latin")), lname_latin);
        enterToTextArea(driver.findElement(By.id("id_blog_name")), blogName);
        enterToTextArea(driver.findElement(By.xpath("//input[@name='date_of_birth']")), birthday);
    }

    public void userLocation(String country,String city) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(By.cssSelector("div:nth-child(1) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div")).click();
        driver.findElement(By.cssSelector(String.format("button[title='%s']", country))).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement citys = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(2) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div")));
        citys.click();
        WebElement arrCity = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(String.format("button[title='%s']",city))));
        arrCity.click();
    }

    public void languageLevel(String langLevelVal) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement langLevel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div")));
        langLevel.click();
        driver.findElement(By.cssSelector(String.format("button[title='%s']", langLevelVal))).click();
    }

    public void readyToMove() {
        driver.findElement(By.cssSelector(" div.container__col.container__col_9.container__col_md-8.container__col_middle > label:nth-child(1)")).click();
    }

    public void workFormat() {
        driver.findElement(By.cssSelector("div.container__col.container__col_9.container__col_md-8.container__col_middle > div:nth-child(1) > div:nth-child(2) > label")).click();
    }

    public void addContacts(String contactType, String contactText){
        List<WebElement> contactElements = findAllElem("//div[contains(@class, 'custom-select')]/../input[contains(@id, 'id_contact')]");
        String inputText = contactElements.get(contactElements.size()-1).getAttribute("value");
        if(!inputText.isEmpty()){
            clickElementByXpath("//button[.='Добавить']");
            contactElements = findAllElem("//div[contains(@class, 'custom-select')]/../input[contains(@id, 'id_contact')]");
        }
        clickElementByXpath("//span[.='Способ связи']");
        clickElementByXpath(String.format("button[title='%s']",contactType));
        int intElem = contactElements.size();
        contactElements.get(intElem -1).click();
        contactElements.get(intElem -1).sendKeys(contactText);
    }

    public void enterToTextArea(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }


}
