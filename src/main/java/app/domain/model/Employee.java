package app.domain.model;

import app.domain.model.Exceptions.*;

public class Employee implements User{
    private String id;
    private String name;
    private String address;
    private String email;
    private int phoneNumber;
    private int socCode;
    private OrganizationRole organizationRole;

    public Employee(String id, OrganizationRole role, String name, String address, String email, int phoneNumber, int soc) {
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
        this.phoneNumber = phoneNumber;
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

    private boolean validatePhoneNumber(int phoneNumber) {
        if (phoneNumber > 999999999 || phoneNumber < 100000000 ) return false;
        return true;
    }

    private boolean validateSocCode(int soc) {
        if (soc > 9999 || soc < 1000) return false;
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
