package pl.seleniumdemo.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultPage;

import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void getInfoTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("London");
        hotelSearchPage.setDates("29/07/2023", "29/08/2023");
        hotelSearchPage.setTravellers(1,2);
        hotelSearchPage.performSearch();

        ResultPage resultPages = new ResultPage(driver);
        List<String> hotelNames = resultPages.getHotelNames();

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");

        }

    @Test
    public void searchHotelWithoutNameTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("22/04/2023", "30/04/2023");
        hotelSearchPage.setTravellers(0,1);
        hotelSearchPage.performSearch();

        ResultPage resultPage = new ResultPage(driver);

        Assert.assertTrue(resultPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultPage.getHeadingText(), "No Results Found");

    }
}




