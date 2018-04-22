package tests;

import core.*;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TestCaseBroadcast_1 extends TestBase {

    public static String id1;
    public static String id2;

    @Before
    public void PreCondition(){
        init();
    }

    @Test
    public void testBroadcastCreation(){
        new LoginMainPage(driver).doLogin(new TestBot("+79111026260", "2206851qwe"));
        new MainPage(driver).clickVideoOnToolbar();
        VideoPage videoPage = new VideoPage(driver);
        videoPage.clickBroadcastButton();
        Assert.assertTrue("Меню настройки трансляции не отображается", isElementPresent(VideoPage.BROADCAST_SETTING));
        videoPage.settingBroadcast();
        videoPage.startPreviewBroadcast();
        BroadcastPage broadcastPage = new BroadcastPage(driver);
        id1 = driver.findElement(Verification.GET_ID_1).getAttribute("data-movie-id");
        broadcastPage.StartBroadcast();
        //Assert.assertTrue("Не отображается счетчик до начала трансляции", isElementPresent(BroadcastPage.COUNTER_BROADCAST));
        Promise promise = new BroadcastPage(driver).restartBroadcast().Error();
        broadcastPage.StopBroadcast();
        Assert.assertTrue("Сообщение об окончании трансляции не отображается", isElementPresent(BroadcastPage.MESSAGE_END_BROADCAST) );
        broadcastPage.closeBroadcastWindow();
        //Assert.assertTrue("Плеер трансляции не закрылся!", !isElementPresent(BROADCAST_LAYER) );
        new Verification(driver).CheckBroadcastPresence();
        id2 = driver.findElement(Verification.GET_ID_2).getAttribute("data-id");
        new Verification(driver).CheckBroadcastRecord();
    }

    @After
    public void tearDown(){
        VideoPage videoPage = new VideoPage(driver);
        videoPage.navigateToBroadcast();
        driver.findElement(VideoPage.DELETE_RECORD).click();
        driver.findElement(VideoPage.DELETE_RECORD_ACCEPT).click();
        stop();
    }
}