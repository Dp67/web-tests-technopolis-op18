package core.WrapperForProgressBarVideo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Трансформер списка вебелементов в список ExampleWrapper
 */
public class ProgressBarTransformer {

    public static List<WrapperForProgressBar> wrap(List<WebElement> elements, WebDriver driver) {
        if (elements.isEmpty()) {
            return Collections.<WrapperForProgressBar>emptyList();
        }
        List<WrapperForProgressBar> bars = new ArrayList<WrapperForProgressBar>();
        for (WebElement bar : elements) {
            bars.add(new WrapperForProgressBar(bar, driver));
        }
        return bars;
    }
}
