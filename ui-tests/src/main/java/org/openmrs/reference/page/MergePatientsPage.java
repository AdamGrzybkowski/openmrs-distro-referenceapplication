package org.openmrs.reference.page;

import org.openmrs.uitestframework.page.Page;
import org.openqa.selenium.By;

/**
 */
public class MergePatientsPage extends Page{

    private final static By ID_PATIENT_1 = By.id("patient1-text");
    private final static By ID_PATIENT_2 = By.id("patient2-text");
    private final static By CONTINUE = By.id("confirm-button");
    private final static By MERGE_PATIENT = By.id("second-patient");

    public MergePatientsPage(Page parent) {
        super(parent);
    }

    public void clickOnContinue(){
        waitForElementToBeEnabled(CONTINUE);
        clickOn(CONTINUE);
    }

    public void clickOnMergePatient(){
        waitForElement(MERGE_PATIENT);
        clickOn(MERGE_PATIENT);
    }


    public void setPatient1(String patient1){
        setText(ID_PATIENT_1, patient1);
    }

    public void setPatient2(String patient2){
        setText(ID_PATIENT_2, patient2);
    }

    @Override
    public String getPageUrl() {
        return "/datamanagement/mergePatients.page";
    }
}
