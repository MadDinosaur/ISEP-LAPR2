package app.domain.model;

import app.domain.model.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class ClinicalAnalysisLaboratory {

    /**
     * Attributes
     */
    private String name;
    private String address;
    private long phonenumber;
    private long tin;
    private String laboratoryID;
    private final List<TestType> testTypeList = new ArrayList<>();
    
    /**
     *
     */
    public ClinicalAnalysisLaboratory() {
        // Empty constructor due to previous problems
    }

    /**
     * set laboratory name
     * @param name name of laboratory
     */
    public void setName(String name) {
        if (!(validateName(name))) {
            this.name = name;
        } else {
            throw new InvalidNameException();
        }
    }

    /**
     * set address
     * @param address laboratory's address
     */
    public void setAddress(String address) {
        if (!(validateAddress(address))) {
            this.address = address;
        } else {
            throw new InvalidAddressException();
        }
    }

    /**
     * set phonenumber
     * @param phonenumber laboratory's phonenumber
     */
    public void setPhoneNumber(long phonenumber) {
        if (validatePhoneNumber(phonenumber)) {
            this.phonenumber = phonenumber;
        } else {
            throw new InvalidPhoneNumberException();
        }
    }

    /**
     * set tin
     * @param tin tax identification number
     */
    public void setTin(long tin){
        if (validateTin(tin)) {
            this.tin = tin;
        } else {
            throw new InvalidTINException();
        }
    }

    /**
     * set labID
     * @param laboratoryID laboratory's ID
     */
    public void setLaboratoryID(String laboratoryID){
        if ((validateLaboratoryID(laboratoryID))) {
            this.laboratoryID = laboratoryID;
        } else {
            throw new InvalidLaboratoryIDException();
        }
    }

    /**
     * set types of test performed by the lab
     * @param tt test type
     */
    public void setTestTypeList(TestType tt) {
        this.testTypeList.add(tt);
    }

    /**
     * getters of each attribute
     * @return returns the wanted attribute
     */
    public String getName() { return name; }
    public String getAddress() { return address; }
    public long getPhonenumber() { return phonenumber; }
    public long getTin() { return tin; }
    public String getLaboratoryID() { return laboratoryID; }
    public List<TestType> getTestTypeList() { return testTypeList; }

    /**
     * attributes validation
     * @return returns true/false depending if the attribute in question is valid or not
     */
    public boolean validateName(String name) { return (name == null || name.isEmpty() || name.length() > 20); }
    public boolean validateAddress (String address) { return (address == null || address.isEmpty() || address.length() > 30); }
    public boolean validatePhoneNumber (long phonenumber) { return (phonenumber > 10000000000L && phonenumber < 99999999999L); }
    public boolean validateTin (long tin) { return (tin > 1000000000 && tin < 9999999999L); }
    public boolean validateLaboratoryID (String laboratoryID) { return laboratoryID.length() == 5; }
}
