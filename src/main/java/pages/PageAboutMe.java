package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PageAboutMe extends AbsBasePage {

    private static final String path = "/lk/biography/personal/";

    public PageAboutMe(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open(String path) {
        super.open(PageAboutMe.path);
    }

    public void enterToLK() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://otus.ru/lk/biography/personal/");
    }

    public void personalInfo(String fName, String fname_Latin, String lname, String lname_latin, String blogName, String birthday) {
        enterToTextArea(driver.findElement(By.id("id_fname")), fName);
        enterToTextArea(driver.findElement(By.id("id_fname_latin")), fname_Latin);
        enterToTextArea(driver.findElement(By.id("id_lname")), lname);
        enterToTextArea(driver.findElement(By.id("id_lname_latin")), lname_latin);
        enterToTextArea(driver.findElement(By.id("id_blog_name")), blogName);
        enterToTextArea(driver.findElement(By.xpath("//input[@name='date_of_birth']")), birthday);
    }

    public void userLocation(String country) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(By.cssSelector("div:nth-child(1) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div")).click();
        driver.findElement(By.cssSelector(String.format("button[title='%s']", country))).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement city = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(2) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div")));
        city.click();
        WebElement arrCity = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[title='Москва']")));
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

    public void addSkypeContacts(String skype) {
            driver.findElement(By.cssSelector(".placeholder")).click();
            driver.findElement(By.cssSelector("button[@data-value='skype']")).click();
            driver.findElement(By.id("id_contact-0-value")).sendKeys(skype);
    }
    public void addViberContacts(String viber){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".placeholder")));
        driver.findElement(By.cssSelector("")).click();

        driver.findElement(By.cssSelector("input[name='contact-1-service']")).click();
        driver.findElement(By.cssSelector("button[title='Viber']")).click();
        driver.findElement(By.id("id_contact-1-value")).sendKeys(viber);
    }

    public void enterToTextArea(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }


}
