package tests;

import core.*;
import core.WrapperForVideos.WrapperForVideos;
import model.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestCase3 extends TestBase {

    @Before
    public void SetUp() throws Exception {
        new LoginMainPage(driver).doLogin(new TestBot("QA18testbot87", "QA18testbot"));
        new MainPage(driver).clickVideoOnToolbar();
        new VideoPage(driver).clickButtonMyVideo();
        MyVideosPage myVideosPage =new MyVideosPage(driver);
        List<WrapperForVideos> videos;
        while(myVideosPage.checkVideosPresent()) {
            videos = new MyVideosPage(driver).getVideos();
            videos.get(0).DeleteVideos();
            myVideosPage.waitForVideo(videos.size() - 1);
        }
    }

    @Test
    public void testDownloadVideoFromYoutube() throws Exception {

        DowloadPage dowloadPage = new VideoPage(driver).clickDowloadVideoButton();
        DowloadPageFromSites dowloadPageFromSites = dowloadPage.ClickToDowloadingFromSites();
        DataForVideoFromYoutube dataForVideoFromYoutube = DataForVideoFromYoutube.URL;
        dowloadPageFromSites.putVideoFromSite(dataForVideoFromYoutube.getClaim());
        dowloadPageFromSites.clickBtnAddVideo();
        MyVideosPage myVideosPage = new MyVideosPage(driver);
        myVideosPage.waitForVideo( 1);
        List<WrapperForVideos> videos = new MyVideosPage(driver).getVideos();
        videos.get(0).checkDetailsVideoDisplayed();
        driver.navigate().refresh();
        myVideosPage.waitForVideo(1);
    }
}