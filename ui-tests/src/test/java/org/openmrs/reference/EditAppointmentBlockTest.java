

package org.openmrs.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.reference.page.*;
import org.openmrs.uitestframework.test.TestBase;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by nata on 08.07.2015.
 */
public class EditAppointmentBlockTest extends TestBase {
    private HomePage homePage;
    private HeaderPage headerPage;
    private AppointmentBlocksPage appointmentBlocksPage;

    public EditAppointmentBlockTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
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
    public void editAppointmentBlockTest() throws Exception {
        appointmentBlocksPage.goToAppointmentBlock();
        appointmentBlocksPage.selectLocation("Outpatient Clinic");
        appointmentBlocksPage.clickOnCurrentDay();
        appointmentBlocksPage.selectLocationBlock("Outpatient Clinic");
        appointmentBlocksPage.enterService("derm");
        appointmentBlocksPage.clickOnSave();
        assertNotNull("Dermatology", appointmentBlocksPage.CURRENT_DAY);
        appointmentBlocksPage.findBlock();
        appointmentBlocksPage.clickOnEdit();
        appointmentBlocksPage.enterProvider("Jake Smith");
        appointmentBlocksPage.clickOnServiceDelete();
        appointmentBlocksPage.enterService("onco");
        appointmentBlocksPage.clickOnSave();
        assertNotNull("Oncology", appointmentBlocksPage.CURRENT_DAY);
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

