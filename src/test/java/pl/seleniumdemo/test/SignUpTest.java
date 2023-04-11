package pl.seleniumdemo.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {

        String lastName = "Kowalski";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "tester" + randomNumber + "@tester.pl";

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Marek");
        signUpPage.setLastName(lastName);
        signUpPage.setPhone("666888999");
        signUpPage.setEmail(email);
        signUpPage.setPassword("test123");
        signUpPage.setConfirmPassword("test123");

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Adam Kowalski");
    }


    @Test
    public void signUpBlankFormTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForms();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUpButtonClick();

        List<String> errors = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();

    }

    @Test
    public void signUpInvalidMailTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForms();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Marek");
        signUpPage.setLastName("Testowy");
        signUpPage.setPhone("666888999");
        signUpPage.setEmail("Testowy723");
        signUpPage.setPassword("test123");
        signUpPage.setConfirmPassword("test123");

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));

    }
}
