package app.domain.store;

import app.domain.model.OrganizationRole;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrgRoleStore {
    private ArrayList<OrganizationRole> rolesList;

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
