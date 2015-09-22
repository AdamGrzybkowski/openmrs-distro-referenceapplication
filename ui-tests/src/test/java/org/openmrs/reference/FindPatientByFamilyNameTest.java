package org.openmrs.reference;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.reference.page.HeaderPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.uitestframework.test.TestBase;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;


/**
 * Created by nata on 09.07.15.
 */

public class FindPatientByFamilyNameTest extends TestBase {
    private HomePage homePage;
    private HeaderPage headerPage;
    private FindPatientPage findPatientPage;

    public FindPatientByFamilyNameTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Before
    public void setUp() throws Exception {
        loginPage.loginAsAdmin();
        homePage = new HomePage(driver);
        assertPage(homePage);
        headerPage = new HeaderPage(driver);
        findPatientPage = new FindPatientPage(driver);

    }

    @Test
    public void testRecentlyPatientTest() throws Exception {
        homePage.clickOnFindPatientRecord();
        findPatientPage.enterPatient("Smith");
        assertTrue(driver.getPageSource().contains("Smith"));
    }



    @After
    public void tearDown() throws Exception {
        headerPage.clickOnHomeIcon();
        headerPage.logOut();
    }

}
