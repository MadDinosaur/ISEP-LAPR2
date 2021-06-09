package app.controller;

import app.domain.model.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportCSVFileController {


    private RegisterTestController registerTestController = new RegisterTestController();

    private List<String> categoryNameLst = new ArrayList<>();
    private List<String> parameterCodeLst = new ArrayList<>();
    private List<String> resultLst = new ArrayList<>();
    private String[] titulos;
    private String[] valores;
    private String testCode;
    private String nhsCode;
    private String dateTimeRegister;
    private String dateTimeResults;
    private String dateTimeReport;
    private String dateTimeValidation;
    private Test test;

    private static Scanner sc;

    public ImportCSVFileController() throws FileNotFoundException {
        sc = new Scanner(new File("C:\\Users\\tomas\\Desktop\\teste.csv"));
        this.titulos = sc.nextLine().split(";");
        this.valores = sc.nextLine().split(";");
    }

    public void readTestTypeCode() {
        for (int i = 0; i < titulos.length; i++) {
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
                    registerTestController.setCategoryByName(categoryName);
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

    public void goOneLineForward() {
        this.valores = sc.nextLine().split(";");
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
