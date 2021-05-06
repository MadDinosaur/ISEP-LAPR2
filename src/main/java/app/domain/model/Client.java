package app.domain.model;
import auth.domain.model.Email;
import auth.domain.model.Password;
import auth.domain.model.User;

public class Client {

    private User clientUser;
    private String name;
    private int cardNumber;
    private int nhsId;
    private String dateBirth;   //String dateBirth ---> Date dateBirth
    private int TIN;
    private int phoneNumber;
    private Email email;
    private String sex;
    private String SEX_POR_OMISSAO = "No sex assigned";


    public Client(String name, int cardNumber, int nhsId, String dateBirth, int TIN, int phoneNumber, Email email, String sex) {
        Password pwd = new Password(email.getEmail());
        setName(name);
        setCardNumber(cardNumber);
        setNhsId(nhsId);
        setDate(dateBirth);
        setTIN(TIN);
        setPhoneNumber(phoneNumber);
        setSex(sex);
        clientUser = new User(email, pwd, name);
    }

    public Client(String name, int cardNumber, int nhsId, String dateBirth, int TIN, int phoneNumber, Email email) {
        Password pwd = new Password(email.getEmail());
        setName(name);
        setCardNumber(cardNumber);
        setNhsId(nhsId);
        setDate(dateBirth);
        setTIN(TIN);
        setPhoneNumber(phoneNumber);
        setSex(SEX_POR_OMISSAO);
        clientUser = new User(email, pwd, name);
    }

    public void setName(String name) {
        if (name.length() > 35) {
            throw new IllegalArgumentException("Name too long"); //a mudar para controller
        }
        this.name = name;
    }

    public void setCardNumber(int cardNumber) {
        if (!Company.listClients.existsCardNumber(cardNumber)) {
            this.cardNumber = cardNumber;
        }
    }

    public void setNhsId(int nhsId) {
        if (!Company.listClients.existsNhsId(nhsId)) {
            this.nhsId = nhsId;
        }
    }

    public void setDate(String dateBirth) {
        //mudar para date
        this.dateBirth = dateBirth;
    }

    public void setTIN(int TIN) {
        if (!Company.listClients.existsTIN(TIN)) {
            this.TIN = TIN;
        }
    }

    public void setPhoneNumber(int phoneNumber) {
        if (!Company.listClients.existsPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(Email email) {
        if (!Company.listClients.exists(email)) {
            this.email = email;
        }
    }

    //Verify if equal in Company
    public boolean hasEmail(Email email) {
        return this.email.equals(email);
    }

    public boolean hasTIN(int TIN) {
        return this.TIN == TIN;
    }

    public boolean hasCardNumber(int cardNumber) {
        return this.cardNumber == cardNumber;
    }

    public boolean hasNhsID(int nhsId) {
        return this.nhsId == nhsId;
    }

    public boolean hasPhoneNumber(int phoneNumber) {
        return this.phoneNumber == phoneNumber;
    }


}
