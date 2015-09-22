package org.openmrs.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.reference.page.ActiveVisitsPage;
import org.openmrs.reference.page.HeaderPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.uitestframework.test.TestBase;

/**
 * Created by nata on 23.07.15.
 */
public class ComeBackToApplicationAfterLogoutTest extends TestBase {

    private HomePage homePage;
    private HeaderPage headerPage;

    public ComeBackToApplicationAfterLogoutTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Before
    public void setUp() throws Exception {
        loginPage.loginAsAdmin();
        homePage = new HomePage(driver);
        assertPage(homePage);
        headerPage = new HeaderPage(driver);
    }

    @Ignore//ignored due to relogin error
    @Test
    public void comeBackToApplicationAfterLogoutTest() throws Exception {
        homePage.goToActiveVisitsSearch();
        headerPage.logOut();
        loginPage.loginAsAdmin();
        assertPage(homePage);
    }

    @After
    public void tearDown ()throws Exception {
        headerPage.clickOnHomeIcon();
        headerPage.logOut();
    }
}
