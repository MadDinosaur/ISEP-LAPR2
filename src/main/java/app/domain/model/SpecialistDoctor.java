package app.domain.model;

public class SpecialistDoctor extends Employee {
    int doctorIndexNumber;

    public SpecialistDoctor(String name, String address, String email, int phoneNumber, int DIN) {
        super(name, address, email, phoneNumber, "Specialist Doctor");
        setDIN(DIN);
    }

    private void validateDIN(int DIN) {

    }

    public void setDIN(int DIN) {
        validateDIN(DIN);
        this.doctorIndexNumber = DIN;
    }
}
