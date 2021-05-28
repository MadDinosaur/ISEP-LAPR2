package app.domain.model;

import app.domain.shared.Constants;
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


    private ClientStore clientStore;
    private OrgRoleStore orgRoleStore;
    private TestStore testStore;
    private TestTypeStore testTypeStore = new TestTypeStore();
    private ReportStore reportStore;

    private List<Test> registeredTests = testStore.getRegisteredTests();
    private TestTypeStore tts = new TestTypeStore();
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
        //Para testes
        clientStore.saveClient(new Client("Joni",(long) 1234567812345678.0,1234512345,new DateBirth(24,12,2002),1234512345,(long)12345123456.0,new Email("teste@gmail.com"),"male"));
        // this.testTypeStore.addTestType(new TestType("123",));

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

    /**
     *
     * getter used on US8
     */
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

    public TestStore getTestStore() { return this.testStore;}

    public List<Test> getUnusedTests() { return this.registeredTests; }

    public ReportStore getReportStore() {
        return this.reportStore;
    }

    public boolean saveEmployeeAsUser(Employee e) {
        String pwd = generateUserPassword();
        if (authFacade.addUserWithRole(e.getName(), e.getEmail(), pwd, e.getRoleId())) {
            sendUserPassword(e.getEmail(), pwd);
            return true;
        }
        return false;
    }
    public boolean saveClientAsUser(Client client,String email){
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
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    private void sendUserPassword(String email, String pwd) {
        File file = null;
        try {
            file = new File("SMS-Emails\\Register_" + email + ".txt");

            FileWriter myWriter = new FileWriter("SMS-Emails\\Register" + email + ".txt");
            myWriter.write("You are now registered in " + getDesignation() + "'s application. " +
                    "Your password is: " + pwd);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred on file " + file.getName());
        }
    }


}
