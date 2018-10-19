import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

class Driver {
    private static final String ADDRESS = "https://gmail.com";
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String DRIVER_NAME = "chrome";

    static ThreadLocal<WebDriver> getDriver() {
        WebDriver d = null;
        if (driver.get() == null) {
            switch (DRIVER_NAME.toLowerCase()) {
                case "chrome": default:
                    ChromeOptions caps = new ChromeOptions();
                    caps.setCapability("acceptInsecureCerts", true);
                    d = new ChromeDriver(caps);
                    break;
                case "firefox":
                    FirefoxOptions capsFF = new FirefoxOptions();
                    new FirefoxOptions().setCapability("marionette", true);
                    d = new FirefoxDriver(capsFF);
                    break;
                case "remote":
                    try {
                        d = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

            }
            assert d != null;
            d.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            d.manage().window().maximize();
            driver.set(d);
        }
        return driver;
    }

    static void openLoginPage() {
        getDriver().get().navigate().to(ADDRESS);
    }

    static void closeDriver() {
        driver.get().close();
        driver.set(null);
    }
}
