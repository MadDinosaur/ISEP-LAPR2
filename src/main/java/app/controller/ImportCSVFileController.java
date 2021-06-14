package app.controller;

import app.domain.model.Client;
import app.domain.model.Exceptions.*;
import app.domain.model.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImportCSVFileController {


    private RegisterTestController registerTestController = new RegisterTestController();
    private RegisterClientController registerClientController = new RegisterClientController();

    private String[] titulos;
    private String[] valores;
    private String testCode;
    private String nhsCode;
    private String dateTimeRegister;
    private String dateTimeResults;
    private String dateTimeReport;
    private String dateTimeValidation;
    private Long cardNumber;
    public Long nhsID;
    private Long tin;
    private String birthday;
    private Long phoneNumber;
    private String name;
    private String email;
    private String address;
    private Client client;
    private Test test;
    private File file;

    private Scanner sc;

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) throws FileNotFoundException {
        validateFile(file);
        this.file = file;
        sc = new Scanner(file);
        this.titulos = sc.nextLine().split(";");
        this.valores = sc.nextLine().split(";");
    }

    private void validateFile(File file) {
        if (!file.getName().contains(".csv")) {
            throw new InvalidFileException();
        }
    }

    public boolean fileHasNextLine() {
        if (sc.hasNextLine()) {
            if (sc.nextLine().split(",").length == 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public void readTestTypeCode() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("TestType")) {
                String testTypeCode = valores[i];
                System.out.println(testTypeCode);
                registerTestController.setTestTypeByCode(testTypeCode);
            }
        }
    }

    public void readCategoryName() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Category")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String categoryName = valores[i];
                    System.out.println(categoryName);
                    registerTestController.setCategoryByName(categoryName);
                }
            }
        }
    }

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
                    System.out.println(parameterCode);
                    String value = valores[i];
                    System.out.println(value);
                    registerTestController.setTestParameterByParameterCode(parameterCode, value);
                }
            }
        }
    }

    public boolean goOneLineForward() {
        if (fileHasNextLine()) {
            this.valores = sc.nextLine().split(";");
        } else {
            return false;
        }
        return true;
    }


    public void readLabId() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Lab_ID")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String labID = valores[i];
                    registerTestController.setLabById(labID);
                }
            }
        }
    }


    public void readTestCode() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Code")) {
                this.testCode = valores[i];
            }
        }
    }

    public void readNhsCode() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Nhs_Code")) {
                this.nhsCode = valores[i];
            }
        }
    }

    public void readDateTimeRegister() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Reg_DateHour")) {
                this.dateTimeRegister = valores[i];
            }
        }
    }

    public void readDateTimeResults() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Chemical_DateHour")) {
                this.dateTimeResults = valores[i];
            }
        }
    }

    public void readDateTimeReport() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Doctor_DateHour")) {
                this.dateTimeReport = valores[i];
            }
        }
    }

    public void readDateTimeValidation() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Validation_DateHour")) {
                this.dateTimeValidation = valores[i];
            }
        }
    }

    public void createTest() {
        System.out.println("Client was created sucessfully!");
        this.test = registerTestController.createTestFromCSV(this.client, this.testCode, this.nhsCode, this.dateTimeRegister, this.dateTimeResults, this.dateTimeReport, this.dateTimeValidation);
    }

    public void saveTest() {
        registerTestController.saveTest(this.test);
        System.out.println("Test was imported");
        this.registerTestController = new RegisterTestController();
    }


    public void readNhsId() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("NHS_Number")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    this.nhsID = Long.parseLong(valores[i]);
                }
            }
        }
    }

    public void readCardNumber() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("CitizenCard_Number")) {
                this.cardNumber = Long.parseLong(valores[i]);
            }
        }
    }

    public void readTin() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("TIN")) {
                this.tin = Long.parseLong(valores[i]);
            }
        }
    }

    public void readbirthday() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("BirthDay")) {
                this.birthday = valores[i];
            }
        }
    }

    public void readPhoneNumber() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("PhoneNumber")) {
                this.phoneNumber = Long.parseLong(valores[i]);
            }
        }
    }

    public void readName() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Name")) {
                this.name = valores[i];
            }
        }
    }

    public void readEmail() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Email")) {
                this.email = valores[i];
            }
        }
    }

    public void readAddress() {
        for (int i = 0; i < valores.length; i++) {
            if (titulos[i].equalsIgnoreCase("Address")) {
                this.address = valores[i];
            }
        }
    }

    public void createClient() {
        System.out.println(name);
        System.out.println(cardNumber);
        System.out.println(nhsID);
        System.out.println(birthday);
        System.out.println(tin);
        System.out.println(phoneNumber);
        System.out.println(email);
        System.out.println(address);
        this.client = registerClientController.createClient(name, cardNumber, nhsID, birthday, tin, phoneNumber, address, email);
    }

    public void saveClient() {
        registerClientController.saveClientFromCSV(client);
        this.registerClientController = new RegisterClientController();
    }
}