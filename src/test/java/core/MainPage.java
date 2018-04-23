package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends Toolbar{

    private static final By VIDEO = By.xpath(".//*[@class = 'toolbar_nav_a toolbar_nav_a__video']");
    private static final By MINI_TOOLBAR_MENU = By.xpath("//*[@class = 'ucard-mini toolbar_ucard']");
    private static final By CLICK_TO_EXITE = By.xpath("//*[@data-l = 't,logoutCurrentUser']");
    private static final By ACCEPT_EXITE = By.xpath("//*[@id = 'hook_FormButton_logoff.confirm_not_decorate']");
    private static final By TOP_TOOLBAR = By.xpath("//*[@class = 'toolbar_c']");
    public static final By SIDE_NAV_BAR = By.xpath(".//div[contains(@class,'nav-side __navigation')]");
    public static final By POSTING_FORM = By.xpath(".//div[@id='hook_Block_PostingForm']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //Жекина проверка
        Assert.assertTrue("Основная страница отображается некорректно",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(TOP_TOOLBAR), 10, 500));
        //Данина проверка
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(SIDE_NAV_BAR) && isElementPresent(POSTING_FORM);
            }
        });
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