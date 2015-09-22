

package org.openmrs.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.reference.page.*;
import org.openmrs.uitestframework.test.TestBase;
import static org.junit.Assert.assertNotNull;


/**
 * Created by nata on 08.07.2015.
 */
public class DeleteAppointmentBlockTest extends TestBase {
    private HomePage homePage;
    private HeaderPage headerPage;
    private AppointmentBlocksPage appointmentBlocksPage;

    public DeleteAppointmentBlockTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Before
    public void setUp() throws Exception {
        loginPage.loginAsAdmin();
        homePage = new HomePage(driver);
        assertPage(homePage);
        headerPage = new HeaderPage(driver);
        appointmentBlocksPage = new AppointmentBlocksPage(driver);
    }

    @Ignore//ignored due to possible application logout
    @Test
    public void deleteAppointmentBlockTest() throws Exception {
        appointmentBlocksPage.goToAppointmentBlock();
        appointmentBlocksPage.selectLocation("Outpatient Clinic");
        appointmentBlocksPage.clickOnCurrentDay();
        appointmentBlocksPage.selectLocationBlock("Outpatient Clinic");
        appointmentBlocksPage.enterService("derm");
        appointmentBlocksPage.enterProvider("Super User");
        appointmentBlocksPage.clickOnSave();
        assertNotNull("Dermatology", appointmentBlocksPage.CURRENT_DAY);
        appointmentBlocksPage.findBlock();
        appointmentBlocksPage.clickOnDelete();
        appointmentBlocksPage.clickOnClose();
        appointmentBlocksPage.clickOnConfirmDelete();
    }
    @After
    public void tearDown() throws Exception {
        headerPage.clickOnHomeIcon();
        headerPage.logOut();
    }
}

