import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;

import java.util.List;
import java.util.Random;

@Listeners(ClassListener.class)
public class Test160 {
    private final String LOGIN = Credentials.creds.getProperty("login");
    private final String LOGIN2 = Credentials.creds.getProperty("login2");
    private final String LOGIN3 = Credentials.creds.getProperty("login3");
    private final String PASS = Credentials.creds.getProperty("password");
    private String subject = "subj_" + new Random().nextInt(10000);

    private LoginPage loginPage;
    private InboxPage inboxPage;

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{LOGIN, PASS},
                new Object[]{LOGIN2, PASS},
                new Object[]{LOGIN3, PASS},
        };
    }

    @BeforeClass
    public void beforeClass() {
        Driver.openLoginPage();
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void afterMethod() {
        if (!loginPage.isDisplayed()) {
            inboxPage.logout();
        }
    }

    @AfterClass
    public void afterClass() {
        Driver.closeDriver();
    }

    @Features("Login Test")
    @Description("Verify if user can log in via Login page")
    @Test(dataProvider = "testData")
    public void loginTest(String login, String password) {
        inboxPage = loginPage.login(login, password);
        Assert.assertTrue(inboxPage.isDisplayed(), "Inbox page is not displayed");
    }

    @Features("Logout Test")
    @Description("Verify if user can log out from Inbox page")
    @Test(dataProvider = "testData")
    public void logoutTest(String login, String password) {
        inboxPage = loginPage.login(login, password);
        inboxPage.logout();
        Assert.assertTrue(loginPage.isDisplayed(), "Inbox page is not displayed");
    }

    @Features("Send emails Test")
    @Description("Verify if user can send emails")
    @Test
    public void sendEmailTest() {
        inboxPage = loginPage.login(LOGIN, PASS);
        inboxPage.initiateCompose().sendLetter(LOGIN2, subject, "testMessage");
        inboxPage = inboxPage.logout().login(LOGIN2, PASS).goToInboxFolder();
        List<String> result = inboxPage.getLettersSubjects();
        Assert.assertTrue(result.contains(subject), "There is no expected email in the Inbox folder");
    }

    @Features("Send emails Test")
    @Description("Verify if user can see emails in Sent folder")
    @Test(dependsOnMethods = "sendEmailTest")
    public void mailInSentFolderTest() {
        inboxPage = loginPage.login(LOGIN, PASS).goToSentFolder();
        List<String> result = inboxPage.getLettersSubjects();
        Assert.assertTrue(result.contains(subject),"There is no expected email in the Sent folder");
    }

    @Features("Delete emails Test")
    @Description("Verify if user can move emails to Trash folder")
    @Test(dependsOnMethods = "sendEmailTest")
    public void mailInTrashFolderTest() {
        inboxPage = loginPage.login(LOGIN2, PASS);
        inboxPage.sendChosenLetterToTrash(subject);
        inboxPage = inboxPage.goToTrashFolder();
        List<String> result = inboxPage.getLettersSubjects();
        Assert.assertTrue(result.contains(subject), "There is no expected email in the Trash folder");
    }
}
