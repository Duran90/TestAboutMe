package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class AbsPageObject {

    protected WebDriver driver;
    protected Actions actions;

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);

        PageFactory.initElements(driver, this);
    }

    public void clickElementByCss(String cssParam){
        driver.findElement(By.cssSelector(cssParam)).click();
    }
    public void clickElementById(String idParam){
        driver.findElement(By.cssSelector(idParam)).click();
    }
    public void clickElementByXpath(String xpathParam){
        driver.findElement(By.xpath(xpathParam)).click();
    }
    public List<WebElement> findAllElem(String xpath){
        return driver.findElements(By.xpath(xpath));
    }

}