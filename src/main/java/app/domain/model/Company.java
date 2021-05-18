package app.domain.model;

import app.domain.store.EmployeeStore;
import app.domain.store.OrgRoleStore;
import app.domain.store.TestTypeStore;
import auth.AuthFacade;
import auth.domain.model.Email;
import org.apache.commons.lang3.StringUtils;

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
    private EmployeeStore employeeStore = new EmployeeStore();
    private OrgRoleStore orgRoleStore = new OrgRoleStore();
    private List<Category> parameterCategoryList;
    private TestTypeStore tts = new TestTypeStore();
    private List<Category> categoryList = new ArrayList<Category>(Collections.singleton(new Category("Hemograma", "pistola", "WBC", "toma")));

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();

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
        return tts;
    }

    public List<Category> getCategoryList() {
        return this.categoryList;
    }

    public EmployeeStore getEmployeeStore() {
        return this.employeeStore;
    }

    public OrgRoleStore getOrgRoleStore() { return this.orgRoleStore;}

    public boolean saveEmployeeAsUser(Employee e) {
        String pwd = generateUserPassword();
        return authFacade.addUserWithRole(e.getName(), e.getEmail(), pwd, e.getRoleId());
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
}
