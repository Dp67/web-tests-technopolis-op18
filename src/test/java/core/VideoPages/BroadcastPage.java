package core.VideoPages;

import core.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class BroadcastPage extends HelperBase {

    public static final By COUNTER_BROADCAST = By.xpath("//*[@class = 'vp-counter js-vs-counter']");
    public static final By CREATE_NEW_BROADCAST = By.xpath(".//*[@class = 'button-pro form-actions_yes js-vs-btn-start']");
    public static final By CLOSE_BROADCAST = By.xpath(".//*[@class = 'button-pro __sec js-vs-btn-close']");
    public static final By STOP_BROADCAST = By.xpath(".//*[@class = 'button-pro vs-form_stop-btn form-actions_yes js-vs-btn-stop']");
    public static final By BROADCAST_PLAYER = By.xpath(".//*[@class = 'vp-layer sidebar-animation']");
    public static final By MESSAGE_END_BROADCAST = By.xpath("//*[@class = 'vp_video_stub __na']");
    public static final By BROADCAST_LAYER = By.xpath(".//*[@data-module = 'videostream/Layer']");
    public static final By ERROE_START_BROADCAST = By.xpath("//*[@class = 'vp_video_stub_txt vs-error-text' and text() = 'Не удалось создать трансляцию, проверьте микрофон и камеру, и повторите попытку']");

    public BroadcastPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Не отображается плеер трансляции",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BROADCAST_PLAYER), 10, 500));
    }

    //Запуск трансляции
    public void startBroadcast() {
        Assert.assertTrue("Не дождались кнопки созданиия новой трансляции",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CREATE_NEW_BROADCAST), 10, 500));
        driver.findElement(CREATE_NEW_BROADCAST).click();
    }
    //Остановка записи трансляции
    public void stopBroadcast() {
        Assert.assertTrue("Не дождались кнопки окончания новой трансляции",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(STOP_BROADCAST), 10, 500));
        driver.findElement(STOP_BROADCAST).click();
    }
// Закрыть окно трансляции
    public void closeBroadcastWindow() {
        Assert.assertTrue("Не отображается кнопка закрытия трансляции", isElementPresent(CLOSE_BROADCAST));
        driver.findElement(CLOSE_BROADCAST).click();
        Assert.assertTrue("Плеер трансляции не закрылся",
                explicitWait(ExpectedConditions.invisibilityOfElementLocated(BROADCAST_LAYER), 10, 500));
    }
//Попытка реализации Promis
    public Promise restartBroadcast() {
        Assert.assertTrue("Счетчик отсчета до начала трансляции завис",
                explicitWait(ExpectedConditions.invisibilityOfElementLocated(COUNTER_BROADCAST), 10, 100));
        final boolean errorStartBroadcast = isElementPresent(ERROE_START_BROADCAST);
        if(errorStartBroadcast){
            Assert.fail("Трансляция не запустилась");
            return null;
        }
        else {System.out.println("Трансляция запущена");}
        return new Promise(driver).Error();
    }
}
