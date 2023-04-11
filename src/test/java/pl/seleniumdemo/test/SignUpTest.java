package pl.seleniumdemo.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.test.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {

        String lastName = "Kowalski";

        int randomNumber = (int) (Math.random()*1000);
        String email = "tester" + randomNumber + "@tester.pl";

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click); //to podejście jest lepsze
        driver.findElements(By.xpath("//a[text() = '  Sign Up']")).get(1).click(); //niż to
        driver.findElement(By.name("firstname")).sendKeys("Adam");
        driver.findElement(By.name("lastname")).sendKeys("Kowalski");
        driver.findElement(By.name("phone")).sendKeys("123456");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Kowalski123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Kowalski123");
        driver.findElement(By.xpath("//div[@class='form-group']//button")).click();
        WebElement heading = driver.findElement(By.xpath("//h3[@class = 'RTL']"));
        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(), "Hi, Adam Kowalski");


    }
    @Test
    public void signUpBlankFormTest(){

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click); //to podejście jest lepsze
        driver.findElements(By.xpath("//a[text() = '  Sign Up']")).get(1).click(); //niż to
        driver.findElement(By.xpath("//div[@class='form-group']//button")).click();

        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();


    }
    @Test
    public void signUpIvalidMailTest() {

        String lastName = "Kowalski";

        int randomNumber = (int) (Math.random()*1000);
        String email = "tester" + randomNumber;

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click); //to podejście jest lepsze
        driver.findElements(By.xpath("//a[text() = '  Sign Up']")).get(1).click(); //niż to
        driver.findElement(By.name("firstname")).sendKeys("Adam");
        driver.findElement(By.name("lastname")).sendKeys("Kowalski");
        driver.findElement(By.name("phone")).sendKeys("123456");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Kowalski123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Kowalski123");
        driver.findElement(By.xpath("//div[@class='form-group']//button")).click();

        List<String> invalidEmailError = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertTrue(invalidEmailError.contains("The Email field must contain a valid email address."));


    }


}