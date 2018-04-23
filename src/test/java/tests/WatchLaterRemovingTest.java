package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WatchLaterRemovingTest extends TestBase {

    @Before
    public void preCondition() throws Exception {
        MainPage mainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        VideoPage videoPage = mainPage.clickVideoOnToolbar();
        videoPage.clickButtonMyVideo();
        videoPage.clickWatchLaterSection();
    }

    @Test
    public void watchLaterRemovingTest() throws Exception {
        VideoPage videoPage = new VideoPage(driver);
        String watchLaterVideoName = "1";
        Boolean isVideoPresent = videoPage.checkVideoByName(watchLaterVideoName, videoPage.getFrozenVideos());
        Assert.assertTrue("Видео с таким именем отсутствует", isVideoPresent);
        videoPage.clickOnVideoByName(watchLaterVideoName, videoPage.getFrozenVideos());
        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
        videoPlayerPage.clickWatchLater();
        videoPlayerPage.closeVideo();
        driver.navigate().refresh();
        VideoPage videoPageAfterClose = new VideoPage(driver);
        videoPageAfterClose.clickButtonMyVideo();
        videoPageAfterClose.clickWatchLaterSection();
        Boolean isVideoPresentAfterRemoving = videoPageAfterClose.checkVideoByName(watchLaterVideoName, videoPageAfterClose.getFrozenVideos());
        Assert.assertFalse("Видео с таким именем присутствует", isVideoPresentAfterRemoving);
    }
}
