package app.domain.model;

import app.domain.model.Exceptions.*;

import java.util.ArrayList;

public class ClinicalAnalysisLaboratory {
    private String name;
    private String address;
    private long phonenumber;
    private long TIN;
    private String laboratoryID;
    private ArrayList<TestType> testTypeList;

/*
    private static final String NO_NAME = "";
    private static final String NO_ADDRESS = "";
    private static final long NO_PHONENUMBER = 0;
    private static final long NO_TIN = 0;
    private static final String NO_LABORATORYID = "";

    public ClinicalAnalysisLaboratory() {
        this.name = NO_NAME;
        this.address = NO_ADDRESS;
        this.phonenumber = NO_PHONENUMBER;
        this.TIN = NO_TIN;
        this.laboratoryID = NO_LABORATORYID;
    }

    public ClinicalAnalysisLaboratory(String name, String address, long phonenumber, long TIN, String laboratoryID) {
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
        this.TIN = TIN;
        this.laboratoryID = laboratoryID;
    }
*/

    public ClinicalAnalysisLaboratory() {}

    /**
     *
     * setters
     */
    public void setName(String name) {
        if (!(validateName(name))) {
            this.name = name;
        } else {
            throw new InvalidNameException();
        }
    }

    public void setAddress(String address) {
        if (!(validateAddress(address))) {
            this.address = address;
        } else {
            throw new InvalidAddressException();
        }
    }

    public void setPhoneNumber(long phonenumber) {
        if (validatePhoneNumber(phonenumber)) {
            this.phonenumber = phonenumber;
        } else {
            throw new InvalidPhoneNumberException();
        }
    }

    public void setTIN(long TIN){
        if (validateTIN(TIN)) {
            this.TIN = TIN;
        } else {
            throw new InvalidTINException();
        }
    }

    public void setLaboratoryID(String laboratoryID){
        if (!(validateLaboratoryID(laboratoryID))) {
            this.laboratoryID = laboratoryID;
        } else {
            throw new InvalidLaboratoryIDException();
        }
    }

    public void setTestTypeList(ArrayList<TestType> testTypeList) {
        this.testTypeList = testTypeList;
    }

    /**
     *
     * getters
     */
    public String getName() { return name; }
    public String getAddress() { return address; }
    public long getPhonenumber() { return phonenumber; }
    public long getTIN() { return TIN; }
    public String getLaboratoryID() { return laboratoryID; }
    public ArrayList<TestType> getTestTypeList() { return testTypeList; }

    /**
     *
     * parameter validation according to us8 acceptance criteria
     */
    public boolean validateName(String name) { return (name == null || name.isEmpty() || name.length() > 20); }
    public boolean validateAddress (String address) { return (address == null || address.isEmpty() || address.length() > 30); }
    public boolean validatePhoneNumber (long phonenumber) { return (phonenumber > 10000000000L && phonenumber < 99999999999L); }
    public boolean validateTIN (long TIN) { return (TIN > 1000000000 && TIN < 9999999999L); }
    public boolean validateLaboratoryID (String laboratoryID) { return (laboratoryID == null || laboratoryID.length() != 5); }
}
