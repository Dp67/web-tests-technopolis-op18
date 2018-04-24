package core.VideoPages;

import core.HelperBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebDriver;

public class BroadcastSkinPage extends HelperBase {

    public static final By READY_PICTURE_BROADCAST = By.xpath("//*[@class = 'button-pro attachButton']");
    public static final By PICTURE_FOR_BROADCAST = By.xpath("//*[@class = 'photo-crop']");
    public static final By CHECK_CHOOSE_PICTURE = By.xpath("//*[@class = 'photo-crop_cnt selectable-card __selected']");
    public static final By PICTURES_LAYER = By.xpath("//*[@class = 'modal-new_close_ovr scroll-right-indent']");
    public static final By CLOSE_PICTURES_LAYER = By.xpath("//*[@class = 'modal-new_close_ovr scroll-right-indent']");

    public BroadcastSkinPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Меню выбора фотографии не отображается",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PICTURES_LAYER), 10, 500));
    }
    //Выбор фотографии для обложки трнсляции
    public void choosePictureForBroadcast() {
        Assert.assertTrue("Фотографии не загружены", isElementPresent(PICTURE_FOR_BROADCAST));
        driver.findElement(PICTURE_FOR_BROADCAST).click();
    }
    //Нажатие на кнопку готово, после выбора фотографии для трансляции
    public void readyPictureForBroadcast() {
        Assert.assertTrue("Кнопка готовности обложки для трансляции не отображается", isElementPresent(READY_PICTURE_BROADCAST));
        driver.findElement(READY_PICTURE_BROADCAST).click();
        Assert.assertTrue("Окно выбора обложки не закрывается",
                explicitWait(ExpectedConditions.invisibilityOfElementLocated(CLOSE_PICTURES_LAYER), 10, 500));
    }
}
