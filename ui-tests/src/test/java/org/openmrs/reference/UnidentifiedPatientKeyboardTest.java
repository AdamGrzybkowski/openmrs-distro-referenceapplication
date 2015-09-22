package org.openmrs.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.reference.helper.PatientGenerator;
import org.openmrs.reference.helper.TestPatient;
import org.openmrs.reference.page.HeaderPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.PatientDashboardPage;
import org.openmrs.reference.page.RegistrationPage;
import org.openmrs.uitestframework.test.TestBase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tomasz on 22.07.15.
 */
public class UnidentifiedPatientKeyboardTest extends TestBase {
    private HeaderPage headerPage;
    private RegistrationPage registrationPage;
    private HomePage homePage;
    private PatientDashboardPage patientDashboardPage;
    private TestPatient patient;

    public UnidentifiedPatientKeyboardTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Before
    public void setUp() throws Exception {
        headerPage = new HeaderPage(driver);
        assertPage(loginPage);
        loginPage.loginAsClerk();
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        patientDashboardPage = new PatientDashboardPage(driver);
        assertPage(homePage);
    }

    @After
    public void tearDown() throws Exception {
        headerPage.clickOnHomeIcon();
        deletePatient(patient.Uuid);
        waitForPatientDeletion(patient.Uuid);
        headerPage.logOut();
    }

    // Test for RA-472,
    @Ignore//ignored due to inability to check unindentified patient by keyboard
    @Test
    public void registerUnidentifiedPatient() throws InterruptedException {
        homePage.openRegisterAPatientApp();
        patient = PatientGenerator.generateTestPatient();
        registrationPage.enterUnidentifiedPatientByKeyboard(patient);

        assertTrue(registrationPage.getNameInConfirmationPage().contains("--"));
        assertTrue(registrationPage.getGenderInConfirmationPage().contains(patient.gender));

        registrationPage.confirmPatientByKeyboard();
        patient.Uuid = patientIdFromUrl();
        assertPage(patientDashboardPage);	// remember just-registered patient id, so it can be removed.
        assertTrue(driver.getPageSource().contains("UNKNOWN UNKNOWN"));
    }
}
