import driver.DriverFactory;
import exceptions.DriverNotSupportedException;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.PageAboutMe;


import java.util.concurrent.TimeUnit;

public class TestPageAboutMe {
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TestPageAboutMe.class);
    private WebDriver driver;

    String fname = "Игорь";
    String fnamelatin = "Карданов";
    String lname = "Igor";
    String lname_latin = "Kardanov";
    String blogName = "Игорь";
    String birthday = "06.06.1999";
    String country = "Россия";
    String city = "Москва";
    String langLevelVal = "Элементарный уровень (Elementary)";
    String skype = "Тестовый скайп";
    String viber = "Тестовый вайбер";


    @BeforeEach
    public void setUp() throws DriverNotSupportedException {
        this.driver = new DriverFactory().getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
    }


    @AfterEach
   public void startDown(){
        if(driver != null){driver.close();driver.quit();}
    }

    @Test
    public void otusTest(){
        MainPage mainPage = new MainPage(driver);
        PageAboutMe pageAboutMe = new PageAboutMe(driver);
        driver.get(System.getProperty("base.url"));
        logger.info("Сайт открыт");
        mainPage.auth();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pageAboutMe.enterToLK();
        fillAboutMe();
        openClearBrowser();
        mainPage.auth();
        pageAboutMe.enterToLK();
        asserPersonalIfo(fname,fnamelatin,lname,lname_latin,blogName,birthday);
        assertUserLocation(country,city);
        assertLang(langLevelVal);
        assertReadyToMove();
        assertWorkFormat();
        assertSkypeContact(skype);
        assertViberContact(viber);
    }
    private void openClearBrowser(){
        driver.quit();
        driver = new ChromeDriver();
        driver.get("https://otus.ru");
    }



    public void fillAboutMe(){
        PageAboutMe pageAboutMe = new PageAboutMe(driver);
        pageAboutMe.personalInfo(fname,fnamelatin,lname,lname_latin,blogName,birthday);
        pageAboutMe.userLocation(country);
        pageAboutMe.languageLevel(langLevelVal);
        pageAboutMe.readyToMove();
        pageAboutMe.workFormat();
        pageAboutMe.addSkypeContacts(skype);
        pageAboutMe.addViberContacts(viber);
        driver.findElement(By.xpath("//div[@class = 'lk-cv-action-buttons']//button[@title = 'Сохранить и продолжить']")).submit();
    }

    public void asserPersonalIfo(String fname, String fnamelatin, String lname, String lname_latin, String blogName,String birthday){
        String name = driver.findElement(By.id("id_fname")).getText();
        Assertions.assertEquals(name, fname);
        String latinName = driver.findElement(By.id("id_fname_latin")).getText();
        Assertions.assertEquals(fnamelatin,latinName);
        String lastName = driver.findElement(By.id("id_lname")).getText();
        Assertions.assertEquals( lname, lastName);
        String latinLastName = driver.findElement(By.id("id_lname_latin")).getText();
        Assertions.assertEquals( lname_latin, latinLastName);
        String bName = driver.findElement(By.id("id_blog_name")).getText();
        Assertions.assertEquals(blogName,bName);
        String date = driver.findElement(By.xpath("//input[@name='date_of_birth']")).getText();
        Assertions.assertEquals(birthday,date);
    }

    public void assertUserLocation(String country, String city){
       String userCountry =  driver.findElement(By.cssSelector("div:nth-child(1) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div")).getText();
       Assertions.assertEquals( country,userCountry);
       String userCity = driver.findElement(By.cssSelector("div:nth-child(2) > div.container__col.container__col_9.container__col_md-8.container__col_middle > div > label > div")).getText();
        Assertions.assertEquals( city,userCity);
    }

    public void assertLang(String langLevelVal){
        String langLevel = driver.findElement(By.cssSelector("div.container__col.container__col_9.container__col_ssm-12 > ")).getText();
        Assertions.assertEquals(langLevelVal,langLevel);
    }
    public void assertReadyToMove(){
        String valueNo = driver.findElement(By.id("id_ready_to_relocate_0")).getAttribute("checked");
        String valueYes = driver.findElement(By.id("id_ready_to_relocate_1")).getAttribute("checked");
        Assertions.assertNull(valueNo);
        Assertions.assertNotNull(valueYes);

    }

    public void assertWorkFormat(){
        String fullDay = driver.findElement(By.cssSelector("//input[value='full']")).getAttribute("checked");
        Assertions.assertNotNull(fullDay);
    }

    public void assertSkypeContact(String skype){
        String valueSkype = driver.findElement(By.id("id_contact-0-value")).getText();
        Assertions.assertEquals(skype,valueSkype);
    }
    public void assertViberContact(String viber){
        String valueViber = driver.findElement(By.id("id_contact-1-value")).getText();
        Assertions.assertEquals(viber,valueViber);
    }

}
