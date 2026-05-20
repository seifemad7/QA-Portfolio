package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    By signButton = By.cssSelector("[data-qa='btn_header_signInOrUp-header-desktop']");
    By emailInput = By.cssSelector("[data-qa='lbl_value_']");
    By continueButton = By.cssSelector("[data-qa='btn_Continue']");
    By passwordTab = By.xpath("//button[@data-qa='تسجيل-الدخول-بكلمة-المرور']");
    By passwordField = By.id("password");
    By loginButton = By.xpath("//*[@id='overlay-portal']/div/div[2]/div/div[2]/div/div/button");

    By emailErrorMessage = By.cssSelector("[data-qa='lbl_email-addressError']");
    By emptyPasswordMessage = By.cssSelector(".PasswordInput-module-scss-module__URbSyG__fadeIn");
    By userName = By.cssSelector("[data-qa='dd_userName']");


    public void openSignIn() {
        driver.findElement(signButton).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void choosePasswordLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordTab)).click();
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isEmailErrorDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailErrorMessage)
        ).isDisplayed();
    }

    public boolean isEmptyPasswordMessageDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(emptyPasswordMessage)
        ).isDisplayed();
    }

    public boolean isUserLoggedIn() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(userName)
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }
}