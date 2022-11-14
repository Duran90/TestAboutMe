package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPage extends  AbsBasePage{
//
//    private String email = System.getProperty("email");
//    private String password =System.getProperty("password");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPath() {
        return "";
    }

}
