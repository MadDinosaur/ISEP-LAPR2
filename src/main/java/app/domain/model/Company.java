package app.domain.model;

import app.domain.adapter.BiggestContiguousSumAlgorithm;
import app.domain.model.Exceptions.InvalidLaboratoryIDException;
import app.domain.model.Exceptions.UnassignedExternalModuleException;
import app.domain.store.*;
import auth.AuthFacade;
import auth.domain.model.Email;
import org.apache.commons.lang3.StringUtils;
import static com.nhs.report.Report2NHS.writeUsingFileWriter;

import java.io.*;
import java.util.*;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private final List<ClinicalAnalysisLaboratory> clinicalAnalysisLaboratoryLst = new ArrayList<>();
    private final String designation;
    private final AuthFacade authFacade;
    private final EmployeeStore employeeStore;
    private final ClientStore clientStore;
    private final OrgRoleStore orgRoleStore;
    private final TestStore testStore;
    private final TestTypeStore testTypeStore = new TestTypeStore();
    private final ReportStore reportStore = new ReportStore();
    private final Random random = new Random();
    private SampleList sampleList;
    private int testNumber;
    private final List<Category> categoryList = new ArrayList<>(Collections.singleton(new Category("Hemograma", "pistola", "WBC", "toma")));


    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.employeeStore = new EmployeeStore();
        this.clientStore = new ClientStore();
        this.orgRoleStore = new OrgRoleStore(authFacade);
        this.testStore = new TestStore();
        this.testNumber = 1;
        this.sampleList = new SampleList();




        //HARDCODED THINGS FOR TESTS
        Employee testEmployee = new Employee("testEmployee", orgRoleStore.getOrganizationRole("Receptionist"), "nameEmployee", "house", "testEmployee@gmail.com", "123456789", "1234");
        Client testClient = new Client("testClient", (long) 8765432187654321.0, 1234512347, new DateBirth(24, 12, 2002), 1234512345, (long) 12345123457.0, new Email("testClient@gmail.com"), "male");
        //add client and Receptionist as a user
        saveClientAsUser(testClient, testClient.getEmail().toString());
        saveEmployeeAsUser(testEmployee);
        //
        clientStore.saveClient(new Client("Maria", (long) 1234567812345678.0, 1234512345, new DateBirth(24, 12, 2002), 1234512346, (long) 12345123456.0, new Email("teste@gmail.com"), "male"));
        clientStore.saveClient(new Client("João", (long) 8765432187654322.0, 1234512345, new DateBirth(24, 12, 2002), 1234512347, (long) 12345123456.0, new Email("teste2@gmail.com"), "male"));
        clientStore.saveClient(new Client("Pedro", (long) 8765432187654323.0, 1234512346, new DateBirth(11, 9, 2001), 1234512348, (long) 12345123457.0, new Email("teste3@gmail.com"), "male"));
        CollectionMethod collectionMethodTest = new CollectionMethod("test Colection");
        Category categoryTest = new Category("Hemogram", "HEM00", "Hemogram Description", "toma");
        String hb000 = "HB000";
        Parameter parameter = new Parameter("par2345", hb000, "test f234");
        Parameter parameter2 = new Parameter("par2346", "HB001", "test f235");
        categoryTest.saveParameter(parameter);
        categoryTest.saveParameter(parameter2);
        Category cholesterol = new Category("Cholesterol", "code", "description", "nhsid");
        Parameter cholesterolParameter = new Parameter("HDL00", "HDL00", "description");
        cholesterol.saveParameter(cholesterolParameter);
        List<Category> categoryList1 = new ArrayList<>(List.of(categoryTest, cholesterol));
        TestType testTypeHardCoded = new TestType("Blood", "test Of Test", collectionMethodTest, categoryList1);
        testTypeStore.addTestType(testTypeHardCoded);
        Test testTestHardCoded = new Test(clientStore.getClientByCardNumber((long) 8765432187654321.0), testTypeHardCoded, testNumberGenerator(), nhsCodeGenerator());
        Test testTestHardCodedRegistered = new Test(clientStore.getClientByCardNumber((long) 8765432187654322.0), testTypeHardCoded, testNumberGenerator(), nhsCodeGenerator());
        Test testTestHardCodedDiagnosed = new Test(clientStore.getClientByCardNumber((long) 8765432187654323.0), testTypeHardCoded, testNumberGenerator(), nhsCodeGenerator());
        testTestHardCoded.saveTestParameterResult(parameter, testTestHardCoded.createTestParameterResult(hb000, "135", "mg"));
        testTestHardCodedDiagnosed.saveTestParameterResult(parameter, testTestHardCodedDiagnosed.createTestParameterResult(hb000, "135", "mg"));
        testTestHardCoded.setStateOfTestToSamplesAnalyzed();
        testTestHardCodedDiagnosed.setStateOfTestToSamplesAnalyzed();
        testTestHardCodedDiagnosed.addReport(new Report("diagnosis", "Report is complete"));
        testStore.addTest(testTestHardCodedDiagnosed);
        testStore.addTest(testTestHardCodedRegistered);
        testStore.addTest(testTestHardCoded);
        //END OF HARDCODED THINGS FOR TESTS

    }


    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public Category createCategory(String name, String code, String description) {
        return new Category(name, code, description );
    }

    public boolean validateCategory(Category pc) {
        if (pc == null)
            return false;
        return !this.categoryList.contains(pc);
    }

    public boolean saveCategory(Category pc) {
        if (!validateCategory(pc))
            return false;
        return this.categoryList.add(pc);
    }

    public List<TestType> getTestTypeList() {
        return getTestTypeStore().getTestTypeList();
    }

    public TestTypeStore getTestTypeStore() {
        return testTypeStore;
    }

    public List<Category> getCategoryList() {
        return this.categoryList;
    }

    public EmployeeStore getEmployeeStore() {
        return this.employeeStore;
    }

    public ClientStore getClientStore() {
        return clientStore;
    }

    /**
     * Returns the Company Org Role Store
     *
     * @return OrgRoleStore
     */
    public OrgRoleStore getOrgRoleStore() {
        return this.orgRoleStore;
    }

    /**
     * Returns a TestStore
     *
     * @return TestStore
     */
    public TestStore getTestStore() {
        return this.testStore;
    }

    /**
     * Returns a ReportStore
     *
     * @return ReportStore
     */
    public ReportStore getReportStore() {
        return this.reportStore;
    }

    public SampleList getSampleStore() {
        return this.sampleList;
    }

    /**
     * Saves the employee as a user
     *
     * @return (in)success of the operation
     */
    public boolean saveEmployeeAsUser(Employee e) {
        String pwd = generateUserPassword();
        if (authFacade.addUserWithRole(e.getName(), e.getEmail(), pwd, e.getRoleId())) {
            sendUserPassword(e.getEmail(), pwd);
            return true;
        }
        return false;
    }

    /**
     * Saves the client as a user
     *
     * @return (in)success of the operation
     */
    public boolean saveClientAsUser(Client client, String email) {
        String pwd = generateUserPassword();
        if (authFacade.addUserWithRole(client.getName(), email, pwd, client.getOrganizationRole())) {
            clientStore.saveClient(client);
            sendUserPassword(email, pwd);
            System.out.println("Client has been successfully registered");
            return true;
        }
        System.out.println("An error has happened during the registration");
        return false;
    }

    public String generateUserPassword() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;


        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    private void sendUserPassword(String email, String pwd) {
        File file = null;
        FileWriter myWriter = null;
        try {
            file = new File("SMS-Emails\\Register_" + email + ".txt");

            myWriter = new FileWriter("SMS-Emails\\Register_" + email + ".txt");
            myWriter.write("You are now registered in " + getDesignation() + "'s application. " +
                    "Your password is: " + pwd);
        } catch (IOException e) {
            System.out.println("An error occurred on file " + file.getName());
        } finally {
            try {
                assert myWriter != null;
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method that sends an email notification to a client
     * @param client
     * @param msg
     */
    public void sendNotification(Client client, String msg) {
        File file = null;
        FileWriter myWriter = null;
        try {
            file = new File("SMS-Emails\\TestResultsDiagnosis_" + client.getEmail() + ".txt");
            myWriter = new FileWriter("SMS-Emails\\TestResultsDiagnosis_" + client.getEmail() + ".txt");
            myWriter.write(msg);
        } catch (IOException e) {
            System.out.println("An error occurred on file " + file.getName());
        } finally {
            try {
                assert myWriter != null;
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String[] createTestToClient(Client client, TestType testType) {
        String generatedTestCode = testNumberGenerator();
        boolean nhsCodeIsValidated;
        String generatedNhsCode;
        do {
            generatedNhsCode = nhsCodeGenerator();
            nhsCodeIsValidated = validateNhsNumber(generatedNhsCode);
        } while (!nhsCodeIsValidated);

        Test testToClient = new Test(client, testType, generatedTestCode, generatedNhsCode);
        testStore.addTest(testToClient);
        client.addTestToClient(testToClient);
        String[] codes = new String[2];
        codes[0] = generatedTestCode;
        codes[1] = generatedNhsCode;
        return codes;
    }

    private String testNumberGenerator() {
        StringBuilder generatedTestCode = new StringBuilder();
        String testCodeString = String.valueOf(testNumber);
        int lengthTestNumber = 12;
        while (generatedTestCode.length() + testCodeString.length() < lengthTestNumber) {
            generatedTestCode.append("0");
        }
        generatedTestCode.append(testCodeString);
        testNumber++;
        return generatedTestCode.toString();
    }

    private String nhsCodeGenerator() {
        StringBuilder generatedNhsCode = new StringBuilder();
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int lengthNhsCode = 12;
        while (lengthNhsCode-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            generatedNhsCode.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return generatedNhsCode.toString();
    }

    private boolean validateNhsNumber(String nhsCode) {
        return testStore.validadeTestCode(nhsCode);
    }

    public String generateNumberTest() {
        return testNumberGenerator();
    }

    public String generateNhsCodeGenerator() {
        return nhsCodeGenerator();
    }

    public void makeMultiLinearRegressionReport(int historicalPoints, String dateCurrentDay, Date dateInitalDay,Date dateDayFinal){
        System.out.println("");
        WriteReport writeReport = new WriteReport(testStore,historicalPoints,dateCurrentDay,dateInitalDay,dateDayFinal);
        String stringToReport = writeReport.getReport();
        writeUsingFileWriter(stringToReport);
    }
    public void makeSimpleLinearRegressionReport(int historicalPoints, String dateCurrentDay, Date dateInitalDay,Date dateCurrentDayFinal, String independentVar) throws Exception {
        WriteReport writeReport = new WriteReport(testStore,historicalPoints,dateCurrentDay,dateInitalDay,dateCurrentDayFinal,independentVar);
        String stringToReport = writeReport.getReport();
        writeUsingFileWriter(stringToReport);
    }



    public ClinicalAnalysisLaboratory getLabById(String laboratoryID) {
        for (ClinicalAnalysisLaboratory clinicalAnalysisLaboratory : clinicalAnalysisLaboratoryLst) {
            if (clinicalAnalysisLaboratory.getLaboratoryID().equals(laboratoryID)) {
                return clinicalAnalysisLaboratory;
            }
        }
        throw new InvalidLaboratoryIDException("There's no laboratory with such ID " + laboratoryID);
    }



    public BiggestContiguousSumAlgorithm getBiggestContinuousSumAlgorithm(String code) {
        Class<?> oClass = null;

        try {
            switch (code) {
                case "BruteForce":
                    oClass = Class.forName("app.domain.adapter.BruteForceAdapter");
                    break;
                case "Benchmark":
                    oClass = Class.forName("app.domain.adapter.BenchmarkAdapter");
                    break;
                default:
                    System.out.println("No algorithm found!");
            }
            if (oClass != null) {
                return (BiggestContiguousSumAlgorithm) oClass.newInstance();
            } else {
                throw new NullPointerException("The return value is null");
            }

        } catch (ClassNotFoundException ex) {
            throw new UnassignedExternalModuleException();
        } catch (Exception ex) {
            throw new UnassignedExternalModuleException("Cannot access algorithm!");
        }
    }
}
