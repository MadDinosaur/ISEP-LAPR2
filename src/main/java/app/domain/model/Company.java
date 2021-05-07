package app.domain.model;

import app.domain.store.ClientStore;
import app.domain.store.TestTypeStore;
import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {
    private String designation;
    private AuthFacade authFacade;
    private TestTypeStore tts;
    private List<Category> categoryList;

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
     * getter used on US8
     */
    public ArrayList<TestType> getTestTypeList() {
        return getTestTypeStore().getTestTypeList();
    }

    public TestTypeStore getTestTypeStore() {
        return tts;
    }

    public boolean addClient(Client client) {
        String pass = ".";
        authFacade.addUser(client.getName(), client.getEmail().toString(), pass);
        return true;
    }

    public List<Category> getCategoryList() {
        return this.categoryList;
    }
}
