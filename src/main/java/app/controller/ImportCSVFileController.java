package app.controller;

import app.domain.model.Exceptions.*;
import app.domain.model.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class ImportCSVFileController {


    private RegisterTestController registerTestController = new RegisterTestController();

    private String[] titulos;
    private String[] valores;
    private String testCode;
    private String nhsCode;
    private String dateTimeRegister;
    private String dateTimeResults;
    private String dateTimeReport;
    private String dateTimeValidation;
    private Test test;
    private File file;

    private Scanner sc;

    public ImportCSVFileController() {

    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) throws FileNotFoundException {
        validateFile(file);
        this.file = file;
        sc = new Scanner(file);
        this.titulos = sc.nextLine().split(";");
        this.valores = sc.nextLine().split(";");
        System.out.println(valores.length);
    }

    private void validateFile(File file) {
        if (!file.getName().contains(".csv")) {
            throw new InvalidFileException();
        }
    }

    public void readTestTypeCode() {
        for (int i = 0; i < this.titulos.length; i++) {
            if (titulos[i].equalsIgnoreCase("TestType")) {
                String testTypeCode = valores[i];
                registerTestController.setTestTypeByCode(testTypeCode);
            }
        }
    }

    public void readCategoryName() {
        for (int i = 0; i < titulos.length; i++) {
            if (titulos[i].equalsIgnoreCase("Category")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String categoryName = valores[i];
                    try {
                        registerTestController.setCategoryByName(categoryName);
                    } catch (InvalidCategoryException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void readParameterCodeAndResult() {
        for (int i = 0; i < titulos.length; i++) {
            if (titulos[i].equalsIgnoreCase("HB000")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String parameterCode = titulos[i];
                    String value = valores[i];
                    try {
                        registerTestController.setTestParameterByParameterCode(parameterCode, value);
                    } catch (InvalidParameterException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (titulos[i].equalsIgnoreCase("WBC00")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String parameterCode = titulos[i];
                    String value = valores[i];
                    try {
                        registerTestController.setTestParameterByParameterCode(parameterCode, value);
                    } catch (InvalidParameterException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (titulos[i].equalsIgnoreCase("PLT00")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String parameterCode = titulos[i];
                    String value = valores[i];
                    try {
                        registerTestController.setTestParameterByParameterCode(parameterCode, value);
                    } catch (InvalidParameterException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (titulos[i].equalsIgnoreCase("RBC00")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String parameterCode = titulos[i];
                    String value = valores[i];
                    try {
                        registerTestController.setTestParameterByParameterCode(parameterCode, value);
                    } catch (InvalidParameterException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (titulos[i].equalsIgnoreCase("HDL00")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String parameterCode = titulos[i];
                    String value = valores[i];
                    try {
                        registerTestController.setTestParameterByParameterCode(parameterCode, value);
                    } catch (InvalidParameterException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (titulos[i].equalsIgnoreCase("IgGAN")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String parameterCode = titulos[i];
                    String value = valores[i];
                    try {
                        registerTestController.setTestParameterByParameterCode(parameterCode, value);
                    } catch (InvalidParameterException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public boolean goOneLineForward() {
        if (sc.hasNextLine()) {
            this.valores = sc.nextLine().split(";");
        } else {
            return false;
        }
        return true;
    }

    public void readNhsId() {
        for (int i = 0; i < titulos.length; i++) {
            if (titulos[i].equalsIgnoreCase("NHS_Number")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    Long nhsID = Long.parseLong(valores[i]);
                    try {
                        registerTestController.setClientByNhsID(nhsID);
                    } catch (InvalidClientException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void readLabId() {
        for (int i = 0; i < titulos.length; i++) {
            if (titulos[i].equalsIgnoreCase("Lab_ID")) {
                if (!valores[i].equalsIgnoreCase("NA")) {
                    String labID = valores[i];
                    try {
                        registerTestController.setLabById(labID);
                    } catch (InvalidLaboratoryIDException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public void readTestCode() {
        for (int i = 0; i < titulos.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Code")) {
                this.testCode = valores[i];
            }
        }
    }

    public void readNhsCode() {
        for (int i = 0; i < titulos.length; i++) {
            if (titulos[i].equalsIgnoreCase("Nhs_Code")) {
                this.nhsCode = valores[i];
            }
        }
    }

    public void readDateTimeRegister() {
        for (int i = 0; i < titulos.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Reg_DateHour")) {
                this.dateTimeRegister = valores[i];
            }
        }
    }

    public void readDateTimeResults() {
        for (int i = 0; i < titulos.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Chemical_DateHour")) {
                this.dateTimeResults = valores[i];
            }
        }
    }

    public void readDateTimeReport() {
        for (int i = 0; i < titulos.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Doctor_DateHour")) {
                this.dateTimeReport = valores[i];
            }
        }
    }

    public void readDateTimeValidation() {
        for (int i = 0; i < titulos.length; i++) {
            if (titulos[i].equalsIgnoreCase("Test_Validation_DateHour\n")) {
                this.dateTimeValidation = valores[i];
            }
        }
    }

    public void createTest() {
        this.test = registerTestController.createTestFromCSV(this.testCode, this.nhsCode, this.dateTimeRegister, this.dateTimeResults, this.dateTimeReport, this.dateTimeValidation);
    }

    public void saveTest() {
        registerTestController.saveTest(this.test);
        this.registerTestController = new RegisterTestController();
    }
}
