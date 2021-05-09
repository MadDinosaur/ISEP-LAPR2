package app.domain.store;

import app.domain.model.Company;
import app.domain.model.OrganizationRole;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrgRoleStore {
    private Company company;
    private ArrayList<OrganizationRole> rolesList = new ArrayList<>();

    public OrgRoleStore(Company company) {
        rolesList.add(new OrganizationRole("admin", "Administrator", company));
        rolesList.add(new OrganizationRole("cct", "Clinical Chemistry Technologist", company));
        rolesList.add(new OrganizationRole("lc", "Laboratory Coordinator", company));
        rolesList.add(new OrganizationRole("mlt", "Medical Lab Technician", company));
        rolesList.add(new OrganizationRole("receptionist", "Receptionist", company));
        rolesList.add(new OrganizationRole("sd", "Specialist Doctor", company));
    }

    public List<String> getOrganizationRoles() {
        List<String> toString = rolesList.stream()
                .map(role -> String.valueOf(role))
                .collect(Collectors.toList());
        return toString;
    }

    public OrganizationRole getOrganizationRole(String description) {
        for (OrganizationRole role : rolesList) {
            if (role.getDescription().equalsIgnoreCase(description)) {
                return role;
            }
        }
        return null;
    }
}
