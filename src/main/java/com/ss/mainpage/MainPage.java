package com.ss.mainpage;

import com.ss.basepage.BasePage;
import com.ss.basepage.Selenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    final String mainPageUrl = "https://www.ss.com/en";

    public MainPage(Selenium selenium) {
        super(selenium);
    }

    @FindBy(xpath = "//a[@title='Cars, Announcements']")
    WebElement getCarsCategory;

    public void openSite() {
        open(mainPageUrl);
    }

    public void openCarsCategory() {
        getCarsCategory.click();
    }

}
