package org.openmrs.reference;

/**
 * Created by nata on 16.07.15.
 */
import org.junit.*;
import static org.junit.Assert.*;

import org.openmrs.reference.page.*;
import org.openmrs.uitestframework.test.TestBase;


public class DeleteLocationTest extends TestBase {
    private HomePage homePage;
    private PatientDashboardPage patientDashboardPage;
    private HeaderPage headerPage;
    private LocationPage locationPage;
    private SettingPage settingPage;

    public DeleteLocationTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Before
    public void setUp() throws Exception {

        loginPage.loginAsAdmin();
        homePage = new HomePage(driver);
        assertPage(homePage);
        patientDashboardPage = new PatientDashboardPage(driver);
        headerPage = new HeaderPage(driver);
        locationPage = new LocationPage(driver);
        settingPage = new SettingPage(driver);
        homePage.goToAdministration();


    }

    @Test
    public void deleteLocationTest() throws Exception {
        locationPage.clickOnManageLocation();
        if(locationPage.locationPresent()) {
            locationPage.checkLocation();
            locationPage.clickOnDelete();
        }
        else {
        locationPage.clickOnAddLocation();
        locationPage.enterName("psychiatric hospital");
        locationPage.chooseTags("Admission Location");
        locationPage.clickOnTags();
        locationPage.clickOnSaveLocation();
        locationPage.checkLocation();
        locationPage.clickOnDelete();
        settingPage.waitForMessage();
        assertTrue(driver.getPageSource().contains("deleted"));}

    }

    @After
    public void tearDown() throws Exception {
        headerPage.clickOnHomeLink();
        headerPage.logOut();
    }

}
