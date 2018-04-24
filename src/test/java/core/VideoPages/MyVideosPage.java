package core.VideoPages;

import core.DownloadPages.DowloadPage;
import core.HelperBase;
import core.WrapperForProgressBarVideo.ProgressBarTransformer;
import core.WrapperForProgressBarVideo.WrapperForProgressBar;
import core.WrapperForVideos.VideoTransformer;
import core.WrapperForVideos.VideoWrapper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collections;
import java.util.List;


public class MyVideosPage extends HelperBase {

    private static final By VIDEOS = By.xpath(".//div[contains(@class,'draggable')]");
    private static final By DELETE_VIDEO = By.xpath(".//a[contains(text(),'Удалить')]");
    private static final By CONFIRM_DELETE_VIDEO = By.xpath(".//input[contains(text(),'Удалить')]");
    private static final By DOWLOAD_VIDEOS = By.xpath(".//*[contains(@class,'add') and contains(@class,'video')]/*[@class='vl_btn' or contains(@href,'upload')]");
    private static final By INPUT_FOR_VIDEOS = By.xpath(".//input[@type='file' and @name='videos']");
    private static final By PROGRESS_BAR_CONTAINER = By.xpath(".//div[contains(@class,'progressbar')]"); //контейнер для прогресс баров
    private static final By PROGRESS_BAR = By.xpath(".//div[contains(@class,'js-uploader-container')]");
    private static final By PROGRESS_BAR_QUEUE = By.xpath(".//div[contains(@class,'container')and contains(@class,'queue')]");// прогресс бар дожидающийся загрузки;
    private static final By PROGRESS_BAR_DONE = By.xpath(".//div[contains(@class,'container')and contains(@class,'done')]");// прогресс бар загруженный;
    private static final By PROGRESS_BAR_LOAD = By.xpath(".//div[contains(@class,'container')and contains(@class,'load')]");// прогресс бар загружающийся;
    private static final By PROGRESS_BAR_ACTIVE = By.xpath(".//div[contains(@class,'container')and contains(@class,'active')]");// прогресс бар загружающийся;

    public MyVideosPage(WebDriver driver) {
        super(driver);
    }


    protected void check() {

    }

    public void checkForVideos() {
        List<VideoWrapper> videos = new MyVideosPage(driver).getVideos();
        Assert.assertTrue("Нет элементов видео", videos.get(0).checkDetailsVideoDisplayed());
        //for (int n=0;n<=(videos.size()-1);n++) videos.get(n).isDetailsVideoDisplayed();
    }
    public void waitForVideo(int number_videos) {
        Assert.assertTrue("Не дождались появления нового видео",
                explicitWait(ExpectedConditions.numberOfElementsToBe(VIDEOS,number_videos), 3+number_videos*50, 500));
    }
    public void waitForBar(int number_bars) {
        Assert.assertTrue("Не дождались появления загрузки/ок",
                explicitWait(ExpectedConditions.numberOfElementsToBe(PROGRESS_BAR,number_bars), 5+number_bars*5, 100));
    }


    public boolean checkVideosPresent () {
        return isElementPresent(VIDEOS);
    }


    public DowloadPage clickOnDowloadVideo() {
        click(DOWLOAD_VIDEOS);
        return new DowloadPage(driver);
    }

    public List<VideoWrapper> getVideos() {
        if (isElementPresent(VIDEOS)) {
            return VideoTransformer.wrap(driver.findElements(VIDEOS), driver);
        }
        return Collections.emptyList();
    }

    public List<WrapperForProgressBar> getProgressBars() {
        if (isElementPresent(PROGRESS_BAR)) {
            return ProgressBarTransformer.wrap(driver.findElements(PROGRESS_BAR), driver);
        }
        return Collections.emptyList();
    }

    public void inputVideos(String path) {
        type(path,INPUT_FOR_VIDEOS);
    }
}
