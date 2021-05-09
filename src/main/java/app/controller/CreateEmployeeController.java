package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;

import java.util.List;

public class CreateEmployeeController {
    Company company;
    Employee employee;

    public CreateEmployeeController(Company company) {
        this.company = company;
    }

    public List<String> getOrganizationRoles() {
        return company.getOrgRoleStore().getOrganizationRoles();
    }

    public void createEmployee(String role, String name, String address, String phoneNumber, String email, String soc) {
        String id = company.getEmployeeStore().generateEmployeeId(name);
        employee = company.getOrgRoleStore().createEmployee(role, id, name, address, phoneNumber, email, soc);
    }

    public void createEmployee(String role, String name, String address, String phoneNumber, String email, String soc, String DIN) {
        String id = company.getEmployeeStore().generateEmployeeId(name);
        employee = company.getOrgRoleStore().createEmployee(role, id, name, address, phoneNumber, email, soc, DIN);
    }

    public boolean saveEmployee() {
        return company.getEmployeeStore().saveEmployee(employee) && company.saveEmployeeAsUser(employee);
    }
}
