package core.WrapperForVideos;

import com.google.common.base.Preconditions;
import core.DataForVideos.DataForVideo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class VideoWrapper {

    private WebElement element;
    private WebDriver driver;

    private static final By VIEWS_VIDEO = By.xpath(".//div[contains(@class,'views')]");
    private static final By DURATION_VIDEO = By.xpath(".//div[contains(@class,'duration')]/div[contains(@class,'duration')]");
    private static final By NAME_VIDEO = By.xpath(".//div[contains(@class,'n_w')]/div[contains(@class,'n')]");
    private static final By DELETE_VIDEO = By.xpath(".//a[contains(@title,'Удалить')]");
    private static final By CONFIRM_DELETE_VIDEO = By.xpath(".//input[contains(@value,'Удалить')]");
    public static final By VIDEO_NAME = By.xpath(".//div[@class='vid-card_n']"); //почему паблик?
    private static final By DELETE_FORM = By.xpath(".//div[@class='portlet_h']");
    private String videoName; //подумать над другой реализацией

    public VideoWrapper(WebElement element, WebDriver driver) {
        this.driver = driver;
        this.element = element;
    }

    /**
     * Возвращает true если отображается что-то
     */
    public boolean checkDetailsVideoDisplayed() {
        if (!element.findElement(DURATION_VIDEO).isDisplayed())
            throw new IllegalArgumentException("From WrapperForScope - Duration of video not found!");
        else if (!element.findElement(VIEWS_VIDEO).isDisplayed())
            throw new IllegalArgumentException("From WrapperForScope - Views of video not found!");
        else if (!element.findElement(NAME_VIDEO).isDisplayed())
            throw new IllegalArgumentException("From WrapperForScope - Name of video not found!");
        else return true;
    }
    public void checkVideoDurationDisplayed() {
        Assert.assertTrue("From WrapperForScope - Duration of video not found!", element.findElement(DURATION_VIDEO).isDisplayed());
    }
    public void checkVideoViewsDisplayed() {
        Assert.assertTrue("From WrapperForScope - Views of video not found!", element.findElement(VIEWS_VIDEO).isDisplayed());
    }
    public void checkVideoNameDisplayed() {
        Assert.assertTrue("From WrapperForScope - Name of video not found!", element.findElement(NAME_VIDEO).isDisplayed());
    }


    //метод удаляющий видео
    public void deleteVideos() {
        moveToElement(element.findElement(DELETE_VIDEO));
        Assert.assertTrue("Не отображается кнопка удаление при наведении!", element.findElement(DELETE_VIDEO).isDisplayed());
        clickToElement(element.findElement(DELETE_VIDEO));
        Assert.assertTrue("Форма подтверждения удаления не появилась", element.findElement(DELETE_FORM).isDisplayed());
        moveToElement(driver.findElement(CONFIRM_DELETE_VIDEO));
        clickToElement(driver.findElement(CONFIRM_DELETE_VIDEO));
        Assert.assertTrue("Форма удаления не исчезла, после подтверждения!", !element.findElement(DELETE_FORM).isDisplayed());
    }
    //метод наведения на элемент
    public void moveToElement(WebElement webElement) {
        new Actions(driver).moveToElement(webElement).build().perform();
    }
    //метод кликающий на элемент
    public void clickToElement(WebElement webElement) {
        new Actions(driver).click(webElement).build().perform();
    }

    //Проверка видео по имени
    public void checkVideoName() {
        Assert.assertTrue("Имя видео не соответствует задуманному!", element.findElement(NAME_VIDEO).getText().equals(DataForVideo.NAME.getClaim()));
    }
    //Проверка видео по длительности
    public void checkVideoDuration() {
        Assert.assertTrue("Продолжительность видео не соответствует задуманному!", element.findElement(DURATION_VIDEO).getText().equals(DataForVideo.DURATION.getClaim()));
    }
    //Проверка видео по кол-ву просмотров
    public void checkVideoViews(String number_of_views) {
        Assert.assertTrue("Кол-во просмотров видео не соответствует задуманному!", element.findElement(VIEWS_VIDEO).getText().equals(number_of_views));
    }
    //Проверка по имени
    public String checkVideoNameNotNull() {
        videoName = element.findElement(VIDEO_NAME).getText();
        Preconditions.checkNotNull(videoName, "Название видео не может быть пустым");
        return videoName;
    }

    public WebElement getElement() {
        return element;
    }

}