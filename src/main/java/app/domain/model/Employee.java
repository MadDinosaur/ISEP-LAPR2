package app.domain.model;

import app.domain.model.Exceptions.*;
import auth.domain.model.User;

public class Employee {
    private String id;
    private String name;
    private String address;
    private String email;
    private int phoneNumber;
    private int socCode;
    private OrganizationRole organizationRole;

    public Employee(String id, OrganizationRole role, String name, String address, String email, String phoneNumber, String soc) {
        if (!validateName(name)) throw new InvalidNameException();
        if (!validateAddress(address)) throw new InvalidAddressException();
        if (!validateEmail(email)) throw new InvalidEmailException();
        if (!validatePhoneNumber(phoneNumber)) throw new InvalidPhoneNumberException();
        if (!validateSocCode(soc)) throw new InvalidSocCodeException();

        this.id = id;
        this.organizationRole = role;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = Integer.parseInt(phoneNumber);
        this.socCode = Integer.parseInt(soc);
    }

    private boolean validateName(String name) {
        if (name == null || name.isEmpty()) return false;
        return true;
    }

    private boolean validateAddress(String address) {
        if (address == null || address.isEmpty()) return false;
        return true;
    }

    private boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) return false;
        if (!email.contains("@")) return false;
        return true;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        int convertedPhoneNumber;
        try {
            convertedPhoneNumber = Integer.parseInt(phoneNumber); }
        catch (NumberFormatException e) {
            throw new InvalidPhoneNumberException("Phone number must be a number!");
        }
        if (convertedPhoneNumber > 999999999 || convertedPhoneNumber < 100000000 ) return false;
        return true;
    }

    private boolean validateSocCode(String soc) {
        int convertedSocCode;
        try {
            convertedSocCode = Integer.parseInt(soc); }
        catch (NumberFormatException e) {
            throw new InvalidPhoneNumberException("SOC code must be a number!");
        }
        if (convertedSocCode > 9999 || convertedSocCode < 1000) return false;
        return true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getRoleId() {
        return organizationRole.getId();
    }
}
