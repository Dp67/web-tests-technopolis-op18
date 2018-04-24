package tests;

import core.*;
import core.WrapperForProgressBarVideo.WrapperForProgressBar;
import core.WrapperForVideos.VideoWrapper;
import model.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class Download_TestCase3 extends TestBase {

    @Before
    public void SetUp() throws Exception {
        new LoginMainPage(driver).doLogin(new TestBot("QA18testbot82", "QA18testbot"));
        new MainPage(driver).clickVideoOnToolbar();
        new VideoPage(driver).clickButtonMyVideo();
        MyVideosPage myVideosPage =new MyVideosPage(driver);
        List<VideoWrapper> videos;
        while(myVideosPage.checkVideosPresent()) {
            videos = new MyVideosPage(driver).getVideos();
            videos.get(0).DeleteVideos();
            myVideosPage.waitForVideo(videos.size() - 1);
        }
    }

    @Test
    public void testDownloadVideoStopAndResume() throws Exception {
        MyVideosPage myVideosPage = new MyVideosPage(driver);
        DowloadPage dowloadPage = myVideosPage.clickOnDowloadVideo();
        myVideosPage = dowloadPage.ijection();
        List<WrapperForProgressBar> wrapperForProgressBars = myVideosPage.getProgressBars();
        wrapperForProgressBars.get(0).stopDowloadVideo();
        wrapperForProgressBars.get(0).resumeDownloadVideo();
        myVideosPage.waitForVideo( 1);
        List<VideoWrapper> videos = new MyVideosPage(driver).getVideos();
        videos.get(0).checkDetailsVideoDisplayed();
        driver.navigate().refresh();
        myVideosPage.waitForVideo(1);
    }
}