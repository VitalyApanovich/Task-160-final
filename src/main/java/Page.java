import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


class Page {

    private final String TITLE;
    WebDriver driver;

    Page(String title) {
        TITLE = title;
        driver = Driver.getDriver().get();
    }

    boolean isDisplayed() {
        return driver.getTitle().split("-")[0].contains(TITLE);
    }

    boolean isElementPresent(WebElement element){
        try{
            element.isDisplayed();
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

}
