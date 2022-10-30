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

    public void auth(){
        driver.findElement(By.cssSelector(".header2__auth")).click();
        WebElement form = driver.findElement(By.xpath("//form[@action = '/login/']"));
        WebElement inputEmail = form.findElement(By.xpath(".//input[@name = 'email']"));
        WebElement inputPassword = form.findElement(By.xpath(".//input[@name = 'password']"));
        WebElement submit = form.findElement(By.xpath(".//button[@type = 'submit']"));
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        System.out.println(System.getProperty("email") + " email");
        inputEmail.sendKeys(System.getProperty("email"));
        inputPassword.sendKeys(System.getProperty("password"));
        submit.submit();

    }
}
