import driver.DriverFactory;
import exceptions.DriverNotSupportedException;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;


import java.util.concurrent.TimeUnit;

public class PageAboutMe_Test {
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PageAboutMe_Test.class);
    private WebDriver driver;


    @BeforeEach
    public void setUp() throws DriverNotSupportedException {
        this.driver = new DriverFactory().getDriver();
        logger.info("Драйвер поднят");
    }

    @AfterEach
    public void startDown(){
        if(driver != null){driver.close();driver.quit();}
    }

    @Test
    public void otusTest(){
        MainPage mainPage = new MainPage(driver);
        driver.get(System.getProperty("base.url"));
        logger.info("Сайт открыт");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage.auth();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        enterToLK();
        fillAboutMe();
        openClearBrowser();
        mainPage.auth();
        enterToLK();
    }
    private void openClearBrowser(){
        driver.quit();
        driver = new ChromeDriver();
        driver.get("https://otus.ru");
    }


    private void enterToLK(){
        driver.get("https://otus.ru/lk/biography/personal/");
    }

    public void fillAboutMe(){
        enterToTextArea(driver.findElement(By.id("id_fname")), "Игорь");
        enterToTextArea(driver.findElement(By.id("id_fname_latin")), "Igor");
        enterToTextArea(driver.findElement(By.id("id_lname")), "Карданов");
        enterToTextArea(driver.findElement(By.id("id_lname_latin")), "Kardanov");
        enterToTextArea(driver.findElement(By.id("id_blog_name")), "Игорь");
        enterToTextArea(driver.findElement(By.xpath("//input[@name='date_of_birth']")), "06.06.1999");
        driver.findElement(By.cssSelector("input[name='country']")).click();
        driver.findElement(By.cssSelector("button[title='Россия']")).click();
        driver.findElement(By.cssSelector("input[name='city']")).click();
        driver.findElement(By.cssSelector("button[title='Россия']")).click();
        driver.findElement(By.className(".js-change-phone")).click();
        driver.findElement(By.xpath("//div[@class = 'lk-cv-action-buttons']//button[@title = 'Сохранить и продолжить']")).submit();
    }
    public void enterToTextArea(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }


}
