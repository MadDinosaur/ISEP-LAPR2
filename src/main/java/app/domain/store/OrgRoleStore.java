package app.domain.store;

import app.domain.model.Employee;
import app.domain.model.Exceptions.UnregisteredOrgRolesException;
import app.domain.model.OrganizationRole;
import app.domain.shared.Constants;
import auth.AuthFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrgRoleStore implements Serializable {
    private AuthFacade authFacade;
    private ArrayList<OrganizationRole> rolesList = new ArrayList<>();

    public OrgRoleStore(AuthFacade auth) {
        this.authFacade = auth;
        addOrganizationRole(Constants.ROLE_ADMIN, "Administrator");
        addOrganizationRole("cct", "Clinical Chemistry Technologist");
        addOrganizationRole("lc", "Laboratory Coordinator");
        addOrganizationRole("mlt", "Medical Lab Technician");
        addOrganizationRole("receptionist", "Receptionist");
        addOrganizationRole("sd", "Specialist Doctor");
        addOrganizationRole("cl","Client"); //
    }

    public List<String> getOrganizationRoles() {
        if (rolesList.isEmpty()) throw new UnregisteredOrgRolesException();

        List<String> toString = rolesList.stream()
                .map(role -> String.valueOf(role))
                .collect(Collectors.toList());
        return toString;
    }

    public OrganizationRole getOrganizationRole(String description) {
        if (description == null) return null;

        for (OrganizationRole role : rolesList) {
            if (role.getDescription().equalsIgnoreCase(description)) {
                return role;
            }
        }
        return null;
    }
    public boolean addOrganizationRole(String id, String description) {
        OrganizationRole newRole = new OrganizationRole(id, description);
        if (validateOrganizationRole(newRole)) return rolesList.add(newRole) && authFacade.addUserRole(id, description);
        return false;
    }
    private boolean validateOrganizationRole(OrganizationRole role) {
        if (role == null) return false;
        return !rolesList.contains(role) && role.validate();
    }
    public Employee createEmployee(String role, String id, String name, String address, String phoneNumber, String email, String soc) {
        return getOrganizationRole(role).createEmployee(id, name, address, phoneNumber, email, soc);
    }

    public Employee createEmployee(String role, String id, String name, String address, String phoneNumber, String email, String soc, String din) {
        return getOrganizationRole(role).createEmployee(id, name, address, phoneNumber, email, soc, din);
    }
}
