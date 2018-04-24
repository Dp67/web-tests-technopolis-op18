package core.WrapperForProgressBarVideo;

import core.DataForVideos.DataForVideo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WrapperForProgressBar {

    private WebElement element;
    private WebDriver driver;

    private static final By TAGS_VIDEO_FORM = By.xpath(".//div[contains(@class,'tags') and contains(@class,'form')]");
    private static final By PROGRESS_OF_DOWLOADING_VIDEO = By.xpath(".//div[contains(@class,'load')and contains(@class,'progress')]");
    private static final By NAME_VIDEO_FORM = By.xpath(".//div[contains(@class,'name') and contains(@class,'form')]");
    private static final By DESCRIPTION_VIDEO_FORM = By.xpath(".//div[contains(@class,'description') and contains(@class,'form')]");
    private static final By BTN_SAVE = By.xpath(".//button[text()='Сохранить']");
    private static final By SET_VISIBILITY_FORM = By.xpath(".//div[contains(@class,'opt')]"); //форма установки видимости видео
    private static final By INPUT_NAME_VIDEO = By.xpath(".//input[@name='mname']"); //инпут для имени видео
    private static final By INPUT_DESCRIPTION_VIDEO = By.xpath(".//textarea[@name='mdesc']"); //описание видео
    private static final By INPUT_TAGS_VIDEO = By.xpath(".//input[contains(@class,'tag')]"); // инпут для ключевых слов для видео
    private static final By PROGRESS_BAR = By.xpath(".//div[contains(@class,'progressbar')]");
    private static final By BTN_CLOSE = By.xpath(".//span[contains(@class,'delete')]/span[contains(@class,'close')]");
    private static final By BTN_RESUME = By.xpath(".//span[contains(@class,'resume')]");
    private static final By BAR_ERROR = By.xpath(".//div[contains(@class,'ellip')and contains(@class,'err') and contains(@class,'progress') ]");
    private static final By CHANGES_SAVE_INFO = By.xpath(".//div[contains(text(),'Изменения сохранены')]");

    public WrapperForProgressBar(WebElement element, WebDriver driver) {
        this.driver = driver;
        this.element = element;
    }

    /**
     * Возвращает true если отображается что-то
     */
    public void isDetailsProgressBarDisplayed() {

        (new WebDriverWait(driver, 4))
                .until(ExpectedConditions.visibilityOfElementLocated(TAGS_VIDEO_FORM));
        (new WebDriverWait(driver, 4))
                .until(ExpectedConditions.visibilityOfElementLocated(NAME_VIDEO_FORM));
        (new WebDriverWait(driver, 4))
                .until(ExpectedConditions.visibilityOfElementLocated(BTN_SAVE));
        (new WebDriverWait(driver, 4))
                .until(ExpectedConditions.visibilityOfElementLocated(PROGRESS_OF_DOWLOADING_VIDEO));
        (new WebDriverWait(driver, 4))
                .until(ExpectedConditions.visibilityOfElementLocated(SET_VISIBILITY_FORM));
        (new WebDriverWait(driver, 4))
                .until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_VIDEO_FORM));
    }

    public boolean isDetailsVideoTrue(String duration, String name) {
        if (!(element.findElement(INPUT_NAME_VIDEO).getText() == duration))
            throw new IllegalArgumentException("From WrapperForScope - Duration of video not found!");
        else if (!(element.findElement(INPUT_TAGS_VIDEO).getText() == "0"))
            throw new IllegalArgumentException("From WrapperForScope - Views of video not found!");
        else if (!(element.findElement(INPUT_DESCRIPTION_VIDEO).getText() == name))
            throw new IllegalArgumentException("From WrapperForScope - Name of video not found!");
        else return true;
    }
    public void stopDowloadVideo () {
        element.findElement(BTN_CLOSE).click();
        Assert.assertTrue("Не найдена кнопка Возобновить", element.findElement(BTN_RESUME).isDisplayed());
    }
    public void resumeDownloadVideo (){
        driver.findElement(BTN_RESUME).click();
        Assert.assertTrue("Не найден элемент создания группы", !(driver.findElement(BTN_RESUME).isDisplayed()));
    }
    public void setNameVideo() {
        type(DataForVideo.NAME.getClaim(), INPUT_NAME_VIDEO);
    }
    public void setNamesVideo(int n) {
        type(DataForVideo.NAME.getClaim()+n, INPUT_NAME_VIDEO);
    }
    public void checkErrorOfDownload() {
        Assert.assertTrue("Нет сообщения о ошибке!", element.findElement(BAR_ERROR).isDisplayed());
    }
    public void saveChanges() {
        element.findElement(BTN_SAVE).click();
        Assert.assertTrue("Нет сообщения о сохранении изменений!", element.findElement(CHANGES_SAVE_INFO).isDisplayed());
    }

    protected void type(String text, By locator) {
        element.findElement(locator).clear();
        element.findElement(locator).sendKeys(text);
    }

}