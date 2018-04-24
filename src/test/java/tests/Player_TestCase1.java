package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class Player_TestCase1 extends TestBase {

    @Before
    public void preCondition() throws Exception {
        MainPage mainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        FriendsMainPage friendsMainPage = mainPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend();
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        friendPlaylistPage.selectVideo();
    }

    @Test
    public void likeTest() throws Exception {
        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
        int likeBefore = videoPlayerPage.getLikeCount();
        final WebElement likeElement = driver.findElement(videoPlayerPage.LIKE_COUNT);
        videoPlayerPage.clickLike();
        videoPlayerPage.waitStalenessOfElement(likeElement);
        int likeAfter = videoPlayerPage.getLikeCount();
        Assert.assertEquals("Количество классов не совпадает", likeBefore + 1, likeAfter);
        videoPlayerPage.closeVideo();
        FriendVideoPage friendVideoPage = new FriendVideoPage(driver);
        friendVideoPage.clickUserMenu();
        friendVideoPage.clickExitButton();
        LoginMainPage loginMainPage = friendVideoPage.confirmExit();
        MainPage mainPage = loginMainPage.doLogin(new TestBot("89315960060", "q123451234"));
        mainPage.clickFeedback();
        String actualLikeFeedbackText = mainPage.getActualLikeFeedbackText();
        String likeFeedbackText = mainPage.getLikeFeedbackText();
        Assert.assertEquals("Текст уведомления о классе не совпадает", likeFeedbackText, actualLikeFeedbackText);
    }
}
