package app.domain.model;

import app.domain.adapter.BiggestContiguousSubSequenceAlgorithm;
import app.domain.model.Exceptions.InvalidLaboratoryIDException;
import app.domain.model.Exceptions.UnassignedExternalModuleException;
import app.domain.store.*;
import auth.AuthFacade;
import auth.domain.model.Email;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

import static com.nhs.report.Report2NHS.writeUsingFileWriter;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    /**
     * Designation of the company
     */
    private final String designation;

    /**
     * List of Clinical Analysis Laboratories in the company
     */
    private final List<ClinicalAnalysisLaboratory> clinicalAnalysisLaboratoryLst = new ArrayList<>();

    /**
     * AuthFacade of the System
     */
    private final AuthFacade authFacade;

    /**
     * Store with company's employees
     */
    private EmployeeStore employeeStore;

    /**
     * Store with company's clients
     */
    private ClientStore clientStore;

    /**
     * Store with company's organization roles
     */
    private OrgRoleStore orgRoleStore;

    /**
     * Store with company's tests
     */
    private TestStore testStore;

    /**
     * Store with company's test types
     */
    private TestTypeStore testTypeStore = new TestTypeStore();

    /**
     * Store with company's test reports
     */
    private ReportStore reportStore = new ReportStore();

    /**
     * List with company's samples
     */
    private SampleList sampleList;

    /**
     * Number of total tests in company
     */
    private int testNumber;

    /**
     * List with company's categories
     */
    private final List<Category> categoryList = new ArrayList<>(Collections.singleton(new Category("Hemograma", "pistola", "WBC", "toma")));

    /**
     * Random constructor
     */
    private final Random random = new Random();

    /**
     * Company class constructor
     *
     * @param designation Designation of the company
     */
    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();

        desSerialization();

        if(employeeStore == null){
            this.employeeStore = new EmployeeStore();
        }
        if(clientStore == null){
            this.clientStore = new ClientStore();
        }
        if(testStore == null) {
            this.testStore = new TestStore();
        }
        this.orgRoleStore = new OrgRoleStore(authFacade);
        if(sampleList == null){
            this.sampleList = new SampleList();
        }

        this.testNumber = 1;
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
        clientStore.saveClient(new Client("Tomás", (long) 8765432187654324.0, 1234512346, new DateBirth(11, 9, 2001), 1234512349, (long) 12345123458.0, new Email("teste4@gmail.com"), "male"));
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
        //Test mdisc1 = new Test(clientStore.getClientByCardNumber((long) 8765432187654324.0), testTypeHardCoded, testNumberGenerator(), nhsCodeGenerator());
        //mdisc1.setStateOfTestToRegistered();
        testTestHardCoded.saveTestParameterResult(parameter, testTestHardCoded.createTestParameterResult(hb000, "135", "mg"));
        testTestHardCodedDiagnosed.saveTestParameterResult(parameter, testTestHardCodedDiagnosed.createTestParameterResult(hb000, "135", "mg"));
        testTestHardCoded.setStateOfTestToSamplesAnalyzed();
        testTestHardCodedDiagnosed.setStateOfTestToSamplesAnalyzed();
        testTestHardCodedDiagnosed.addReport(new Report("diagnosis", "Report is complete"));
        //testTestHardCodedDiagnosed.validateTest();
        testStore.addTest(testTestHardCodedDiagnosed);
        testStore.addTest(testTestHardCodedRegistered);
        testStore.addTest(testTestHardCoded);
        //END OF HARDCODED THINGS FOR TESTS
    }

    /**
     * Getter for the company designation
     *
     * @return company designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Getter for the company authFacade
     *
     * @return company authFacade
     */
    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    /**
     * Method to create a category
     *
     * @param name
     * @param code
     * @param description
     * @return company authFacade
     */
    public Category createCategory(String name, String code, String description) {
        return new Category(name, code, description );
    }

    /**
     * Method to validate a category
     *
     * @param pc
     * @return boolean of validated
     */
    public boolean validateCategory(Category pc) {
        if (pc == null)
            return false;
        return !this.categoryList.contains(pc);
    }

    public boolean saveCategory(Category pc) {
        if (!validateCategory(pc))
            return false;
        serialization();
        return this.categoryList.add(pc);
    }

    public List<TestType> getTestTypeList() {
        return getTestTypeStore().getTestTypeList();
    }

    /**
     * Getter for the test type store
     *
     * @return employee test type
     */
    public TestTypeStore getTestTypeStore() {
        return testTypeStore;
    }

    /**
     * Getter for the employee store
     *
     * @return employee store
     */
    public EmployeeStore getEmployeeStore() {
        return this.employeeStore;
    }

    /**
     * Getter for the client store
     *
     * @return client store
     */
    public ClientStore getClientStore() {
        return clientStore;
    }

    public List<Category> getCategoryList() {
        return this.categoryList;
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
            serialization();
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
            serialization();
            return true;
        }
        System.out.println("An error has happened during the registration");
        return false;
    }

    /**
     * Generates a user password
     *
     * @return String with password
     */
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

    /**
     * Sends a password to a user
     *
     */
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

    /**
     * Method that creates a test to a client
     *
     * @param client client to be Registered the test
     * @param testType test type with categories and parameters of test
     */
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
        serialization();
        client.addTestToClient(testToClient);
        String[] codes = new String[2];
        codes[0] = generatedTestCode;
        codes[1] = generatedNhsCode;
        return codes;
    }

    /**
     * Method that generates a test number
     *
     * @return test number in a string
     */
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

    /**
     * Method that generates a nhs code
     *
     * @return nhs code in a string
     */
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

    /**
     * Method that validates a nhs code
     * @return boolean of validated
     */
    private boolean validateNhsNumber(String nhsCode) {
        return testStore.validadeTestCode(nhsCode);
    }

    /**
     * Getter for a test number
     *
     * @return test number in string
     */
    public String generateNumberTest() {
        return testNumberGenerator();
    }

    /**
     * Getter for a nhs code
     *
     * @return nhs code in string
     */
    public String generateNhsCodeGenerator() {
        return nhsCodeGenerator();
    }

    /**
     * Method that creates a report for multi linear regression
     *
     * @return string with report
     */
    public String makeMultiLinearRegressionReport(int historicalPoints, String dateCurrentDay, Date dateInitalDay,Date dateDayFinal,double confidence, double significance, String varTest){
        WriteReport writeReport = new WriteReport(testStore,historicalPoints,dateCurrentDay,dateInitalDay,dateDayFinal,confidence,significance,varTest);
        String stringToReport = writeReport.getReport();
        writeUsingFileWriter(stringToReport);
        return stringToReport;
    }

    /**
     * Method that creates a report for simple linear regression
     *
     * @return string with report
     */
    public void makeSimpleLinearRegressionReport(int historicalPoints, String dateCurrentDay, Date dateInitalDay,Date dateCurrentDayFinal, String independentVar,double confidence, double significance,String varTest) throws Exception {
        WriteReport writeReport = new WriteReport(testStore,historicalPoints,dateCurrentDay,dateInitalDay,dateCurrentDayFinal,independentVar, confidence, significance,varTest);
        String stringToReport = writeReport.getReport();
        writeUsingFileWriter(stringToReport);
    }

    /**
     * Method that gets a laboratory by its ID
     *
     * @return clinical analysis laboratory
     */
    public ClinicalAnalysisLaboratory getLabById(String laboratoryID) {
        for (ClinicalAnalysisLaboratory clinicalAnalysisLaboratory : clinicalAnalysisLaboratoryLst) {
            if (clinicalAnalysisLaboratory.getLaboratoryID().equals(laboratoryID)) {
                return clinicalAnalysisLaboratory;
            }
        }
        throw new InvalidLaboratoryIDException("There's no laboratory with such ID " + laboratoryID);
    }



    public BiggestContiguousSubSequenceAlgorithm getBiggestContinuousSumAlgorithm(String code) {
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
                return (BiggestContiguousSubSequenceAlgorithm) oClass.newInstance();
            } else {
                throw new NullPointerException("The return value is null");
            }

        } catch (ClassNotFoundException ex) {
            throw new UnassignedExternalModuleException();
        } catch (Exception ex) {
            throw new UnassignedExternalModuleException("Cannot access algorithm!");
        }
    }
    public void serialization(){
        String filename = "dataSerialized.txt";

        try {

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(employeeStore);
            out.writeObject(clientStore);
            out.writeObject(testStore);
            out.writeObject(reportStore);
            out.writeObject(sampleList);
            out.writeObject(testTypeStore);
            out.writeObject(testNumber);
            out.close();
            file.close();

        }

        catch (IOException ex) {
            System.out.println("IO Exception caught");
        }

    }
    public void desSerialization(){
        String filename = "dataSerialized.txt";
        try {

            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            employeeStore = (EmployeeStore)in.readObject();
            clientStore = (ClientStore)in.readObject();
            testStore = (TestStore)in.readObject();
            reportStore = (ReportStore)in.readObject();
            sampleList = (SampleList)in.readObject();
            testTypeStore = (TestTypeStore)in.readObject();
            testNumber = (int)in.readObject();

            in.close();
            file.close();
        }

        catch (IOException ex) {
        ex.printStackTrace();
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" +
                    " is caught");
        }
    }

}
