package app.domain.model;

import app.domain.model.Exceptions.*;

public class ClinicalAnalysisLaboratory {
    private String name;
    private String address;
    private long phonenumber;
    private long TIN;
    private String laboratoryID;

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
*/

    public ClinicalAnalysisLaboratory(String name, String address, long phonenumber, long TIN, String laboratoryID) {
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
        this.TIN = TIN;
        this.laboratoryID = laboratoryID;
    }

    public void setName(String name) {
        if (name == null || name.length() > 20) {
            throw new InvalidNameException();
        } else {
            this.name = name;
        }
    }

    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new InvalidAddressException();
        } else {
            this.address = address;
        }
    }

    public void setPhoneNumber(long phonenumber) {
        if (phonenumber > 10000000000L && phonenumber < 99999999999L) {
            this.phonenumber = phonenumber;
        } else {
            throw new InvalidPhoneNumberException();
        }
    }

    public void setTIN(long TIN){
        if (TIN > 1000000000 && TIN < 9999999999L) {
            this.TIN = TIN;
        } else {
            throw new InvalidTINException();
        }
    }

    public void setLaboratoryID(String laboratoryID){
        if (laboratoryID == null || laboratoryID.length() != 5) {
            throw new InvalidLaboratoryIDException();
        } else {
            this.laboratoryID = laboratoryID;
        }
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public long getPhonenumber() { return phonenumber; }
    public long getTIN() { return TIN; }
    public String getLaboratoryID() { return laboratoryID; }
}
