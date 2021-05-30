package app.domain.model;

import app.domain.store.*;
import auth.AuthFacade;
import auth.domain.model.Email;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;
    private EmployeeStore employeeStore;
    private int testNumber;

    private ClientStore clientStore;
    private OrgRoleStore orgRoleStore;
    private TestStore testStore = new TestStore();
    private TestTypeStore testTypeStore = new TestTypeStore();
    private ReportStore reportStore = new ReportStore();
    private SampleList sampleList;
    private Random random = new Random();

    private List<Category> parameterCategoryList = new ArrayList<>();

    private List<Category> categoryList = new ArrayList<Category>(Collections.singleton(new Category("Hemograma", "pistola", "WBC", "toma")));

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

        //HARDCODED THINGS FOR TESTES
        clientStore.saveClient(new Client("Teste",(long) 8765432187654321.0,1234512347,new DateBirth(24,12,2002),1234512345,(long)12345123457.0,new Email("teste50@gmail.com"),"male"));
        clientStore.saveClient(new Client("Joni",(long)  1234567812345678.0,1234512345,new DateBirth(24,12,2002),1234512346,(long)12345123456.0,new Email("teste@gmail.com"),"male"));
        clientStore.saveClient(new Client("Joni",(long)  8765432187654322.0,1234512345,new DateBirth(24,12,2002),1234512347,(long)12345123456.0,new Email("teste@gmail.com"),"male"));
        CollectionMethod collectionMethodTest = new CollectionMethod("test Colection");
        Category categoryTest = new Category("Hemogram", "HEM00", "Hemogram Description", "toma");
        Parameter parameter = new Parameter("par2345","HB000","test f234");
        categoryTest.saveParameter(parameter);
        List<Category> categoryList = new ArrayList<Category>(Collections.singleton(categoryTest));
        TestType testTypeHardCoded = new TestType("Blood","test Of Test",collectionMethodTest,categoryList);
        testTypeStore.addTestType(testTypeHardCoded);
        Test testTestHardCoded = new Test(clientStore.getClientByCardNumber((long)8765432187654321.0),testTypeHardCoded,testNumberGenerator(),nhsCodeGenerator());
        Test testTestHardCodedRegistered = new Test(clientStore.getClientByCardNumber((long)8765432187654322.0),testTypeHardCoded,testNumberGenerator(),nhsCodeGenerator());
        testTestHardCoded.saveTestParameterResult(parameter, testTestHardCoded.createTestParameterResult("HB000", "135", "mg"));
        testTestHardCoded.setStateOfTestToSamplesAnalyzed();
        testStore.addTest(testTestHardCodedRegistered);
        testStore.addTest(testTestHardCoded);

    }


    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public Category createCategory(String code, String description, String nhsId) {
        return new Category(code, description, nhsId);
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

    public ArrayList<TestType> getTestTypeList() {
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

    public OrgRoleStore getOrgRoleStore() { return this.orgRoleStore;}

    /**
     * Returns a TestStore
     * @return TestStore
     */
    public TestStore getTestStore() { return this.testStore;}

    /**
     * Returns a ReportStore
     * @return ReportStore
     */
    public ReportStore getReportStore() {
        return this.reportStore;
    }

    public SampleList getSampleStore() { return this.sampleList; }

    public boolean saveEmployeeAsUser(Employee e) throws IOException {
        String pwd = generateUserPassword();
        if (authFacade.addUserWithRole(e.getName(), e.getEmail(), pwd, e.getRoleId())) {
            sendUserPassword(e.getEmail(), pwd);
            return true;
        }
        return false;
    }
    public boolean saveClientAsUser(Client client,String email) throws IOException {
        String pwd = generateUserPassword();
        if (authFacade.addUserWithRole(client.getName(), email, pwd, client.getOrganizationRole())) {
            clientStore.saveClient(client);
            sendUserPassword(email, pwd);
            System.out.println("Client has been successfully registered"); //__
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

    private void sendUserPassword(String email, String pwd) throws IOException {
        File file = null;
        FileWriter myWriter = null;
        try {
            file = new File("SMS-Emails\\Register_" + email + ".txt");

            myWriter = new FileWriter("SMS-Emails\\Register" + email + ".txt");
            myWriter.write("You are now registered in " + getDesignation() + "'s application. " +
                    "Your password is: " + pwd);
        } catch (IOException e) {
            System.out.println("An error occurred on file " + file.getName());
        } finally {
            myWriter.close();
        }
    }

    public void sendNotification(Client client, String msg) throws IOException {
        File file = null;
        FileWriter myWriter = null;
        try {
            file = new File("SMS-Emails\\TestResultsDiagnosis_" + client.getEmail() + ".txt");
            myWriter = new FileWriter("SMS-Emails\\TestResultsDiagnosis_" + client.getEmail() + ".txt");
            myWriter.write(msg);
        } catch (IOException e) {
            System.out.println("An error occurred on file " + file.getName());
        }finally {
            myWriter.close();
        }
    }

    public String[] createTestToClient(Client client, TestType testType) {
        String generatedTestCode = testNumberGenerator();
        boolean nhsCodeIsValidated;
        String generatedNhsCode;
        do {
            generatedNhsCode = nhsCodeGenerator();
            nhsCodeIsValidated = validateNhsNumber(generatedNhsCode);
        }while (!nhsCodeIsValidated);

        Test testToClient = new Test(client,testType,generatedTestCode,generatedNhsCode);
        testStore.addTest(testToClient);
        String[] codes=  new String[2];
        codes[0]= generatedTestCode;
        codes[1]= generatedNhsCode;
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

    private boolean validateNhsNumber(String nhsCode){
        return testStore.validadeTestCode(nhsCode);
    }

    public String generateNumberTest(){
        return testNumberGenerator();
    }

    public String generateNhsCodeGenerator(){return  nhsCodeGenerator();}
}
