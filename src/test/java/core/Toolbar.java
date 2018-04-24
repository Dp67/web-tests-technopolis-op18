package core;

import core.FriendsPages.FriendsMainPage;
import core.VideoPages.VideoPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class Toolbar extends HelperBase {

    public static final By VIDEO = By.xpath(".//li[@id='hook_Block_TopMenuVideo']");
    public static final By FRIENDS = By.xpath(".//li[@data-l='t,friends']");
    public static final By USER_MENU = By.xpath(".//div[@class='toolbar_dropdown_w h-mod']");
    public static final By EXIT_BUTTON = By.xpath(".//a[contains(@data-l, 'logout') and text()='Выйти']");
    public static final By CONFIRM_EXIT = By.xpath(".//input[contains(@data-l, 'confirm') and @value='Выйти']");
    public static final By FEEDBACK = By.xpath(".//span[contains(@class, 'feedback')]");
    public static final By FEEDBACK_LIKE_VIDEO = By.xpath(".//div[contains(@class,'notif_tx')]");
    public static final String likeFeedbackText = "QA18testbot58 QA18testbot58 считает классным видео «1» ";

    public Toolbar(WebDriver driver) {
        super(driver);
    }

    public VideoPage clickVideoOnToolbar() {
        Assert.assertTrue("Отсутствует кнопка \"Видео\" на тулбаре", isElementPresent(VIDEO));
        click(VIDEO);
        return new VideoPage(driver);
    }

    public FriendsMainPage clickFriendsOnToolbar() {
        Assert.assertTrue("Отсутствует кнопка \"Друзья\" на тулбаре", isElementPresent(FRIENDS));
        click(FRIENDS);
        return new FriendsMainPage(driver);
    }

    public void clickUserMenu() {
        Assert.assertTrue("Отсутствует кнопка для открытия меню пользователя", isElementPresent(USER_MENU));
        click(USER_MENU);
    }

    public void clickExitButton() {
        Assert.assertTrue("Отсутствует кнопка \"Выйти\"", isElementPresent(EXIT_BUTTON));
        click(EXIT_BUTTON);
    }

    public LoginMainPage confirmExit() {
        Assert.assertTrue("Отсутствует кнопка подтверждения выхода", isElementPresent(CONFIRM_EXIT));
        click(CONFIRM_EXIT);
        return new LoginMainPage(driver);
    }

    public void clickFeedback() {
        Assert.assertTrue("Отсутствует кнопка \"События\" на тулбаре", isElementPresent(FEEDBACK));
        click(FEEDBACK);
    }

    public String getActualLikeFeedbackText() {
        return driver.findElements(FEEDBACK_LIKE_VIDEO).get(0).getText();
    }

    public String getLikeFeedbackText() {
        return likeFeedbackText;
    }


}
