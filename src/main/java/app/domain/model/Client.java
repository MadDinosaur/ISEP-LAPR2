package app.domain.model;
import auth.domain.model.Email;
import auth.domain.model.User;

public class Client {

    private User clientUser;
    private String name;
    private long cardNumber;
    private long nhsId;
    private DateBirth dateBirth;
    private long TIN;
    private long phoneNumber;
    private Email email;
    private String sex;
    private String SEX_POR_OMISSAO = "No sex assigned";

    public Client(String name, long cardNumber, long nhsId, DateBirth dateBirth, long TIN, long phoneNumber, Email email, String sex) {
        setName(name);
        setCardNumber(cardNumber);
        setNhsId(nhsId);
        setDateBirth(dateBirth);
        setTIN(TIN);
        setPhoneNumber(phoneNumber);
        setSex(sex);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardNumber(long cardNumber) {

        this.cardNumber = cardNumber;
    }

    public void setNhsId(long nhsId) {
        this.nhsId = nhsId;

    }

    public void setDateBirth(DateBirth dateBirth) {
        this.dateBirth = dateBirth;
    }

    public void setTIN(long TIN) {

        this.TIN = TIN;

    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(Email email) {

        this.email = email;
    }

    public Email getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }




}
