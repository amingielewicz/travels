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

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstName) {
        firstnameInput.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastnameInput.sendKeys(lastName);
    }

    public void setPhone(String phone) {
        phoneInput.sendKeys(phone);
    }

    public void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void setPassword(String password) {
        passwoordInput.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        confirmPasswordInput.sendKeys(password);
    }

    public void signUpButtonClick() {
        signUpButton.click();
    }

    public List<String> getErrors() {
        return errors.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

}

