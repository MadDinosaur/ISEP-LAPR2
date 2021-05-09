package app.controller;

import junit.framework.TestCase;
import org.junit.Assert;

import java.rmi.registry.Registry;

public class RegisterClientControllerTest extends TestCase {

    public void testCreateClient() {

    }

    public void testSaveClient() {
    }

    public void testSendEmail() {
    }

    public void testSetNameClient() {
        RegisterClientController.setNameClient("ana");
        Assert.assertEquals("ana",RegisterClientController.getName());

    }

    public void testSetCardNumber() {
    }

    public void testSetNhsId() {
    }

    public void testSetTIN() {
    }

    public void testSetPhoneNumber() {
    }

    public void testTestCreateClient() {
    }

    public void testTestSaveClient() {
    }

    public void testTestSendEmail() {
    }
}