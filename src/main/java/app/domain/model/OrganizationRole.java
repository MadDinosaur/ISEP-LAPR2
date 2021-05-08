package app.domain.model;

import java.security.InvalidParameterException;

public class OrganizationRole {
    private String id;
    private String description;

    OrganizationRole(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    /**
     *
     * @param id
     * @param role
     * @param name
     * @param address
     * @param phoneNumber
     * @param email
     * @param DIN special parameter for SpecialistDoctor class. If omitted (defined as null) does the same as
     *            createEmployee(String, String, String, String, int, String).
     * @return
     */
    public Employee createEmployee(String id, String role, String name, String address, int phoneNumber, String email, int soc, int DIN) {
        if (this.description.equalsIgnoreCase("Specialist Doctor"))
            return new SpecialistDoctor(id, this, name, address, email, phoneNumber, soc,  DIN);
        else
            return new Employee(id, this, name, address, email, phoneNumber, soc);
    }

    public Employee createEmployee(String id, String role, String name, String address, int phoneNumber, String email, int soc) {
        if (this.description.equalsIgnoreCase("SpecialistDoctor"))
            throw new InvalidParameterException("Specialist Doctor must have a Doctor Index Number!");
        return new Employee(id, this, name, address, email, phoneNumber, soc);
    }
}
