package com.ss.search;


import com.ss.basepage.SearchModule;
import com.ss.basepage.Selenium;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainSearchPage extends SearchModule {

    public MainSearchPage(Selenium selenium) {
        super(selenium);
    }

    @FindBy(className = "in1")
    WebElement getSearchInput;

    @FindBy(xpath = "//select[@name='cid_0']")
    WebElement getSectionSelection;

    @FindBy(xpath = "//option[contains(text(), 'Job and business')]")
    WebElement getJobAndBusinessOption;

    private void setSearchText(String text) {
        getSearchInput.sendKeys(text + Keys.ENTER);
    }

    private void selectJobAndBusinessSection() {
        getSectionSelection.click();
        getJobAndBusinessOption.click();
    }

    public void filterGameEvAds() {
        selectJobAndBusinessSection();
        setSearchText("Evolution Gaming");
    }
}
