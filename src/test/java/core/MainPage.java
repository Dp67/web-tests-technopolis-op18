package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends HelperBase{

    private static final By VIDEO = By.xpath(".//*[@class = 'toolbar_nav_a toolbar_nav_a__video']");
    private static final By MINI_TOOLBAR_MENU = By.xpath("//*[@class = 'ucard-mini toolbar_ucard']");
    private static final By CLICK_TO_EXITE = By.xpath("//*[@data-l = 't,logoutCurrentUser']");
    private static final By ACCEPT_EXITE = By.xpath("//*[@id = 'hook_FormButton_logoff.confirm_not_decorate']");
    private static final By TOP_TOOLBAR = By.xpath("//*[@class = 'toolbar_c']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Основная страница отображается некорректно",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(TOP_TOOLBAR), 10, 500));
    }
//Переход в меню видео через вверхний тулбар стратовой стриницы
    public void clickVideoOnToolbar() {
        Assert.assertTrue("Кнопка Видео не отображается", isElementPresent(VIDEO));
        driver.findElement(VIDEO).click();
    }
//Разлогинивание через вверхний тулбар
    public void clickOnMiniToolbar() {
        Assert.assertTrue("Кнопка мини тулбара не отображается", isElementPresent(MINI_TOOLBAR_MENU));
        driver.findElement(MINI_TOOLBAR_MENU).click();
    }
    public void clickQuitInMiniToolbar(){
        Assert.assertTrue("Выпадающее меню мини тулбара не отображается", isElementPresent(CLICK_TO_EXITE));
        driver.findElement(CLICK_TO_EXITE).click();
    }
    public void clickQuit (){
        Assert.assertTrue("Кнопка выходы из аккаунта не отображается", isElementPresent(ACCEPT_EXITE));
        driver.findElement(ACCEPT_EXITE).click();
    }
//Метод выхода из аккаунта через минитулбар
    public void QuitMT(){
        clickOnMiniToolbar();
        clickQuitInMiniToolbar();
        clickQuit();
    }
}