package app.domain.model;

import app.domain.model.Exceptions.*;

import java.util.ArrayList;

public class ClinicalAnalysisLaboratory {

    /**
     * Attributes
     */
    private String name;
    private String address;
    private long phonenumber;
    private long tin;
    private String laboratoryID;
    private ArrayList<TestType> testTypeList = new ArrayList<>();

    public ClinicalAnalysisLaboratory() {

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
    public void setTIN(long tin){
        if (validateTIN(tin)) {
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
    public long getTIN() { return tin; }
    public String getLaboratoryID() { return laboratoryID; }
    public ArrayList<TestType> getTestTypeList() { return testTypeList; }

    /**
     * attributes validation
     * @return returns true/false depending if the attribute in question being validated or not
     */
    public boolean validateName(String name) { return (name == null || name.isEmpty() || name.length() > 20); }
    public boolean validateAddress (String address) { return (address == null || address.isEmpty() || address.length() > 30); }
    public boolean validatePhoneNumber (long phonenumber) { return (phonenumber > 10000000000L && phonenumber < 99999999999L); }
    public boolean validateTIN (long TIN) { return (TIN > 1000000000 && TIN < 9999999999L); }
    public boolean validateLaboratoryID (String laboratoryID) { return laboratoryID.length() == 5; }
}
