package tests;

import core.*;
import core.VideoPages.BroadcastPage;
import core.VideoPages.VideoPage;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Broadcast_TestCase1 extends TestBase {

    public static String id1;
    public static String id2;

    @Before
    public void PreCondition(){
        init();
    }

    @Test
    public void testBroadcastCreation(){
        new LoginMainPage(driver).doLogin(new TestBot("+79111026260", "2206851qwe"));
        VideoPage videoPage = new MainPage(driver).clickVideoOnToolbar();
        videoPage.clickBroadcastButton();
        Assert.assertTrue("Меню настройки трансляции не отображается", isElementPresent(VideoPage.BROADCAST_SETTING));
        videoPage.settingBroadcast();
        videoPage.startPreviewBroadcast();
        BroadcastPage broadcastPage = new BroadcastPage(driver);
        id1 = driver.findElement(Verification.GET_ID_1).getAttribute("data-movie-id");
        broadcastPage.startBroadcast();
        Promise promise = new BroadcastPage(driver).restartBroadcast().Error();
        broadcastPage.stopBroadcast();
        Assert.assertTrue("Сообщение об окончании трансляции не отображается", isElementPresent(BroadcastPage.MESSAGE_END_BROADCAST) );
        broadcastPage.closeBroadcastWindow();
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
