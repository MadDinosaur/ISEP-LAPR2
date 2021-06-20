package app.domain.store;

import app.domain.model.Employee;
import app.domain.model.Exceptions.InvalidEmployeeException;
import app.domain.model.Exceptions.InvalidNameException;

import java.io.Serializable;
import java.util.ArrayList;

public class EmployeeStore implements Serializable {
    private int id = 1;
    private ArrayList<Employee> employeeList = new ArrayList<>();

    public String generateEmployeeId(String fullName) {
        if (fullName == null || fullName.isEmpty()) throw new InvalidNameException();

        StringBuilder initials = new StringBuilder();
        String[] names = fullName.split(" ");
        for (String name : names) {
            initials.append(name.charAt(0));
        }
        return initials.toString().toUpperCase() + String.format("%05d", id);
    }

    private boolean validateEmployee(Employee employee) {
        if (employee == null) return false;
        if (this.employeeList.contains(employee)) return false;
        for (Employee e : employeeList) {
            if (e.getEmail().equalsIgnoreCase(employee.getEmail())) return false;
            if (e.getPhoneNumber() == employee.getPhoneNumber()) return false;
            if (e.getName().equalsIgnoreCase(employee.getName()) && e.getAddress().equalsIgnoreCase(employee.getAddress())) return false;
            }
        return true;
        }

    private boolean addEmployee(Employee employee) {
        if (!validateEmployee(employee)) throw new InvalidEmployeeException();
        id++;
        return this.employeeList.add(employee);
    }
    public boolean saveEmployee(Employee employee) {
        return addEmployee(employee);
    }
}
