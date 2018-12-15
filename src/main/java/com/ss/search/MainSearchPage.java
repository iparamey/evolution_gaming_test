package com.ss.search;


import com.ss.basepage.SearchPage;
import com.ss.basepage.Selenium;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainSearchPage extends SearchPage {

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

    @Override
    public String getDescriptionByAdNumber(int adNumber) {
        return getElementByXpath(String.format("//table[2]/tbody/tr[%d]/td[3]/div/a/b", (adNumber + 1))).getText().substring(0, 30);
    }

    public void filterGameEvAds() {
        selectJobAndBusinessSection();
        setSearchText("Evolution Gaming");
    }
}
