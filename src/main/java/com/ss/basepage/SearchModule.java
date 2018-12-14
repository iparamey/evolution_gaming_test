package com.ss.basepage;

import com.ss.announcement.Announcement;
import com.ss.announcement.Favorites;
import org.openqa.selenium.WebElement;

public class SearchModule extends BasePage {

    public SearchModule(Selenium selenium) {
        super(selenium);
    }

    private WebElement getAdCheckboxByNumber(int adNumber) {
        return getElementByXpath(String.format("//table[2]/tbody/tr[%d]/td[1]/input", (adNumber + 1)));
    }

    private String getDescriptionByAdNumber(int adNumber) {
        return getElementByXpath(String.format("//table[2]/tbody/tr[%d]/td[3]", (adNumber + 1))).getText().substring(0, 30);
    }

    public Announcement getAdByNumber(int adNumber) {
        return new Announcement(getDescriptionByAdNumber(adNumber));
    }

    public void selectFilteredAds(Favorites favorites) {
        int counter = 1;
        for (WebElement element : getElementsByXpath("//*[@name='mid[]']")) {
            element.click();
            favorites.add(favorites.size(), getAdByNumber(counter));
            counter++;
        }
    }

    public void selectFirstAd(Favorites favorites) {
        getAdCheckboxByNumber(1).click();
        favorites.add(favorites.size(), getAdByNumber(1));
    }
}
