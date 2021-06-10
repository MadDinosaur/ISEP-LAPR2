package app.mappers.dto;

public class ClientDTO {
    protected String password;
    private String name;
    private String cardNumber;
    private String nhsId;
    private String dateBirth;
    private String TIN;
    private String phoneNumber;
    private String email;
    private String sex;

    public ClientDTO(String name, String cardNumber, String nhsId, String dateBirth, String TIN, String phoneNumber, String email, String password, String sex) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.nhsId = nhsId;
        this.dateBirth = dateBirth;
        this.TIN = TIN;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getNhsId() {
        return nhsId;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public String getTIN() {
        return TIN;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }

    public String getSex() {
        return sex;
    }
}
