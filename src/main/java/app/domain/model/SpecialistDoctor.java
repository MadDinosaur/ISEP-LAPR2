package app.domain.model;

import app.domain.model.exceptions.InvalidDINException;

public class SpecialistDoctor extends Employee {
    int doctorIndexNumber;

    public SpecialistDoctor(String id, OrganizationRole role, String name, String address, String email, String phoneNumber, String soc, String DIN) {
        super(id, role, name, address, email, phoneNumber, soc);
        setDIN(DIN);
    }

    private void validateDIN(String DIN) {
        int convertedDIN;
        try {
            convertedDIN = Integer.parseInt(DIN); }
        catch (NumberFormatException e) {
            throw new InvalidDINException("DIN must be a number!");
        }
        if (convertedDIN > 999999 || convertedDIN < 100000) throw new InvalidDINException();
    }

    public void setDIN(String DIN) {
        validateDIN(DIN);
        this.doctorIndexNumber = Integer.parseInt(DIN);
    }
}
