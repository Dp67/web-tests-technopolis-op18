package core;

import model.TestBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginMainPage extends HelperBase {

    public static final By TEXT_FIELDS = By.xpath(".//div[@class='it_w']");

    public LoginMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(TEXT_FIELDS);
            }
        });
    }

    public MainPage doLogin(TestBot testBot) {
        type(testBot.getLogin(), By.id("field_email"));
        type(testBot.getPassword(), By.id("field_password"));
        click(By.xpath(".//*[contains(@value,'Войти')]"));
        return new MainPage(driver);
    }
}