package core;

import core.WrapperForVideos.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public class VideoPage extends Toolbar {

    public static final By MENU_SETTING_BROADCAST = By.xpath("//*[@class = 'js-popup-tab js-main-tab']");
    private static final By PREVIEW_BROADCAST = By.xpath(".//*[@class = 'form-actions_yes vl_btn']");
    private static final By BUTTON_MYVIDEO = By.xpath("//*[@id = 'vv_btn_myVideo']");
    private static final By BUTTON_MYLIVES = By.xpath("//*[@id = 'vv_btn_myLives']");
    private static final By INVIS_BROADCAST = By.xpath("//*[@class = 'mml_irc_l']");
    private static final By BROADCAST = By.xpath(".//*[@class = 'vl_start-live __redesign vl_btn']");
    private static final By SETTING_BROADCAST = By.xpath("//*[@class = 'v-live h-mod']");
    private static final By SAVE_SETTING_BRODCAST = By.xpath("//*[@class = 'vl_btn' and @type = 'submit']");
    private static final By BUTTON_LOAD_PICTURE = By.xpath("//*[@class = 'thumbnails-w_h_ac tico']");
    private static final By BUTTON_SETTING_BROADCAST = By.xpath("//*[@class = 'vid-card_ac_i ic vl_ic_edit js-history']");
    private static final By VIDGET_BROADCAST = By.xpath("//*[@class = 'vid-card_n_w']");
    private static final By MENU_VIDEO = By.xpath("//*[@class = 'layer_hld inverted']");
    private static final By START_PREVIEW_BROADCAST = By.xpath("//*[@name = 'button_save']");
    public static final By SUBPANEL_VIDEO = By.xpath("//*[@class = 'mml_cat_btn js-history js-has-subsections js-icon-toggle js-opened __active']");
    public static final By WINDOW_CREAT_CHANNAL = By.xpath("//*[@class = 'portlet_h' and text() = 'Создание канала']");
    public static final By BROADCAST_SETTING = By.xpath("//*[@id = 'hook_Form_VideoVitrinaStartLiveForm']");
    public static final By DELETE_RECORD = By.xpath("//*[@class = 'vid-card_ac_i ic vl_ic_delete']");
    public static final By BROADCAST_LAYER = By.xpath("//*[@class = 'mml_ucard_n_g' and text() ='Мои трансляции']");
    public static final By PREVIEW_SKIN_BROADCAST = By.xpath("//*[@class = 'slider_img preview-image']");
    public static final By BUTTON_CHANNAL = By.xpath(".//*[@class = 'mml_subcat_ul __inner']/descendant::*[contains(@class,'mml_subcat_li js-droppable ui-droppable')]");
    public static final By PRE_CREAT_BUTTON_CHANNEL = By.xpath("//*[@id = 'vv_btn_create_channel_left_menu']");
    public static final By NAME_FIELD_CHANNEL = By.xpath(("//*[@name = 'st.vv_albumName']"));
    public static final By CREATE_CHANNEL_ACCEPT = By.xpath("//*[@class = 'vl_btn' and @value = 'Создать канал']");
    public static final By DELETE_CHANNEL = By.xpath("//*[@class = 'tico_img vl_ic_delete']");
    public static final By DELETE_CHANNEL_ACCEPT = By.xpath("//*[@value = 'Удалить' and @class = 'vl_btn']");
    public static final By DELETE_RECORD_ACCEPT = By.xpath("//*[@value = 'Удалить']");
    private static final By DOWLOAD_VIDEOS = By.xpath(".//*[contains(@class,'add') and contains(@class,'video')]/*[@class='vl_btn' or contains(@href,'upload')]"); //кнопка загрузить видео
   // private static final By MY_VIDEOS = By.xpath(".//*[text()='Моё видео' and contains(@class,'ellip')]/ancestor::*[contains(@class,'btn')]");
    private static final By INPUT_FOR_VIDEOS = By.xpath(".//input[@type='file' and @name='videos']"); //инпут для загрузки видео
    public static final By SECTIONS_BLOCK = By.xpath(".//ul[@class='mml_cat_ul']");
    public static final By SEARCH_VIDEO = By.xpath(".//div[@class='it_w search-input' and @id='vv-search']");
    public static final By VIDEO_PREVIEW = By.xpath(".//div[contains(@class,'vid-card js-frozen js-watched')]");
    public static final By WATCHLATER_VIDEO = By.xpath(".//a[@id='vv_btn_watchLater']");
    // Локаторы Дани
    public static final By VIDEO_FROZEN = By.xpath(".//div[contains(@class,'vid-card js-frozen js-watched')]"); //локатор для поиска отложенных(замороженных) видео


    public VideoPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(SECTIONS_BLOCK) && isElementPresent(SEARCH_VIDEO);
            }
        });
        Assert.assertTrue("Не отображается меню Видео",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MENU_VIDEO), 10, 500));
    }

    //Нажатие на кнопку "Мои Видео" в левом тулбаре Видео (вызывает список)
    public void clickButtonMyVideo() {
        Assert.assertTrue("Нет кнопки моё видео", isElementPresent(BUTTON_MYVIDEO));
        driver.findElement(BUTTON_MYVIDEO).click();
    }

    //Нажатие на кнопку "Загрузить" на верхней панели раздела Видео (вызывает форму загрузки видео)
    public DowloadPage clickDowloadVideoButton() {
        //Assert
        click(DOWLOAD_VIDEOS);
        return new DowloadPage(driver);
    }
    //Нажатие на кнопку в левом тулбаре Видео (открывает форму с отложенными видео)
    public void clickWatchLaterSection() {
        Assert.assertTrue("Отсутствует кнопка \"Отложенное видео\"", isElementPresent(WATCHLATER_VIDEO));
        click(WATCHLATER_VIDEO);
    }

    //Инъекция видео
    public void inputVideos(String path) {
        type(path,INPUT_FOR_VIDEOS);
    }

    //Нажатие на кнопку "Мои Трансляции" в списке кнопки Видео левого тулбара
    public void ButtonMyLives() {
        Assert.assertTrue("Не открылось окно трансляций", isElementPresent(BUTTON_MYLIVES));
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(BUTTON_MYLIVES));
        moveToElement(driver.findElement(BUTTON_MYLIVES));
        new Actions(driver).click(driver.findElement(BUTTON_MYLIVES)).build().perform();
    }

    // нажимаем на кнопку "Эфир"
    public void clickBroadcastButton() {
        Assert.assertTrue("Не отображается кнопка Эфир", isElementPresent(BROADCAST));
        driver.findElement(BROADCAST).click();
    }

    //Настройка трансляции
    public void settingBroadcast() {
        Assert.assertTrue("Не отображается инвизибл",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(INVIS_BROADCAST), 10, 500));
        //Assert.assertTrue("Не отображается инвизибл", isElementPresent(INVIS_BROADCAST));
        Wrapper ExSetting = new Wrapper(element, driver);
        ExSetting.Wrapper(driver.findElements(SETTING_BROADCAST), driver);

    }

    //Нажимаем на кнопку "Начать трансляцию"
    public void startPreviewBroadcast() {
        Assert.assertTrue("Не отображается кнопка превью трансляции", isElementPresent(START_PREVIEW_BROADCAST));
        driver.findElement(PREVIEW_BROADCAST).click();
    }

    public void navigateToBroadcast() {
        Assert.assertTrue("Не дождались кнопки окончания новой трансляции",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(VIDGET_BROADCAST), 10, 500));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(VIDGET_BROADCAST)).build().perform();
    }
