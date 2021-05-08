package app.domain.store;

import app.domain.model.Employee;

import java.util.ArrayList;

public class EmployeeStore {
    private ArrayList<Employee> employeeList;

    public boolean validateEmployee(Employee employee) {
        if (employee == null) return false;
        return !this.employeeList.contains(employee);
    }
    public boolean addEmployee(Employee employee) {
        if (!validateEmployee(employee)) return false;
        return this.employeeList.add(employee);
    }
    public boolean saveEmployee(Employee employee) {
        return addEmployee(employee);
    }
}
