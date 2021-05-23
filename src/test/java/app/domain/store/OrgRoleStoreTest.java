package app.domain.store;

import app.domain.model.OrganizationRole;
import auth.AuthFacade;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class OrgRoleStoreTest {
    AuthFacade auth = new AuthFacade();
    OrgRoleStore ors = new OrgRoleStore(auth);


    @Test
    public void getOrganizationRoles() {
        String[] expected = {"Administrator", "Clinical Chemistry Technologist", "Laboratory Coordinator",
                "Medical Lab Technician", "Receptionist", "Specialist Doctor"};
        List<String> actual = ors.getOrganizationRoles();
        Assert.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    public void getOrganizationRoleNull() {
        OrganizationRole expected = null;
        OrganizationRole actual = ors.getOrganizationRole(null);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getOrganizationRole() {
        OrganizationRole expected = new OrganizationRole("sd", "Specialist Doctor");
        OrganizationRole actual = ors.getOrganizationRole("Specialist Doctor");
        Assert.assertEquals(expected, actual);
    }
}