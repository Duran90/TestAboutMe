package pages;

import org.openqa.selenium.WebDriver;
import pageObject.AbsPageObject;

public abstract class AbsBasePage extends AbsPageObject {

    public final static String BASE_URL = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    public void open(String path) {
        driver.get(BASE_URL + path);
    }
}
