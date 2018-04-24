package tests;


import core.*;
import core.MainPage;
import core.TestBase;
import core.WrapperForProgressBarVideo.WrapperForProgressBar;
import core.WrapperForVideos.VideoWrapper;
import model.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class Download_TestCase4 extends TestBase {

    @Before
    public void SetUp() throws Exception {
        init();
        new LoginMainPage(driver).doLogin(new TestBot("QA18testbot85", "QA18testbot"));
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
    public void protectFromSameVideos() throws Exception {

        MyVideosPage myVideosPage = new MyVideosPage(driver);
        myVideosPage.inputVideos(DataForVideo.PATH.getClaim());
        myVideosPage.inputVideos(DataForVideo.PATH.getClaim());
        myVideosPage.waitForBar(2);
        List<WrapperForProgressBar> wrapperForProgressBars = new MyVideosPage(driver).getProgressBars();
        wrapperForProgressBars.get(1).checkErrorOfDownload();
        myVideosPage.waitForVideo( 1);
    }








    }
