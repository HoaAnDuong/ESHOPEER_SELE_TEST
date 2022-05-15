package EShopper;

import Common.Common.Utilities;
import Common.Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestBase extends TestPreCondition{

    @BeforeClass
    public void before() {
        System.setProperty("webdriver.chrome.driver", Utilities.getProjectPath() + "\\src\\main\\java\\Executables\\chromedriver.exe");
        Constant.WEBDRIVER = new ChromeDriver();

        Constant.WEBDRIVER.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

//    @AfterClass
//    public void after() {
//        System.out.println("Quit Chrome");
//        Constant.WEBDRIVER.quit();
//    }
}
