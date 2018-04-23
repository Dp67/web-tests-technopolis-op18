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

    public static List<VideoWrapper> wrap(List<WebElement> elements, WebDriver driver) {
        if (elements.isEmpty()) {
            return Collections.<VideoWrapper>emptyList();
        }
        List<VideoWrapper> videos = new ArrayList<VideoWrapper>();
        for (WebElement video : elements) {
            videos.add(new VideoWrapper(video, driver));
        }
        return videos;
    }
}
