import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

public class App {

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        String gridUrl = System.getProperty("GRID_URL");

        while (true) {
            WebDriver driver = null;
            try {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(false);
                chromeOptions.addArguments(
                        "--ignore-certificate-errors",
                        "--auto-open-devtools-for-tabs"
                );
                driver = RemoteWebDriver.builder()
                        .augmentUsing(new Augmenter())
                        .oneOf(chromeOptions)
                        .address(gridUrl)
                        .build();
                driver.get("http://example.com/");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (driver != null) {
                    driver.quit();
                }
            }

            TimeUnit.SECONDS.sleep(5);
        }
    }
}
