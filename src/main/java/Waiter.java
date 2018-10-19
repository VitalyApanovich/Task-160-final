import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class Waiter {

    static void waitForElement(WebElement element, int waitTime) {
        new WebDriverWait(Driver.getDriver().get(), waitTime).until(ExpectedConditions.visibilityOf(element));
    }

    static void waitForLetter(String letterSubject, int waitTime) {
        WebDriver driver = Driver.getDriver().get();
        By locator = By.xpath(String.format(InboxPage.LETTER_BY_SUBJECT, letterSubject));
        new WebDriverWait(driver, waitTime).until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
