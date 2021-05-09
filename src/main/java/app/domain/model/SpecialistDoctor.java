package app.domain.model;

import app.domain.model.Exceptions.InvalidDINException;

public class SpecialistDoctor extends Employee {
    int doctorIndexNumber;

    public SpecialistDoctor(String id, OrganizationRole role, String name, String address, String email, int phoneNumber, int soc, int DIN) {
        super(id, role, name, address, email, phoneNumber, soc);
        setDIN(DIN);
    }

    private void validateDIN(int DIN) {
        if (DIN > 999999 || DIN < 100000) throw new InvalidDINException();
    }

    public void setDIN(int DIN) {
        validateDIN(DIN);
        this.doctorIndexNumber = DIN;
    }
}
