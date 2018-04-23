package core.WrapperForVideos;

import core.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WrapperForVideos {

    private WebElement element;
    private WebDriver driver;

    private static final By VIEWS_VIDEO = By.xpath(".//div[contains(@class,'views')]");
    private static final By DURATION_VIDEO = By.xpath(".//div[contains(@class,'duration')]/div[contains(@class,'duration')]");
    private static final By NAME_VIDEO = By.xpath(".//div[contains(@class,'n_w')]/div[contains(@class,'n')]");
    private static final By DELETE_VIDEO = By.xpath(".//a[contains(@title,'Удалить')]");
    private static final By CONFIRM_DELETE_VIDEO = By.xpath(".//input[contains(@value,'Удалить')]");

    public WrapperForVideos(WebElement element, WebDriver driver) {
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
    public boolean checkVideoDurationDisplayed() {
        if (!element.findElement(DURATION_VIDEO).isDisplayed())
            throw new IllegalArgumentException("From WrapperForScope - Duration of video not found!");
        else return true;
    }
    public boolean checkVideoViewsDisplayed() {
        if (!element.findElement(DURATION_VIDEO).isDisplayed())
            throw new IllegalArgumentException("From WrapperForScope - Duration of video not found!");
        else return true;
    }
    public boolean checkVideoNameDisplayed() {
        if (!element.findElement(DURATION_VIDEO).isDisplayed())
            throw new IllegalArgumentException("From WrapperForScope - Duration of video not found!");
        else return true;
    }



    public void DeleteVideos() {
        moveToElement(element.findElement(DELETE_VIDEO));
        System.out.println("Навелся на удаление");
        clickToElement(element.findElement(DELETE_VIDEO));
        System.out.println("Нажал удалить");
        moveToElement(driver.findElement(CONFIRM_DELETE_VIDEO));
        System.out.println("Навелся на элемент");
        clickToElement(driver.findElement(CONFIRM_DELETE_VIDEO));
        System.out.println("Нажал на элемент");
    }



    public void moveToElement(WebElement webElement) {
        new Actions(driver).moveToElement(webElement).build().perform();
    }
    public void clickToElement(WebElement webElement) {
        new Actions(driver).click(webElement).build().perform();
    }

    public void checkVideoName() {
        DataForVideo dataForVideo = DataForVideo.NAME;
        System.out.println(element.findElement(NAME_VIDEO).getText());
        Assert.assertTrue("Имя видео не соответствует задуманному!", element.findElement(NAME_VIDEO).getText().equals(dataForVideo.getClaim()));
    }
    public void checkVideoDuration() {
        DataForVideo dataForVideo = DataForVideo.DURATION;
        System.out.println(element.findElement(DURATION_VIDEO).getText());
        Assert.assertTrue("Продолжительность видео не соответствует задуманному!", element.findElement(DURATION_VIDEO).getText().equals(dataForVideo.getClaim()));
    }
    public void checkVideoViews() {
        DataForVideo dataForVideo = DataForVideo.VIEWS;
        Assert.assertTrue("Кол-во просмотров видео не соответствует задуманному!", element.findElement(VIEWS_VIDEO).getText().equals(dataForVideo.getClaim()));
    }
    public void clickOnVideo() {
        DataForVideo dataForVideo = DataForVideo.VIEWS;
        Assert.assertTrue("Кол-во просмотров видео не соответствует задуманному!", element.findElement(VIEWS_VIDEO).getText().equals(dataForVideo.getClaim()));
    }


}