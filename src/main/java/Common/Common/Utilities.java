package Common.Common;

import Common.Constant.Constant;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Utilities {

    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    public static void scrollIntoView(WebElement scrollToElement) {
        WebElement element = scrollToElement;
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void waitForElement() throws InterruptedException {
        Thread.sleep(1000);
        Constant.WEBDRIVER.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Constant.WEBDRIVER.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
    }

}
