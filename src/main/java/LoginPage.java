import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

class LoginPage extends Page {
    private final static String TITLE = "Gmail";
    @FindBy(id = "identifierId")
    private WebElement usernameInput;
    @FindBy(css = "input[ type ='password']")
    private WebElement passwordInput;
    @FindBy(id = "password")
    private WebElement loginButton;
    @FindBy(id = "identifierNext")
    private WebElement idNextButton;
    @FindBy(id = "passwordNext")
    private WebElement passNextButton;
    @FindBy(id = "initialView")
    private WebElement loginContainer;
    @FindBy(id = "identifierLink")
    private WebElement identifierLink;
    @FindBy(id = "profileIdentifier")
    private WebElement profileIdentifierLink;


    LoginPage() {
        super(TITLE);
        PageFactory.initElements(driver, this);
        Waiter.waitForElement(loginContainer, 10);
    }

    InboxPage login(String login, String pass) {
        if (isElementPresent(profileIdentifierLink)) {
            profileIdentifierLink.click();
            Waiter.waitForElement(identifierLink, 15);
        }
        if (isElementPresent(identifierLink)) {
            new Actions(driver).moveToElement(identifierLink).perform();
            identifierLink.click();
            Waiter.waitForElement(usernameInput, 10);
        }
        usernameInput.sendKeys(login);
        idNextButton.click();
        Waiter.waitForElement(passwordInput, 10);
        passwordInput.sendKeys(pass);
        passNextButton.click();
        return new InboxPage();
    }
}
