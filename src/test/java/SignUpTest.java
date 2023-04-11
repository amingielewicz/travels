import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SignUpTest {
    @Test
    public void signUp() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1230, 730));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://www.kurs-selenium.pl/demo/");


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

}
