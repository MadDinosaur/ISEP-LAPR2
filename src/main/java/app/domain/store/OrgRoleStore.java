package app.domain.store;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.Exceptions.UnregisteredOrgRolesException;
import app.domain.model.OrganizationRole;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrgRoleStore {
    private ArrayList<OrganizationRole> rolesList = new ArrayList<>();

    public OrgRoleStore() {
        rolesList.add(new OrganizationRole("admin", "Administrator"));
        rolesList.add(new OrganizationRole("cct", "Clinical Chemistry Technologist"));
        rolesList.add(new OrganizationRole("lc", "Laboratory Coordinator"));
        rolesList.add(new OrganizationRole("mlt", "Medical Lab Technician"));
        rolesList.add(new OrganizationRole("receptionist", "Receptionist"));
        rolesList.add(new OrganizationRole("sd", "Specialist Doctor"));
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

    public Employee createEmployee(String role, String id, String name, String address, String phoneNumber, String email, String soc) {
        return getOrganizationRole(role).createEmployee(id, name, address, phoneNumber, email, soc);
    }

    public Employee createEmployee(String role, String id, String name, String address, String phoneNumber, String email, String soc, String din) {
        return getOrganizationRole(role).createEmployee(id, name, address, phoneNumber, email, soc, din);
    }
}
