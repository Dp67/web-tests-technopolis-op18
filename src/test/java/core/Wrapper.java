package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Wrapper {
    private WebDriver driver;
    private WebElement element;
    private static final By VISIBLE_INDICATOT = By.xpath("//*[@class = 'mml_irc_l']");
    private static final By BROADCAST_TITLE = By.id("live-title");
    private static final By TEG = By.cssSelector("input.tag_it");
    private static final By DISCRIPTION = By.id("live-description");
    private static final By AVAILABILITY = By.id("live-access");
    private static final By CHANNEL = By.name("st.lv.aid");


    public Wrapper(WebElement element, WebDriver driver) {
        this.driver = driver;
        this.element = element;
    }

    public void Wrapper(List<WebElement> element, WebDriver driver) {
        //не оповещать друзей о начале трансляции
        driver.findElement(VISIBLE_INDICATOT).click();
        driver.findElement(BROADCAST_TITLE).clear();
        //название трансляции
        driver.findElement(BROADCAST_TITLE).sendKeys("Ого! Трансляция из НИК'а!" );
        //тег трансляции
        driver.findElement(TEG).clear();
        driver.findElement(TEG).sendKeys("Тестирование");
        //описание трансляции
        driver.findElement(DISCRIPTION).clear();
        driver.findElement(DISCRIPTION).sendKeys("Кажется у меня что-то получается. Или нет");
        //доспут по прямой ссылке
        new Select(driver.findElement(AVAILABILITY)).selectByVisibleText("По прямой ссылке");
    }
}