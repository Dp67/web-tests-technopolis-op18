package tests;

import core.*;
import core.DataForVideos.DataForVideoFromYoutube;
import core.DownloadPages.DowloadPage;
import core.DownloadPages.DowloadPageFromSites;
import core.VideoPages.MyVideosPage;
import core.VideoPages.VideoPage;
import core.WrapperForVideos.VideoWrapper;
import model.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class Download_TestCase2 extends TestBase {

    @Before
    public void SetUp() throws Exception {
        new LoginMainPage(driver).doLogin(new TestBot("QA18testbot87", "QA18testbot"));
        new MainPage(driver).clickVideoOnToolbar();
        new VideoPage(driver).clickButtonMyVideo();
        MyVideosPage myVideosPage =new MyVideosPage(driver);
        List<VideoWrapper> videos;
        while(myVideosPage.checkVideosPresent()) {
            videos = new MyVideosPage(driver).getVideos();
            videos.get(0).deleteVideos();
            myVideosPage.waitForVideo(videos.size() - 1);
        }
    }

    @Test
    public void testDownloadVideoFromYoutube() throws Exception {

        DowloadPage dowloadPage = new VideoPage(driver).clickDowloadVideoButton();
        DowloadPageFromSites dowloadPageFromSites = dowloadPage.clickToDowloadingFromSites();
        dowloadPageFromSites.putVideoFromSite(DataForVideoFromYoutube.URL.getClaim());
        dowloadPageFromSites.clickBtnAddVideo();
        MyVideosPage myVideosPage = new MyVideosPage(driver);
        myVideosPage.waitForVideo( 1);
        List<VideoWrapper> videos = new MyVideosPage(driver).getVideos();
        videos.get(0).checkDetailsVideoDisplayed();
        driver.navigate().refresh();
        myVideosPage.waitForVideo(1);
    }
}