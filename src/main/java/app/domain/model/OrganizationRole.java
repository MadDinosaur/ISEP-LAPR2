package app.domain.model;

import java.security.InvalidParameterException;

public class OrganizationRole {
    private String id;
    private String description;

    public OrganizationRole(String id, String description) {
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
     * @param name
     * @param address
     * @param phoneNumber
     * @param email
     * @param DIN special parameter for SpecialistDoctor class. If omitted (defined as null) does the same as
     *            createEmployee(String, String, String, String, int, String).
     * @return
     */
    public Employee createEmployee(String id, String name, String address, String phoneNumber, String email, String soc, String DIN) {
        if (this.description.equalsIgnoreCase("Specialist Doctor"))
            return new SpecialistDoctor(id, this, name, address, email, phoneNumber, soc,  DIN);
        else
            return new Employee(id, this, name, address, email, phoneNumber, soc);
    }

    public Employee createEmployee(String id, String name, String address, String phoneNumber, String email, String soc) {
        if (this.description.equalsIgnoreCase("SpecialistDoctor"))
            throw new InvalidParameterException("Specialist Doctor must have a Doctor Index Number!");
        return new Employee(id, this, name, address, email, phoneNumber, soc);
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (o.getClass() != this.getClass()) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        OrganizationRole c = (OrganizationRole) o;

        // Compare the data members and return accordingly
        return c.id.equalsIgnoreCase(this.id) && c.description.equalsIgnoreCase(this.description);
    }

    @Override
    public String toString() {
        return this.description;
    }
}
