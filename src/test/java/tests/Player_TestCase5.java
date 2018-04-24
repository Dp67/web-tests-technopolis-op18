package tests;

import core.*;
import core.FriendsPages.FriendPage;
import core.FriendsPages.FriendPlaylistPage;
import core.FriendsPages.FriendVideoPage;
import core.FriendsPages.FriendsMainPage;
import core.VideoPages.VideoPage;
import core.VideoPages.VideoPlayerPage;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Player_TestCase5 extends TestBase {

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
    public void watchLaterTest() throws Exception {
        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
        videoPlayerPage.clickWatchLater();
        String videoName = videoPlayerPage.getVideoName();
        videoPlayerPage.closeVideo();
        VideoPage videoPage = new FriendVideoPage(driver).clickVideoOnToolbar();
        videoPage.clickButtonMyVideo();
        videoPage.clickWatchLaterSection();
        String watchLaterVideoName = videoPage.getFrozenVideos().get(0).checkVideoNameNotNull();
        Assert.assertEquals("Видео отсутствует в отложенных", videoName, watchLaterVideoName);
    }
}
