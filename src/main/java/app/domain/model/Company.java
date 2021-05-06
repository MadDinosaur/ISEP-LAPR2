package app.domain.model;

import app.domain.store.ClientStore;
import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {
    private String designation;
    private AuthFacade authFacade;
    private ArrayList<TestType> testTypeList;
<<<<<<< HEAD
    public static ClientStore listClients = new ClientStore();
=======
    private List<Category> parameterCategoryList;
>>>>>>> bdd5ab50e7c1a1808b2ea9ec8fbb6122f4395a8e

    public Company(String designation)
    {
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

    public TestType createTestType() {
        return new TestType();
    }

    public CollectionMethod createCollectionMethod() {
        return new CollectionMethod();
    }

    public boolean validate(TestType tt) {
        return tt.validateAttributes();
    }

    public boolean addTestType(TestType tt) {
        if(!tt.validateAttributes() || !validateTestType(tt)) {   //ver isto crlh já me estou a passar
            return false;                                         //vdd
        } else {
            return testTypeList.add(tt);
        }
    }
    public boolean validateTestType(TestType tt) {
        if (testTypeList.contains(tt)) {
            return false;
        } else {
            return true;
        }
    }

    public Category createParameterCategory(String code, String description, String nhsId) {
        return new Category(code, description, nhsId);
    }

    public boolean validateParameterCategory(Category pc) {
        if (pc == null)
            return false;
        return ! this.parameterCategoryList.contains(pc);
    }

    public boolean saveParameterCategory(Category pc) {
        if (!validateParameterCategory(pc))
            return false;
        return this.parameterCategoryList.add(pc);
    }

}
