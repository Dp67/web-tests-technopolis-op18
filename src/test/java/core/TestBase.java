package core;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class TestBase {
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    protected WebDriver driver;
    private WebElement element;

    @After
    public void tearDown() throws Exception {
        stop();
    }

    public void init() {
       // System.setProperty("webdriver.chrome.driver", "C:\\Users\\Eugene\\Desktop\\chromedriver.exe");
        ChromeDriverService driverService = ChromeDriverService.createDefaultService();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("allow-file-access-from-files");
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");
        driver = new ChromeDriver(driverService, options);
        driver.manage().window().setSize(new Dimension(1600, 1200));
        baseUrl = "https://ok.ru/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}