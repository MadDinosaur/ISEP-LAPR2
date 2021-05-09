package app.controller;

import app.domain.model.ClinicalAnalysisLaboratory;

public class CreateNewLabController {

    public void createNewLab() {

    }

    public void setData(String name, String address, long phonenumber, long TIN, String labID) {
        ClinicalAnalysisLaboratory.setName(name);
        ClinicalAnalysisLaboratory.setAddress(address);
        ClinicalAnalysisLaboratory.setPhoneNumber(phonenumber);
        ClinicalAnalysisLaboratory.setTIN(TIN);
        ClinicalAnalysisLaboratory.setLaboratoryID(labID);
    }
}
