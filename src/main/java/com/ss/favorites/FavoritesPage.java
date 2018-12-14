package com.ss.favorites;

import com.ss.announcement.Announcement;
import com.ss.announcement.Favorites;
import com.ss.basepage.BasePage;
import com.ss.basepage.Selenium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class FavoritesPage extends BasePage {

    public FavoritesPage(Selenium selenium) {
        super(selenium);
    }

    private ArrayList<String> getAdsDescriptionsList() {
        return getElementsByCss(".msg2").stream().map(element -> element.getText().substring(0, 30)).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean checkAdsByPrice(Favorites favorites) {
        ArrayList<String> favoritesList = favorites.stream().map(Announcement::getDescription).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<String> priceList = getAdsDescriptionsList();
        Collections.sort(favoritesList);
        Collections.sort(priceList);
        return favoritesList.equals(priceList);
    }
}
