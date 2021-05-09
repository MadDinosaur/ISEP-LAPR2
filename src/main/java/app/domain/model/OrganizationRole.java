package app.domain.model;

import java.security.InvalidParameterException;

public class OrganizationRole {
    private String id;
    private String description;
    private Company organization;

    public OrganizationRole(String id, String description, Company organization) {
        this.id = id;
        this.description = description;
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    /**
     *
     * @param name
     * @param address
     * @param phoneNumber
     * @param email
     * @param DIN special parameter for SpecialistDoctor class. If omitted (defined as null) does the same as
     *            createEmployee(String, String, String, String, int, String).
     * @return
     */
    public Employee createEmployee(String name, String address, String phoneNumber, String email, String soc, String DIN) {
        String id = organization.getEmployeeStore().generateEmployeeId(name);
        if (this.description.equalsIgnoreCase("Specialist Doctor"))
            return new SpecialistDoctor(id, this, name, address, email, phoneNumber, soc,  DIN);
        else
            return new Employee(id, this, name, address, email, phoneNumber, soc);
    }

    public Employee createEmployee(String name, String address, String phoneNumber, String email, String soc) {
        if (this.description.equalsIgnoreCase("SpecialistDoctor"))
            throw new InvalidParameterException("Specialist Doctor must have a Doctor Index Number!");
        return new Employee(id, this, name, address, email, phoneNumber, soc);
    }

    @Override
    public String toString() {
        return this.description;
    }
}
