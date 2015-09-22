package org.openmrs.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.reference.page.*;
import org.openmrs.uitestframework.test.TestBase;
import static org.junit.Assert.assertTrue;


/**
 * Created by nata on 20.07.15.
 */
public class XSSOnPhoneNumberFieldTest extends TestBase {
    private HomePage homePage;
    private HeaderPage headerPage;
    private ManageFormsPage manageForm;
    private PatientDashboardPage patientDashboardPage;
    private RegistrationPage registrationPage;

    public XSSOnPhoneNumberFieldTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Before
    public void setUp() throws Exception {
        loginPage.loginAsAdmin();
        homePage = new HomePage(driver);
        assertPage(homePage);
        headerPage = new HeaderPage(driver);
        manageForm = new ManageFormsPage(driver);
        patientDashboardPage = new PatientDashboardPage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage.goToActiveVisitPatient();
    }

    @Test
    @Ignore
    public void  XSSOnPhoneNumberFieldTest() throws Exception {
        patientDashboardPage.clickOnShowContact();
        patientDashboardPage.clickOnEditContact();
        registrationPage.clickOnPhoneNumberEdit();
        registrationPage.clearPhoneNumber();
        registrationPage.enterPhoneNumber("<script>alert(0)</script>");
        registrationPage.clickOnConfirmEdit();
        assertTrue(driver.getPageSource().contains("Must be a valid phone number (with +, -, numbers or parentheses)"));
        registrationPage.clearPhoneNumber();
        registrationPage.enterPhoneNumber("111111111");
        registrationPage.clickOnConfirmEdit();
        registrationPage.confirmPatient();
        patientDashboardPage.waitForVisitLinkHidden();
        assertTrue(driver.getPageSource().contains("Saved changes in contact info for"));

    }
    @After
    public void tearDown() throws Exception {
        headerPage.clickOnHomeIcon();
        headerPage.logOut();
    }
}

