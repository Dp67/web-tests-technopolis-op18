package tests;

import core.HelperBase;
import core.VideoPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Verification extends HelperBase {

    private VideoPage videoPage = new VideoPage(driver);
    
    public static final By CHANNEL = By.xpath("//*[text() = 'Новый канал для тебя' and @class = 'mml_ucard_n_g textWrap']");
    public static final By GET_ID_PHOTO_4 = By.xpath("//*[@class = 'slider_img preview-image']");
    public static final By GET_ID_PHOTO_3 = By.xpath("//*[@class = 'vid-card_img__link']/descendant::*[@class = 'vid-card_img']");
    private static final By SLIDER_SUCCESS = By.xpath("//*[@class = 'slider_i_success']");
    private static final By PRE_BROADCAST = By.xpath("//*[text() = 'Предварительный просмотр']");
    private static final By VIDGET_RECORD = By.xpath("//*[@class = 'vid-card_img__link']");
    private static final By DURATION_RECORD = By.xpath("//*[@class = 'js-loader-container clearfix']/descendant::*[@class ='vid-card_duration']");
    public static final By GET_ID_1 = By.xpath("//*[@data-module = 'videostream/Layer']");
    public static final By GET_ID_2 = By.xpath("//*[@class = 'js-loader-container clearfix']/descendant::div[1]");
    public static final By GET_ID_PHOTO_1 = By.xpath("//*[@class = 'photo-crop_cnt selectable-card __selected']");
    public static final By GET_ID_PHOTO_2 = By.xpath("//*[@class = 'attach-photo_del attachInput']");
    public static final By MAIN_VIDEO_PAGE = By.xpath("//*[@class = 'jcol-l vl_sctn_h' and text() = 'Ваши видео']");
    public static final By BROADCAST_VIDGET = By.xpath("//*[@class = 'vid-card_img__link']");
    public static final By BROADCAST_CHANNEL_IND = By.xpath("//*[@class = 'js-loader-container clearfix ui-sortable']/descendant::div[1]");


    protected void check() {
    }

    public Verification(WebDriver driver) {
        super(driver);
    }

    //Проверка превью фотографии
    public void CheckBroadcastPicturePreview() {
        TestCaseBroadcast_2.id1 = "PHOTOODKL_" + TestCaseBroadcast_2.id1;
        if (TestCaseBroadcast_2.id2.equals(TestCaseBroadcast_2.id1)) {
            System.out.println("Превью фотографии совпадает с выбранной");
        } else {
            Assert.fail("Фотография не выбранная");
        }
    }
    //Проверка 2
    public void CheckBroadcastPictureSkin(){
        if (TestCaseBroadcast_2.id2.substring(65).equals(TestCaseBroadcast_2.id1.substring(65))) {
            System.out.println("Обложка трансляции совпадает с выбранной");
        } else {
            Assert.fail("Фотография не выбранная");
        }
    }

//Проверка присутствия записи трансляции
    public void CheckBroadcastPresence() {
        (new WebDriverWait(driver, 60)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                driver.navigate().refresh();
                videoPage.clickButtonMyVideo();
                videoPage.ButtonMyLives();
                Assert.assertTrue("Не отображается инвизибл",
                        explicitWait(ExpectedConditions.invisibilityOfElementLocated(MAIN_VIDEO_PAGE), 10, 500));
                Assert.assertTrue("Не отображается виджет созданной трансляции", isElementPresent(BROADCAST_VIDGET));
                return isElementVisible(DURATION_RECORD);
            }
        });
    }
    // Проверка id
    public void CheckBroadcastRecord(){
        if(TestCaseBroadcast_1.id1.equals(TestCaseBroadcast_1.id2)){
            System.out.println("Трансляция была осуществлена");
        }
        else{
            Assert.fail("Трансляция не была осуществлена");
        }
    }
    public void CheckAddRecordToChannel(){
        if(TestCaseBroadcast_4.id1.equals(TestCaseBroadcast_4.id2)){
            System.out.println("Добавление былы осуществлено");
        }
        else{
            Assert.fail("Добавление не было осуществлено");
        }
    }
    public void checkCreateBroadcast(){
        Assert.assertTrue("Канал не создан!", isElementPresent(CHANNEL));
        System.out.println("Канал успешно создан");
    }
}

