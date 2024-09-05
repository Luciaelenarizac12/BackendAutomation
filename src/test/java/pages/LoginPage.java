package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import requestObject.RequestUser;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userName")
    private WebElement usernameElement;

    @FindBy(id = "password")
    private WebElement passwordElement;

    @FindBy(id = "login")
    private WebElement loginElement;

    @FindBy(id = "name")
    private WebElement errorMessageElement;

    public void loginIntoApplication(RequestUser requestBody){
        usernameElement.sendKeys(requestBody.getUserName());
        passwordElement.sendKeys(requestBody.getPassword());
        loginElement.click();
    }

    public void validateLoginError(){
        String actualError = errorMessageElement.getText();
        Assert.assertEquals(actualError, "Invalid username or password!");
    }

}