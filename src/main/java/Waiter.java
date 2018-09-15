import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class Waiter {

    static void waitForElement(WebElement element, int waitTime) {
        new WebDriverWait(Driver.getDriver().get(), waitTime).until(ExpectedConditions.visibilityOf(element));
    }

    static void implicitWait(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
