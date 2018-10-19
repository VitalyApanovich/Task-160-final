import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

class InboxPage extends Page {
    private final static String TITLE = "Inbox";
    @FindBy(css = "[href*='Logout']")
    private WebElement logoutLink;
    @FindBy(css = "[title*='Inbox']")
    private WebElement inboxLink;
    @FindBy(css = "[title*='Sent']")
    private WebElement sentLink;
    @FindBy(css = "[title*='Trash']")
    private WebElement trashLink;
    @FindBy(xpath = "//span[text()='More']")
    private WebElement moreLink;
    @FindBy(css = "[href*='SignOutOptions']")
    private WebElement accountButton;
    @FindBy(xpath = "//div[text()='Compose']")
    private WebElement composeButton;
    @FindBy(css = "[placeholder='Search mail']")
    private WebElement searchInput;
    @FindBy(css = "button[aria-label='Search Mail']")
    private WebElement searchButton;
    @FindBy(css = "colgroup+tbody>tr")
    private List<WebElement> letters;
    @FindBy(css = "[aria-label='Delete']")
    private List<WebElement> deleteButtons;
    static final String LETTER_BY_SUBJECT = ".//span[text()='%s']";


    InboxPage() {
        super(TITLE);
        PageFactory.initElements(driver, this);
        Waiter.waitForElement(accountButton, 5);
    }

    LoginPage logout() {
        accountButton.click();
        logoutLink.click();
        return new LoginPage();
    }

    NewMessagePage initiateCompose() {
        composeButton.click();
        return new NewMessagePage();
    }

    InboxPage goToInboxFolder() {
        inboxLink.click();
        return new InboxPage();
    }

    InboxPage goToSentFolder() {
        sentLink.click();
        return new InboxPage();
    }

    InboxPage goToTrashFolder() {
        moreLink.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(trashLink);
        actions.perform();
        trashLink.click();
        return new InboxPage();
    }

    List<String> getLettersSubjects() {
        List<String> result = new ArrayList<>();
        letters.forEach(item -> result.add(item.findElement(By.cssSelector(".bog span")).getAttribute("innerText")));
        return result;
    }

    private WebElement getLetterBySubject(String subject) {
        for (WebElement item : letters) {
            if (item.findElement(By.cssSelector(".bog span")).getAttribute("innerText").equals(subject))
                return item;
        }
        return null;
    }

    void sendChosenLetterToTrash(String subject) {
        WebElement letter = getLetterBySubject(subject);
        assert letter != null;
        letter.findElement(By.cssSelector("[role='checkbox']")).click();
        clickDeleteButton();
    }

    private void clickDeleteButton() {
        for (WebElement item : deleteButtons) {
            if (item.isDisplayed()) {
                item.click();
                break;
            }
        }
    }
}
