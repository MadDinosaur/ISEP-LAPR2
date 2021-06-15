package app.domain.model;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Exceptions.*;
import org.junit.Assert;
import org.junit.Test;

public class ClinicalAnalysisLaboratoryTest {
    @Test(expected = InvalidNameException.class)
    public void testEmptyName() {
        String name = "";
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        newLab.setName(name);
    }

    @Test(expected = InvalidNameException.class)
    public void testOver20CharacterName() {
        String name = "123456789012345678901";
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        newLab.setName(name);
    }

    @Test(expected = InvalidAddressException.class)
    public void testEmptyAddress() {
        String address = "";
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        newLab.setAddress(address);
    }

    @Test(expected = InvalidAddressException.class)
    public void testOver20CharacterAddress() {
        String address = "1234567890123456789012345678901";
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        newLab.setAddress(address);
    }

    @Test(expected = InvalidPhoneNumberException.class)
    public void testPhoneNumber() {
        long phonenumber = 0;
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        newLab.setPhoneNumber(phonenumber);
    }

    @Test(expected = InvalidPhoneNumberException.class)
    public void testOverLimitPhoneNumber() {
        long phonenumber = 100000000000L;
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        newLab.setPhoneNumber(phonenumber);
    }
    @Test(expected = InvalidTINException.class)
    public void testTIN() {
        long TIN = 0;
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        newLab.setTIN(TIN);
    }

    @Test(expected = InvalidTINException.class)
    public void testOverLimitTIN() {
        long TIN = 10000000000L;
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        newLab.setTIN(TIN);
    }

    @Test(expected = InvalidLaboratoryIDException.class)
    public void testEmptyLabID() {
        String labID = "";
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        newLab.setLaboratoryID(labID);
    }

    @Test(expected = InvalidLaboratoryIDException.class)
    public void testInvalidLabID() {
        String labID = "a1b2c3";
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        newLab.setLaboratoryID(labID);
    }

    @Test
    public void testNameValidation() {
        String name = "name";
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        boolean validation = newLab.validateName(name);
        Assert.assertFalse(validation);
    }

    @Test
    public void testAddressValidation() {
        String address = "address";
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        boolean validation = newLab.validateAddress(address);
        Assert.assertFalse(validation);
    }

    @Test
    public void testPhoneNumberValidation() {
        long phoneNumber = 12312312312L;
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        boolean validation = newLab.validatePhoneNumber(phoneNumber);
        Assert.assertTrue(validation);
    }

    @Test
    public void testTINValidation() {
        long tin = 1231231231;
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        boolean validation = newLab.validateTIN(tin);
        Assert.assertTrue(validation);
    }

    @Test
    public void testLabIDValidation() {
        String labID = "lab55";
        ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();
        boolean validation = newLab.validateLaboratoryID(labID);
        Assert.assertTrue(validation);
    }
}

