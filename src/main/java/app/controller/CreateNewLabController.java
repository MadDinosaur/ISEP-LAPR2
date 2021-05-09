package app.controller;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Company;
import app.domain.model.TestType;

import java.util.ArrayList;

public class CreateNewLabController {
    private ClinicalAnalysisLaboratory newlab = new ClinicalAnalysisLaboratory();
    private Company company;

    public void createNewLab() {

    }

    public void setData(String name, String address, long phonenumber, long TIN, String labID) {
        newlab.setName(name);
        newlab.setAddress(address);
        newlab.setPhoneNumber(phonenumber);
        newlab.setTIN(TIN);
        newlab.setLaboratoryID(labID);
    }

    public ArrayList<TestType> getTestTypeList() {
       return company.getTestTypeList();
    }

    public void setTestType(TestType tt){
        newlab.setTestTypeList(tt);
    }
}
