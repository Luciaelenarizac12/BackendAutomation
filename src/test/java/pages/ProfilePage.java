package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import requestObject.RequestUser;

import java.time.Duration;

public class ProfilePage extends BasePage{
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userName-value")
    private WebElement usernameValueElement;

    @FindBy(xpath = "//button[text()='Delete Account']")
    private WebElement deleteAccountElement;

    @FindBy(id = "closeSmallModal-ok")
    private WebElement deleteAccountOKElement;

    public void validateLoginProcess(RequestUser requestBody){

        String actualUsername = usernameValueElement.getText();
        Assert.assertEquals(actualUsername, requestBody.getUserName());

    }

    public void deleteAccount(){
        deleteAccountElement.click();
        deleteAccountOKElement.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert accountAlert = driver.switchTo().alert();
        System.out.println(accountAlert.getText());
        accountAlert.accept();
    }




}