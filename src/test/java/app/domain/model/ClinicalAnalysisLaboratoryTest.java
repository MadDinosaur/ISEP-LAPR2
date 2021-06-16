package app.domain.model;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Exceptions.*;
import org.junit.Assert;
import org.junit.Test;

public class ClinicalAnalysisLaboratoryTest {
    ClinicalAnalysisLaboratory newLab = new ClinicalAnalysisLaboratory();

    @Test(expected = InvalidNameException.class)
    public void testEmptyName() {
        String name = "";
        newLab.setName(name);
    }

    @Test(expected = InvalidNameException.class)
    public void testOver20CharacterName() {
        String name = "123456789012345678901";
        newLab.setName(name);
    }

    @Test(expected = InvalidAddressException.class)
    public void testEmptyAddress() {
        String address = "";
        newLab.setAddress(address);
    }

    @Test(expected = InvalidAddressException.class)
    public void testOver20CharacterAddress() {
        String address = "1234567890123456789012345678901";
        newLab.setAddress(address);
    }

    @Test(expected = InvalidPhoneNumberException.class)
    public void testPhoneNumber() {
        long phonenumber = 0;
        newLab.setPhoneNumber(phonenumber);
    }

    @Test(expected = InvalidPhoneNumberException.class)
    public void testOverLimitPhoneNumber() {
        long phonenumber = 100000000000L;
        newLab.setPhoneNumber(phonenumber);
    }
    @Test(expected = InvalidTINException.class)
    public void testTin() {
        long tin = 0;
        newLab.setTin(tin);
    }

    @Test(expected = InvalidTINException.class)
    public void testOverLimitTin() {
        long tin = 10000000000L;
        newLab.setTin(tin);
    }

    @Test(expected = InvalidLaboratoryIDException.class)
    public void testEmptyLabID() {
        String labID = "";
        newLab.setLaboratoryID(labID);
    }

    @Test(expected = InvalidLaboratoryIDException.class)
    public void testInvalidLabID() {
        String labID = "a1b2c3";
        newLab.setLaboratoryID(labID);
    }

    @Test
    public void testNameValidation() {
        String name = "name";
        boolean validation = newLab.validateName(name);
        Assert.assertFalse(validation);
    }

    @Test
    public void testAddressValidation() {
        String address = "address";
        boolean validation = newLab.validateAddress(address);
        Assert.assertFalse(validation);
    }

    @Test
    public void testPhoneNumberValidation() {
        long phoneNumber = 12312312312L;
        boolean validation = newLab.validatePhoneNumber(phoneNumber);
        Assert.assertTrue(validation);
    }

    @Test
    public void testTINValidation() {
        long tin = 1231231231;
        boolean validation = newLab.validateTin(tin);
        Assert.assertTrue(validation);
    }

    @Test
    public void testLabIDValidation() {
        String labID = "lab55";
        boolean validation = newLab.validateLaboratoryID(labID);
        Assert.assertTrue(validation);
    }

    @Test
    public void testGetName() {
        String name = "name";
        newLab.setName(name);
        String getName = newLab.getName();
        Assert.assertEquals(name, getName);
    }

    @Test
    public void testGetAddress() {
        String address = "address";
        newLab.setAddress(address);
        String getAddress = newLab.getAddress();
        Assert.assertEquals(address, getAddress);
    }

    @Test
    public void testGetPhoneNumber() {
        long phonenumber = 12312312312L;
        newLab.setPhoneNumber(phonenumber);
        long getPhoneNumber = newLab.getPhonenumber();
        Assert.assertEquals(phonenumber, getPhoneNumber);
    }

    @Test
    public void testGetTin() {
        long tin = 1231231231;
        newLab.setTin(tin);
        long getTIN = newLab.getTin();
        Assert.assertEquals(tin, getTIN);
    }

    @Test
    public void testGetLabID() {
        String labID = "lab50";
        newLab.setLaboratoryID(labID);
        String getLabID = newLab.getLaboratoryID();
        Assert.assertEquals(labID, getLabID);
    }

    @Test
    public void testValidations() {
        String name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String name1 = "name";
        String address = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String address1 = "address";
        long phonenumber = 123;
        long phonenumber1 = 12312312312L;
        long tin = 123;
        long tin1 = 1231231231;
        String labId = "xD";
        String labId1 = "lab50";

        boolean nameValidation = newLab.validateName(name);
        boolean name1Validation = newLab.validateName(name1);
        boolean addressValidation = newLab.validateAddress(address);
        boolean address1Validation = newLab.validateAddress(address1);
        boolean phonenumberValidation = newLab.validatePhoneNumber(phonenumber);
        boolean phonenumber1Validation = newLab.validatePhoneNumber(phonenumber1);
        boolean tinValidation = newLab.validateTin(tin);
        boolean tin1Validation = newLab.validateTin(tin1);
        boolean labIdValidation = newLab.validateLaboratoryID(labId);
        boolean labId1Validation = newLab.validateLaboratoryID(labId1);

        Assert.assertTrue(nameValidation);
        Assert.assertTrue(addressValidation);
        Assert.assertFalse(phonenumberValidation);
        Assert.assertFalse(tinValidation);
        Assert.assertFalse(labIdValidation);

        Assert.assertFalse(name1Validation);
        Assert.assertFalse(address1Validation);
        Assert.assertTrue(phonenumber1Validation);
        Assert.assertTrue(tin1Validation);
        Assert.assertTrue(labId1Validation);

    }
}

