package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {

    @FindBy(name = "firstname")
    private WebElement firstnameInput;

    @FindBy(name = "lastname")
    private WebElement lastnameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwoordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//div[@class='form-group']//button")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> errors;

    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public SignUpPage setFirstName(String firstName) {
        firstnameInput.sendKeys(firstName);
        return this;
    }

    public SignUpPage setLastName(String lastName) {
        lastnameInput.sendKeys(lastName);
        return this;
    }


    public SignUpPage setPhone(String phone) {
        phoneInput.sendKeys(phone);
        return this;
    }

    public SignUpPage setEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public SignUpPage setPassword(String password) {
        passwoordInput.sendKeys(password);
        return this;
    }

    public SignUpPage setConfirmPassword(String confirmPassword) {
        confirmPasswordInput.sendKeys(confirmPassword);

        return this;
    }

    public LoggedUserPage signUpButtonClick() {
        signUpButton.click();
        return new LoggedUserPage(driver);
    }

    public List<String> getErrors() {
        return errors.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

}
