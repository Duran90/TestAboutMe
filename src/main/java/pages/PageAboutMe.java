package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

public class PageAboutMe extends AbsBasePage {

    private static final String path = "/lk/biography/personal/";

    public PageAboutMe(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open(String path) {
        super.open(PageAboutMe.path);
    }
}
