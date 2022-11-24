package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageAboutMe extends AbsBasePage {
    private static final String nameTb = "id_fname";
    private static final String nameLatinTb = "id_fname_latin";
    private static final String lastNameTb = "id_lname";
    private static final String lastNameLatinTb = "id_lname_latin";
    private static final String blogDateTb = "id_blog_name";
    private static final String dateTb = "//input[@name='date_of_birth']";
    private static final String countryTb = "//p[contains(text(),'Страна')]/../following-sibling::div/div";
    private static final String countryName = "//button[@title='%s']";
    private static final String cityTb = "//p[contains(text(),'Город')]/../following-sibling::div/div";
    private static final String cityName = "//*[contains(text(), '%s')]"; //"//*[contains(text(), '" + selectedCity + "')]"
    private static final String langTb = "//p[contains(text(),'Уровень')]/../following-sibling::div/div";
    private static final String langLvl = "//div[contains(@class, 'scroll  js-custom-select-options')]/button[contains(@title, '%s')]";
    private static final String contactType = "//button[@title='%s']";
    private static final String contactInput = "//div[contains(@class, 'custom-select')]/../input[contains(@id, 'id_contact')]";
    private static final String contactTypes = "//div[contains(@data-selected-option-class, 'option_selected')]//input[contains(@name, 'contact')]";
    private static final String contactValues = "//div[contains(@class, 'container')]/input[contains(@id, 'id_contact') and @type='text']";
    private static final String addBtn = "//button[.='Добавить']";
    private static final String contactSb = "//div[contains(@class, 'md-4')]/label/..";
    public static final String submitBnt = "//div[@class = 'lk-cv-action-buttons']//button[@title = 'Сохранить и продолжить']";

    public PageAboutMe(WebDriver driver) {
        super(driver);
    }

    public void personalInfo(String fName, String fname_Latin, String lname, String lname_latin, String blogName, String birthday) {
        enterToTextArea(driver.findElement(By.id(nameTb)), fName);
        enterToTextArea(driver.findElement(By.id(nameLatinTb)), fname_Latin);
        enterToTextArea(driver.findElement(By.id(lastNameTb)), lname);
        enterToTextArea(driver.findElement(By.id(lastNameLatinTb)), lname_latin);
        enterToTextArea(driver.findElement(By.id(blogDateTb)), blogName);
        enterToTextArea(driver.findElement(By.xpath(dateTb)), birthday);
    }

    public void languageLevel(String langLevelVal) {
        clickElementByXpath(langTb);
        clickElementByXpath(String.format(langLvl, langLevelVal));
    }

    public void addContact(String option, String text) {
        List<WebElement> contactElements = driver.findElements(By.xpath(contactInput));
        String textInput = contactElements.get(contactElements.size() - 1).getAttribute("value"); //текст последнего контакта
        if (!textInput.isEmpty()) {
            clickElementByXpath(addBtn);
            contactElements = driver.findElements(By.xpath(contactInput));
        }

        List<WebElement> elems = driver.findElements(By.xpath(contactSb));
        int numberOfContacts = elems.size();
        elems.get(numberOfContacts - 1).click();
        List<WebElement> elemsType = driver.findElements(By.xpath(String.format(contactType, option)));
        elemsType.get(numberOfContacts - 1).click();
        contactElements.get(numberOfContacts - 1).sendKeys(text);
    }

    public void enterToTextArea(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void submit() {
        clickElementByXpath(submitBnt);
    }

    public void userCountry(String country) {
        if(driver.findElement(By.xpath(countryTb)).getText().contains(country)) return;
        clickElementByXpath(countryTb);
        clickElementByXpath(String.format(countryName, country));
    }

    public void userCity(String selectedCity) {
        if(driver.findElement(By.xpath(cityTb)).getText().contains(selectedCity)) return;
        driver.findElement(By.xpath(cityTb)).click();
        driver.findElement(By.xpath(String.format(cityName, selectedCity))).click();

    }

    public String getCountryText() {
        return driver.findElement(By.xpath(countryTb)).getText();
    }

    public String getCityText() {
        return driver.findElement(By.xpath(cityTb)).getText();
    }

    public String getNameText() {
        return driver.findElement(By.id(nameTb)).getAttribute("value");
    }

    public String getNameLatinText() {
        return driver.findElement(By.id(nameLatinTb)).getAttribute("value");
    }

    public String getLastNameText() {
        return driver.findElement(By.id(lastNameTb)).getAttribute("value");
    }

    public String getLastNameLatinText() {
        return driver.findElement(By.id(lastNameLatinTb)).getAttribute("value");
    }

    public String getBlogNameText() {
        return driver.findElement(By.id(blogDateTb)).getAttribute("value");
    }

    public String getDayText() {
        return driver.findElement(By.xpath(dateTb)).getAttribute("value");
    }

    public String getLangLvlText() {
        return driver.findElement(By.xpath(langTb)).getText();
    }

    public String getContactTypeText(int num) {
        return driver.findElements(By.xpath(contactTypes)).get(num).getAttribute("value");
    }

    public String getContactValueText(int num) {
        return driver.findElements(By.xpath(contactValues)).get(num).getAttribute("value");
    }

}
