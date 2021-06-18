package app.controller;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Company;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

public class RegisterNewLabController {
    private ClinicalAnalysisLaboratory newlab = new ClinicalAnalysisLaboratory();
    private Company company;
    public RegisterNewLabController() { this.company = App.getInstance().getCompany(); }

    public void setData(String name, String address, long phonenumber, long TIN, String labID) {
        newlab.setName(name);
        newlab.setAddress(address);
        newlab.setPhoneNumber(phonenumber);
        newlab.setTin(TIN);
        newlab.setLaboratoryID(labID);
    }

    public List<TestType> getTestTypeList() {
       return company.getTestTypeList();
    }

    public void setTestType(TestType tt){
        newlab.setTestTypeList(tt);
    }
}
