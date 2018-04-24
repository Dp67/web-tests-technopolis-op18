package core;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DowloadPage extends HelperBase {

    private static final By NameOfPage = By.xpath(".//div[contains(@class, 'portlet_h') and text()='Добавить видео']");
    private static final By CHOOSE_MENU = By.xpath(".//div[contains(@id,'filter')]/span[contains(text(),'Загрузить')]");
    private static final By TERMS_OF_USER_AGREEMENT = By.xpath(".//*[contains(text(),'условия пользовательского')]");
    private static final By INPUT_BTN = By.xpath(".//*[contains(@name,'files')]");
    private static final By TAB_LOAD_BY_SITES = By.xpath(".//span[contains(text(),'Добавить по ссылке')]");
    private static final By TAB_LOAD_BY_PC = By.xpath(".//span[contains(text(),'с компьютера')]");
    private static final By INPUT_FOR_SITES_VIDEOS = By.xpath(".//input[@name ='st.vv_ugLink']");
    private static final By BTN_PUT_VIDEO_FROM_SITE = By.xpath(".//*[contains(@id,'Uploader')]//*[text()='Добавить' and contains(@class,'btn')]");
    private static final By FORM_PRELOADING_VIDEO_FROM_SITE= By.xpath(".//form[@method='POST']/div[contains(@class,'result')]");
    public DowloadPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                if (isElementPresent(NameOfPage)&isElementPresent(CHOOSE_MENU)&isElementPresent(TERMS_OF_USER_AGREEMENT)&isElementPresent(INPUT_BTN))
                 return true;
                else throw new IllegalArgumentException("From DowloadPage - Page elements not found!");
            }
        });
    }

    public MyVideosPage ijection() {
       type(DataForVideo.PATH.getClaim(),INPUT_BTN);
       return new MyVideosPage(driver);
    }

    public DowloadPageFromSites ClickToDowloadingFromSites(){
        click(TAB_LOAD_BY_SITES);
        return new DowloadPageFromSites(driver);
    }

}