package org.openmrs.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.reference.page.HeaderPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.ProviderPage;
import org.openmrs.uitestframework.test.TestBase;

import static org.junit.Assert.assertTrue;

/**
 * Created by tomasz on 02.07.15.
 */
@Ignore
public class ProviderTest extends TestBase {

    private HeaderPage headerPage;
    private HomePage homePage;
    private ProviderPage providerPage;

    public ProviderTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Before
    public void setUp() {
        headerPage = new HeaderPage(driver);
        login();
        homePage = new HomePage(driver);
        providerPage = new ProviderPage(driver);
    }

    @After
    public void tearDown() throws InterruptedException{
        providerPage.clickOnHomeLink();
        headerPage.logOut();
    }

    @Ignore //ignored due to internal error
    @Test
    public void addEditRetireProviderTest() throws InterruptedException {
        homePage.goToAdministration();
        providerPage.manage();
        if(providerPage.exists("Super Nurse")) {
            providerPage.fillInRetireReason("too much providers of this kind");
            providerPage.retire();
        }
//        assertPage(providerPage);
        providerPage.add();
        providerPage.save();
        assertTrue(driver.getPageSource().contains("Provider Name or Person required"));
        providerPage.fillInIdentifier("super_nurse");
        providerPage.fillInPerson("Super Nurse");
        providerPage.save();
//        assertPage(providerPage);
        providerPage.findBySearch("Super Nurse");
        providerPage.fillInIdentifier("super_nurse2");
        providerPage.save();
        providerPage.findBySearch("Super Nurse");
        providerPage.retire();
        providerPage.waitForError();
        assertTrue(driver.getPageSource().contains("Retired Reason Required"));
        providerPage.fillInRetireReason("disease");
        providerPage.retire();
//        assertPage(providerPage);
    }
}
