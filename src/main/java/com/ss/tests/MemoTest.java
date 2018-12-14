package com.ss.tests;


import com.ss.announcement.Favorites;
import com.ss.basepage.Selenium;
import com.ss.categories.transport.cars.CarsPage;
import com.ss.categories.transport.cars.CarsSearchResultPage;
import com.ss.favorites.FavoritesPage;
import com.ss.mainpage.MainPage;
import com.ss.search.SearchPage;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemoTest {

    static WebDriver driver;
    int favoritesCounter = 0;
    static Favorites favorites;

    MainPage mainPage;
    CarsPage carsPage;
    CarsSearchResultPage carsSearchResultPage;
    FavoritesPage favoritesPage;
    SearchPage searchPage;

    public MemoTest () {
        Selenium selenium = new Selenium(driver);
        mainPage = new MainPage(selenium);
        carsPage = new CarsPage(selenium);
        carsSearchResultPage = new CarsSearchResultPage(selenium);
        favoritesPage = new FavoritesPage(selenium);
        searchPage = new SearchPage(selenium);
    }

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless").addArguments("window-size=1920x1080");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void  test1_addAdToFavorites() {
        mainPage.openSite();
        mainPage.openCarsCategory();
        carsPage.selectVwAnnouncements();
        favorites = new Favorites();
        carsSearchResultPage.selectFirstAd(favorites);
        favoritesCounter += carsSearchResultPage.addSelectedAdsToFavorites();
        Assert.assertEquals(carsSearchResultPage.getMemoCounter(), favoritesCounter);
    }

    @Test
    public void test2_addFilteredAdsToFavorites() {
        mainPage.openSite();
        mainPage.openCarsCategory();
        carsPage.selectMoskvichCategory();
        carsPage.filterCarsByPrices(2000, 15000);
        carsSearchResultPage.selectFilteredAds(favorites);
        favoritesCounter += carsSearchResultPage.addSelectedAdsToFavorites();
        Assert.assertEquals(carsSearchResultPage.getMemoCounter(), favoritesCounter);
    }

    @Test
    public void test3_CheckMemoList() {
        mainPage.openSite();
        mainPage.openFavoritesPage();
        Assert.assertTrue(favoritesPage.checkAdsByPrice(favorites));
    }

    @Test
    public void test4_AddFilteredAdToFavorites() {
        mainPage.openSite();
        mainPage.openSearchPage();
        searchPage.filterGameEvAds();
        searchPage.selectFirstAd(favorites);
        favoritesCounter += searchPage.addSelectedAdsToFavorites();
        Assert.assertEquals(carsSearchResultPage.getMemoCounter(), favoritesCounter);
    }

}
