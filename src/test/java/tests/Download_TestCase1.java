package tests;


import core.*;
import core.DownloadPages.DowloadPage;
import core.VideoPages.MyVideosPage;
import core.VideoPages.VideoPage;
import core.WrapperForProgressBarVideo.WrapperForProgressBar;
import core.WrapperForVideos.VideoWrapper;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class Download_TestCase1 extends TestBase {

    @Before
    public void SetUp() throws Exception {
        init();
        new LoginMainPage(driver).doLogin(new TestBot("QA18testbot86", "QA18testbot"));
        new MainPage(driver).clickVideoOnToolbar();
        new VideoPage(driver).clickButtonMyVideo();
        MyVideosPage myVideosPage =new MyVideosPage(driver);
        List<VideoWrapper> videos;
       while(myVideosPage.checkVideosPresent()) {  //Цикл для удаления всех видео на аккаунте
            //  в цикле проверка на то, что на странице присутствуют видео
            videos = new MyVideosPage(driver).getVideos(); // Обновляем враппер-лист
            videos.get(0).DeleteVideos(); //удаляем первый элемент
            myVideosPage.waitForVideo(videos.size() - 1);  //ждем, что кол-во видео стало на одно меньше
        }

    }

    @Test
    public void testDownloadVideoFromPc() throws Exception {

        MyVideosPage myVideosPage = new MyVideosPage(driver);
        DowloadPage dowloadPage = myVideosPage.clickOnDowloadVideo();
        myVideosPage = dowloadPage.ijection();
        List<WrapperForProgressBar> wrapperForProgressBars = myVideosPage.getProgressBars();
        Assert.assertTrue("Процесс загрузки должен быть один!", wrapperForProgressBars.size()==1);
        wrapperForProgressBars.get(0).setNameVideo();
        wrapperForProgressBars.get(0).saveChanges();
        myVideosPage.waitForVideo(1);
        List<VideoWrapper> videos = new MyVideosPage(driver).getVideos();
        videos.get(0).checkDetailsVideoDisplayed();
        videos.get(0).checkVideoName();
        videos.get(0).checkVideoDuration();
        videos.get(0).checkVideoViews("0");
        driver.navigate().refresh();
        myVideosPage.waitForVideo(1);
    }
}