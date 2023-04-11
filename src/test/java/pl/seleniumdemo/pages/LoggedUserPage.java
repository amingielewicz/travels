package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class LoggedUserPage {

    @FindBy(xpath = "//h3[@class = 'RTL']")
    WebElement heading;

    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getHeadingText() {
        return heading.getText();
    }
}


