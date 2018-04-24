package tests;

import core.*;
import core.VideoPages.BroadcastPage;
import core.VideoPages.BroadcastSkinPage;
import core.VideoPages.VideoPage;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Broadcast_TestCase2 extends TestBase{

    public static String id1;
    public static String id2;

    @Before
    public void PreCondition(){
        init();
        new LoginMainPage(driver).doLogin(new TestBot("+79111026260", "2206851qwe"));
        VideoPage videoPage = new MainPage(driver).clickVideoOnToolbar();
        videoPage.clickBroadcastButton();
        videoPage.settingBroadcast();
        videoPage.startPreviewBroadcast();
        BroadcastPage broadcastPage = new BroadcastPage(driver);
        broadcastPage.startBroadcast();
        Promise promise = new BroadcastPage(driver).restartBroadcast().Error();
        broadcastPage.stopBroadcast();
        broadcastPage.closeBroadcastWindow();
        new Verification(driver).CheckBroadcastPresence();
        new MainPage(driver).QuitMT();
    }

    @Test
    public void test(){
        new LoginMainPage(driver).doLogin(new TestBot("+79111026260", "2206851qwe"));
        new MainPage(driver).clickVideoOnToolbar();
        VideoPage videoPage = new VideoPage(driver);
        videoPage.clickButtonMyVideo();
        Assert.assertTrue("Не открывается сабпанель видео", isElementPresent(VideoPage.SUBPANEL_VIDEO));
        videoPage.buttonMyLives();
        Assert.assertTrue("Не открылось окно трансляций", isElementPresent(VideoPage.BROADCAST_LAYER));
        videoPage.editBroadcast();
        Assert.assertTrue("Не появляется меню редактирования", isElementPresent(VideoPage.MENU_SETTING_BROADCAST));
        videoPage.loadPictureForBroadcast();
        BroadcastSkinPage broadcastSkinPage = new BroadcastSkinPage(driver);
        broadcastSkinPage.choosePictureForBroadcast();
        Assert.assertTrue("Фотография не выбрана", isElementPresent(BroadcastSkinPage.CHECK_CHOOSE_PICTURE));
        id1 = driver.findElement(Verification.GET_ID_PHOTO_1).getAttribute("data-pid");
        broadcastSkinPage.readyPictureForBroadcast();
        //Assert.assertTrue("Окно выбора фотографии для обложки не закрылось", !isElementPresent(By.xpath("//*[@class = 'modal-new_close_ovr scroll-right-indent']")));
        Assert.assertTrue("Превью обложки не отображается", isElementPresent(VideoPage.PREVIEW_SKIN_BROADCAST));
        id2 = driver.findElement(Verification.GET_ID_PHOTO_2).getAttribute("data-id");
        new Verification(driver).CheckBroadcastPicturePreview();
        id1 = driver.findElement(Verification.GET_ID_PHOTO_4).getAttribute("src");
        videoPage.saveSettingBroadcast();
        //Assert.assertTrue("Меню настройки трансляции не закрылось", !isElementPresent(MENU_SETTING_BROADCAST));
        id2 = driver.findElement(Verification.GET_ID_PHOTO_3).getAttribute("src");
        new Verification(driver).CheckBroadcastPictureSkin();

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