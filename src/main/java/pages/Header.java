package pages;


import org.openqa.selenium.WebDriver;
import pageObject.AbsPageObject;

public class Header  extends AbsPageObject {
    public Header(WebDriver driver) {
        super(driver);
    }

    public LoginPage sinInClick() {
        clickElementById("button[data-modal-id='new-log-reg']");
        return new LoginPage(driver);
    }
    public void profile(){
        clickElementByCss("div.header2-menu__item.header2__right__menu__item.header2-menu__item_small.header2-menu__item_dropdown.header2-menu__item_dropdown_no-border");
    }

    public PageAboutMe openProfile(){
        profile();
        clickElementByCss("a[href='/lk/biography/personal/'] > div > b");
        return new PageAboutMe(driver);
    }
}