//Кнопка редактирования трансляции
    public void editBroadcast() {
        navigateToBroadcast();
        Assert.assertTrue("Не отображается кнопка редактирования трансляции", isElementPresent(BUTTON_SETTING_BROADCAST));
        driver.findElement(BUTTON_SETTING_BROADCAST).click();
    }
//Нажатие на кнопку загрузки собственной обложки
    public void loadPictureForBroadcast() {
        Assert.assertTrue("Не отображается кнопка редактирования обложки", isElementPresent(BUTTON_LOAD_PICTURE));
        driver.findElement(BUTTON_LOAD_PICTURE).click();
    }
//Нажатие на кнопку сохранения настроек записи трансляции
    public void saveSettingBroadcast() {
        Assert.assertTrue("Кнопка сохранить не найдена", isElementPresent(SAVE_SETTING_BRODCAST));
        driver.findElement(SAVE_SETTING_BRODCAST).click();
        Assert.assertTrue("Не дождались кнопки окончания новой трансляции",
                explicitWait(ExpectedConditions.invisibilityOfElementLocated(MENU_SETTING_BROADCAST), 10, 500));
    }
    //Нажатие на кнопку создания канала
    public void buttonPreCreateChannalMenuVideo (){
        Assert.assertTrue("Кнопка Создания канала не найдена", isElementPresent(PRE_CREAT_BUTTON_CHANNEL));
        driver.findElement(PRE_CREAT_BUTTON_CHANNEL).click();
    }
    public void nameChannal(){
        Assert.assertTrue("Поле названия канала не найдена", isElementPresent(NAME_FIELD_CHANNEL));
        driver.findElement(NAME_FIELD_CHANNEL).clear();
        driver.findElement(NAME_FIELD_CHANNEL).sendKeys("Новый канал для тебя");
    }
    public void buttonCreateChannal(){
        Assert.assertTrue("Кнопка создания канала не найдена", isElementPresent(CREATE_CHANNEL_ACCEPT));
        driver.findElement(CREATE_CHANNEL_ACCEPT).click();
        Assert.assertTrue("Окно создание канала не закрылось",
                explicitWait(ExpectedConditions.invisibilityOfElementLocated(WINDOW_CREAT_CHANNAL), 10, 500));
    }

    public void chooseYourChannal(){
        new Select(driver.findElement(By.name("st.vv_albumId"))).selectByVisibleText("Новый канал для тебя");
    }

    public void buttonChannal(){
        Assert.assertTrue("Кнопка Созданного канала не найдена", isElementPresent(BUTTON_CHANNAL));
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(BUTTON_CHANNAL));
        driver.findElement(BUTTON_CHANNAL).click();
    }

//Данины методы для работы с враппером

    //Проверка с помощью враппера видеозаписей по имени
    public Boolean checkVideoByName(String videoName, List<VideoWrapper> videoList) {
        if (videoList.isEmpty()) return false;
        for (VideoWrapper video:videoList) {
            if (video.checkVideoNameNotNull().equals(videoName)) return true;
        }
        return false;
    }
    //Клик на видео по имени из листа видеозаписей
    public void clickOnVideoByName(String videoName, List<VideoWrapper> videoList) {
        Assert.assertFalse("Список видео не должен быть пустым", videoList.isEmpty());
        for (VideoWrapper video:videoList) {
            if (video.checkVideoNameNotNull().equals(videoName)) {
                video.getElement().click();
                break;
            }
        }
    }
    //Собираем враппер-лист из всех отложенных видео
    public List<VideoWrapper> getFrozenVideos() {
        if (isElementVisible(VIDEO_FROZEN)) {
            List<WebElement> videos = driver.findElements(VIDEO_FROZEN);
            return VideoTransformer.wrap(videos, driver);
        }
        return Collections.emptyList();
    }

}


