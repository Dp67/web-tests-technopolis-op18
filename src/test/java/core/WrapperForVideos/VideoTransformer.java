package core.WrapperForVideos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Трансформер списка вебелементов в список ExampleWrapper
 */
public class VideoTransformer {

    public static List<WrapperForVideos> wrap(List<WebElement> elements, WebDriver driver) {
        if (elements.isEmpty()) {
            return Collections.<WrapperForVideos>emptyList();
        }
        List<WrapperForVideos> videos = new ArrayList<WrapperForVideos>();
        for (WebElement video : elements) {
            videos.add(new WrapperForVideos(video, driver));
        }
        return videos;
    }
}
