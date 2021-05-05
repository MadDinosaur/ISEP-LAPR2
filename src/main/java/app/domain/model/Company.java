package app.domain.model;

import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;
    private ArrayList<TestType> testTypeList;

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
            return false;
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


}
