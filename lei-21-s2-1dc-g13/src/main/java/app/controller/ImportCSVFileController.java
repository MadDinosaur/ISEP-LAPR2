package app.controller;

import app.domain.model.Client;
import app.domain.model.exceptions.InvalidEmailException;
import app.domain.model.exceptions.InvalidFileException;
import app.domain.model.exceptions.InvalidNameException;
import app.domain.model.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImportCSVFileController {

    /**
     * Register test controller
     */
    private RegisterTestController registerTestController;

    /**
     * Register client controller
     */
    private RegisterClientController registerClientController;

    /**
     * Register lab controller
     */
    private RegisterNewLabController registerNewLabController = new RegisterNewLabController();

    /**
     * Titles
     */
    private String[] titulos;

    /**
     * Values
     */
    private String[] valores;

    /**
     * Test Code
     */
    private String testCode;

    /**
     * Nhs Code
     */
    private String nhsCode;

    /**
     * Date and Time of register
     */
    private String dateTimeRegister;

    /**
     * Date and time of results
     */
    private String dateTimeResults;

    /**
     * Date and time of report
     */
    private String dateTimeReport;

    /**
     * Date and time of validation
     */
    private String dateTimeValidation;

    /**
     * Client's card number
     */
    private Long cardNumber;

    /**
     * Client's nhs Id
     */
    private Long nhsID;

    /**
     * Client's tin
     */
    private Long tin;

    /**
     * Client's birthday
     */
    private String birthday;

    /**
     * Client's phone number
     */
    private Long phoneNumber;

    /**
     * Client's name
     */
    private String name;

    /**
     * Client's e-mail
     */
    private String email;

    /**
     * Client's address
     */
    private String address;

    /**
     * Client
     */
    private Client client;

    /**
     * Test
     */
    private Test test;

    /**
     * File
     */
    private File file;

    /**
     * Scanner
     */
    private Scanner sc;

    /**
     * Getter for the CSV file
     * @return file
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Setter for the CSV file
     * @param file file
     * @throws FileNotFoundException when the file isn't found
     */
    public void setFile(File file) throws FileNotFoundException {
        validateFile(file);
        this.file = file;
        sc = new Scanner(file);
        this.titulos = sc.nextLine().split(";");
    }

    /**
     * Validates the file inputted
     * @param file file
     */
    private void validateFile(File file) {
        if (!file.getName().contains(".csv")) {
            throw new InvalidFileException();
        }
    }

    /**
     * Checks if the file has a next line
     * @return true or false
     */
    public boolean fileHasNextLine() {
       return (sc.hasNextLine());
    }

    /**
     * Reads the test type code
     */
    public void readTestTypeCode() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("TestType")) {
                String testTypeCode = valores[i];
                registerTestController.setTestTypeByCode(testTypeCode);
            }
        }
    }

    /**
     * Reads the category name
     */
    public void readCategoryName() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Category")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String categoryName = valores[i];
                    registerTestController.setCategoryByName(categoryName);
                }
            }
        }
    }

    /**
     * Reads the parameter code and result
     */
    public void readParameterCodeAndResult() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("HB000")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String parameterCode = titulos[i];
                    String value = valores[i];
                    registerTestController.setTestParameterByParameterCode(parameterCode, value);
                }
            }
            if (titulos[i].equalsIgnoreCase("WBC00")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String parameterCode = titulos[i];
                    String value = valores[i];
                    registerTestController.setTestParameterByParameterCode(parameterCode, value);
                }
            }
            if (titulos[i].equalsIgnoreCase("PLT00")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String parameterCode = titulos[i];
                    String value = valores[i];
                    registerTestController.setTestParameterByParameterCode(parameterCode, value);
                }
            }
            if (titulos[i].equalsIgnoreCase("RBC00")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String parameterCode = titulos[i];
                    String value = valores[i];
                    registerTestController.setTestParameterByParameterCode(parameterCode, value);
                }
            }
            if (titulos[i].equalsIgnoreCase("HDL00")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String parameterCode = titulos[i];
                    String value = valores[i];
                    registerTestController.setTestParameterByParameterCode(parameterCode, value);
                }
            }
            if (titulos[i].equalsIgnoreCase("IgGAN")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String parameterCode = titulos[i];
                    String value = valores[i];
                    registerTestController.setTestParameterByParameterCode(parameterCode, value);
                }
            }
        }
    }

    /**
     * Goes one line forward
     * @return true or false
     */
    public boolean goOneLineForward() {
        if (fileHasNextLine()) {
            this.valores = sc.nextLine().split(";");
        } else {
            return false;
        }
        return true;
    }


    /**
     * Reads the Lab ID
     */
    public void readLabId() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Lab_ID")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String labID = valores[i];
                    //LabID is not used
                }
            }
        }
    }


    /**
     * Reads the test code
     */
    public void readTestCode() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Code")) {
                this.testCode = valores[i];
            }
        }
    }

    /**
     * Reads the nhs code
     */
    public void readNhsCode() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Nhs_Code")) {
                this.nhsCode = valores[i];
            }
        }
    }

    /**
     * Reads the date and time of register
     */
    public void readDateTimeRegister() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Reg_DateHour")) {
                this.dateTimeRegister = valores[i];
            }
        }
    }

    /**
     * Reads the date and time of results
     */
    public void readDateTimeResults() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Chemical_DateHour")) {
                this.dateTimeResults = valores[i];
            }
        }
    }

    /**
     * Reads the date and time of report
     */
    public void readDateTimeReport() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Doctor_DateHour")) {
                this.dateTimeReport = valores[i];
            }
        }
    }

    /**
     * Reads the date and time of validation
     */
    public void readDateTimeValidation() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Validation_DateHour")) {
                this.dateTimeValidation = valores[i];
            }
        }
    }

    /**
     * Creates a test with all the attributes read
     */
    public void createTest() {
        this.test = registerTestController.createTestFromCSV(this.client, this.testCode, this.nhsCode, this.dateTimeRegister, this.dateTimeResults, this.dateTimeReport, this.dateTimeValidation);
        System.out.printf("-----Tests's info-----\nTest Code: %s\nNhs Code: %s\nDate and Time of Register: %s\nDate and Time of Results: %s\nDate and Time of Report: %s\nDate and Time of Validation: %s\n", testCode, nhsCode, dateTimeRegister, dateTimeResults, dateTimeReport, dateTimeValidation);

    }

    /**
     * Saves the respective test
     */
    public void saveTest() {
        if (registerTestController.saveTest(this.test)){
            System.out.println("Test was sucessfully imported!\n------------------------------\n\n");
        }
        this.registerTestController = new RegisterTestController();
    }


    /**
     * Reads the nhs id
     */
    public void readNhsId() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("NHS_Number")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    try {
                        this.nhsID = Long.parseLong(valores[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("The nhs id shouldn't letters.");
                    }
                }
            }
        }
    }

    /**
     * Reads the card number
     */
    public void readCardNumber() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("CitizenCard_Number")) {
                this.cardNumber = Long.parseLong(valores[i]);
            }
        }
    }

    /**
     * Reads the tin
     */
    public void readTin() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("TIN")) {
                this.tin = Long.parseLong(valores[i]);
            }
        }
    }

    /**
     * Reads the birthday
     */
    public void readbirthday() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("BirthDay")) {
                this.birthday = valores[i];
            }
        }
    }

    /**
     * Reads the phone number
     */
    public void readPhoneNumber() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("PhoneNumber")) {
                try {
                    this.phoneNumber = Long.parseLong(valores[i]);
                } catch (NumberFormatException e) {
                    System.out.println("The phone number shouldn't contain letters.");
                }
            }
        }
    }

    /**
     * Reads the name
     */
    public void readName() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Name")) {
                if (valores[i].equals("#REF!")) throw new InvalidNameException();
                this.name = valores[i];
            }
        }
    }

    /**
     * Reads the e-mail
     */
    public void readEmail() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].contains("mail")) {
                if (valores[i].equals("#REF!")) throw new InvalidEmailException();
                this.email = valores[i];
            }
        }
    }

    /**
     * Reads the address
     */
    public void readAddress() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Address")) {
                this.address = valores[i];
            }
        }
    }

    /**
     * Creates a client with all the attributes read
     */
    public void createClient() {
        this.client = registerClientController.createClient(name, cardNumber, nhsID, birthday, tin, phoneNumber, address, email);
        System.out.printf("-----Client's info-----\nName: %s\nCard Number: %s\nNhs Id: %s\nBirthday: %s\nTin: %s\nPhone Number: %s\nE-mail: %s\nAddress: %s\n", name, cardNumber, nhsID, birthday, tin, phoneNumber, email, address);

    }

    /**
     * Saves the respective client
     */
    public void saveClient() {
        if (registerClientController.saveClientFromCSV(client)) {
            System.out.println("Client was successfully created!");
        };

        this.registerClientController = new RegisterClientController();
    }

    /**
     * Creates new controllers
     */
    public void createNewControllers() {
        this.registerClientController = new RegisterClientController();
        this.registerTestController = new RegisterTestController();
    }
}
