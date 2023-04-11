package pl.seleniumdemo.test;

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

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Adam")
                .setLastName(lastName)
                .setPhone("666666666")
                .setEmail("tester" + randomNumber + "@tester.pl")
                .setPassword("123456")
                .setConfirmPassword("123456")
                .signUpButtonClick();

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Adam Kowalski");
    }

    @Test
    public void signUpBlankFormTest() {
        SignUpPage signUpPage = new HotelSearchPage(driver).openSignUpForm();
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

        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Adam")
                .setLastName("Kowalski")
                .setPhone("666666666")
                .setEmail("Tester")
                .setPassword("123456")
                .setConfirmPassword("123456");
        signUpPage.signUpButtonClick();


        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));

    }
}
