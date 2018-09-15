import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class NewMessagePage extends Page {
    private final static String TITLE = "New Message";
    @FindBy(name = "to")
    private WebElement toInputBox;
    @FindBy(name = "subjectbox")
    private WebElement subjectInputBox;
    @FindBy(xpath = "//div[text()='Send']")
    private WebElement sendButton;
    @FindBy(css = ".editable")
    private WebElement messageArea;

    NewMessagePage() {
        super(TITLE);
        PageFactory.initElements(driver, this);
        Waiter.waitForElement(toInputBox, 10);
    }

    void sendLetter(String toPerson, String subject, String message) {
        toInputBox.sendKeys(toPerson);
        subjectInputBox.sendKeys(subject);
        messageArea.sendKeys(message);
        sendButton.click();
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
