package org.openmrs.reference;

/**
 * Created by nata on 25.06.15.
 */
import org.junit.*;
import static org.junit.Assert.*;

import org.openmrs.reference.page.HeaderPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.PatientDashboardPage;
import org.openmrs.uitestframework.test.TestBase;


public class AdmitToInpatientTest extends TestBase {
    private HomePage homePage;
    private PatientDashboardPage patientDashboardPage;
    private HeaderPage headerPage;

    public AdmitToInpatientTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Before
    public void setUp() throws Exception {

        loginPage.loginAsAdmin();
        homePage = new HomePage(driver);
        assertPage(homePage);
        patientDashboardPage = new PatientDashboardPage(driver);
        headerPage = new HeaderPage(driver);
        homePage.goToActiveVisitPatient();


    }

    @Ignore//ignored due to adding choose provider functionality
    @Test
    public void admitToInpatientTest() throws Exception {

        if(patientDashboardPage.inpatientPresent()) {
            patientDashboardPage.exitFromInpatient();

        }
        patientDashboardPage.clickOnAdmitToInpatient();
        patientDashboardPage.selectLocation("Unknown Location");
        assertTrue(patientDashboardPage.location().getText().contains("Unknown Location"));

        patientDashboardPage.clickOnSave();
        assertTrue(patientDashboardPage.visitLink().getText().contains("Entered Admission"));
        patientDashboardPage.exitFromInpatient();
        patientDashboardPage.waitForVisitLink();
        assertTrue(patientDashboardPage.visitLink().getText().contains("Entered Discharge"));

    }

    @After
    public void tearDown() throws Exception {
        headerPage.clickOnHomeIcon();
        headerPage.logOut();
    }

}
