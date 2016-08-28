/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.reference;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.reference.helper.TestPatient;
import org.openmrs.reference.page.*;
import org.openmrs.uitestframework.test.TestBase;
import org.junit.*;
import org.openmrs.uitestframework.test.TestData;

import static org.junit.Assert.assertTrue;


/**
 */

public class MergePatientByNameTest extends ReferenceApplicationTestBase {

    private TestData.PatientInfo testPatient1;
    private TestData.PatientInfo testPatient2;

    @Before
    public void setUp() throws Exception {
        testPatient1 = createTestPatient();
        testPatient2 = createTestPatient();
    }

    @Test
    public void mergePatientByNameTest() throws Exception {
        DataManagementPage dataManagementPage = homePage.goToDataMagament();
        MergePatientsPage mergePatientsPage = dataManagementPage.goToMegrePatient();
        mergePatientsPage.setPatient1(testPatient1.id);
        mergePatientsPage.setPatient2(testPatient2.id);
        mergePatientsPage.clickOnContinue();
        mergePatientsPage.clickOnMergePatient();
    }



    @After
    public void tearDown() throws Exception {
        deletePatient(testPatient1.uuid);
        deletePatient(testPatient2.uuid);
    }

}
