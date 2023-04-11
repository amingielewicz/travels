package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultPage {

    @FindBy(xpath = "//h4[contains(@class,'list_title')]//b")   //tu wyszukujemy listę
    private List<WebElement> hotelList;                         //wyszuka nam listę -> findElements

    @FindBy(xpath = "//div[@class='itemscontainer']//h2")
    public WebElement resultHeading;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> getHotelNames(){
        return hotelList.stream()           //zwraca listę z nazwami hoteli
                .map(e1 -> e1.getAttribute("textContent"))
                .collect(Collectors.toList());

    }

    public String getHeadingText() {
        return resultHeading.getText();
    }


}
