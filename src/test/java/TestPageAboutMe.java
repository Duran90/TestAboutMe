import driver.DriverFactory;
import exceptions.DriverNotSupportedException;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.PageAboutMe;

import java.util.concurrent.TimeUnit;

public class TestPageAboutMe {
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TestPageAboutMe.class);
    private WebDriver driver;

    private PageAboutMe pageAboutMe;

    String fname = "Игорь";
    String fnamelatin = "Карданов";
    String lname = "Igor";
    String lname_latin = "Kardanov";
    String blogName = "Игорь";
    String birthday = "06.06.1999";
    String country = "Россия";
    String city = "Москва";
    String langLevelVal = "Элементарный уровень (Elementary)";
    String firsContact = "Skype";
    String inputTextFirsContact = "Тестовый скайп";
    String secondContact = "Viber";
    String inputTextSecondContact = "Тестовый вайбер";


    @BeforeEach
    public void setUp() throws DriverNotSupportedException {
        this.driver = new DriverFactory().getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
    }


    @AfterEach
    public void startDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void otusTest() throws DriverNotSupportedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        logger.info("Сайт открыт");
        LoginPage lp = mainPage.getHeader().sinInClick();
        lp.signIn(System.getProperty("email"), System.getProperty("password"));
        fillAboutMe(mainPage.getHeader().openProfile());

        openClearBrowser();

        mainPage = new MainPage(driver);
        mainPage.open();
        logger.info("Сайт открыт");
        lp = mainPage.getHeader().sinInClick();
        lp.signIn(System.getProperty("email"), System.getProperty("password"));
        pageAboutMe = mainPage.getHeader().openProfile();
        logger.info("Начало проверок");
        asserPersonalIfo(fname, fnamelatin, lname, lname_latin, blogName, birthday);
        assertUserLocation(country, city);
        assertLang(langLevelVal);
        assertSkypeContact(inputTextFirsContact);
        assertViberContact(inputTextSecondContact);
        logger.info("Проверки завершены");
    }

    private void openClearBrowser() throws DriverNotSupportedException {
        startDown();
        setUp();
    }


    private void fillAboutMe(PageAboutMe pageAboutMe) {
        pageAboutMe.personalInfo(fname, fnamelatin, lname, lname_latin, blogName, birthday);
        pageAboutMe.userCountry(country);
        pageAboutMe.userCity(city);
        pageAboutMe.languageLevel(langLevelVal);
        pageAboutMe.addContact(firsContact, inputTextFirsContact);
        pageAboutMe.addContact(secondContact, inputTextSecondContact);
        pageAboutMe.submit();
    }

    private void asserPersonalIfo(String fname, String fnamelatin, String lname, String lname_latin, String blogName, String birthday) {
        String name = pageAboutMe.getNameText();
        Assertions.assertEquals(name, fname);
        String latinName = pageAboutMe.getNameLatinText();
        Assertions.assertEquals(fnamelatin, latinName);
        String lastName = pageAboutMe.getLastNameText();
        Assertions.assertEquals(lname, lastName);
        String latinLastName = pageAboutMe.getLastNameLatinText();
        Assertions.assertEquals(lname_latin, latinLastName);
        String bName = pageAboutMe.getBlogNameText();
        Assertions.assertEquals(blogName, bName);
        String date = pageAboutMe.getDayText();
        Assertions.assertEquals(birthday, date);
    }

    private void assertUserLocation(String country, String city) {
        String userCountry = pageAboutMe.getCountryText();
        Assertions.assertEquals(country, userCountry);
        String userCity = pageAboutMe.getCityText();
        Assertions.assertEquals(city, userCity);
    }

    private void assertLang(String langLevelVal) {
        String langLevel = pageAboutMe.getLangLvlText();
        Assertions.assertEquals(langLevelVal, langLevel);
    }


    public void assertSkypeContact(String value) {
        String valueType = pageAboutMe.getContactTypeText(0);
        Assertions.assertEquals(firsContact.toLowerCase(), valueType);
        String valueSkypeText = pageAboutMe.getContactValueText(0);
        Assertions.assertEquals(value, valueSkypeText);
    }

    public void assertViberContact(String value) {
        String valueType = pageAboutMe.getContactTypeText(1);
        Assertions.assertEquals(secondContact.toLowerCase(), valueType);
        String valueText = pageAboutMe.getContactValueText(1);
        Assertions.assertEquals(value, valueText);
    }

}
