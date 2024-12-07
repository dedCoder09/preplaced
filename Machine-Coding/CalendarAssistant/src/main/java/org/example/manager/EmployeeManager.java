package org.example.manager;

import org.example.entity.Employee;
import org.example.entity.EMPLOYEE_TYPE;
import org.example.entity.Meeting;

import java.util.HashMap;
import java.util.Map;

public class EmployeeManager {

    static Map<String, Employee> employeeMap = new HashMap<>();

    public static void addEmployee(String name, String position){
        EMPLOYEE_TYPE employeeType = EMPLOYEE_TYPE.valueOf(position.toUpperCase());
        name = name.toUpperCase();
        Employee employee = new Employee(name, employeeType);
        employeeMap.put(name,employee);
    }

    public static Employee getEmployee(String employeeName){
        employeeName = employeeName.toUpperCase();
        return employeeMap.get(employeeName);
    }

    public static Map<String, Employee> getAllEmployees() {
        return employeeMap;
    }

    public static void addEmployeeMeeting(String employeeName, Meeting meeting){
        employeeName = employeeName.toUpperCase();
        Employee employee = employeeMap.get(employeeName);
        employee.getMeetingList().add(meeting);
    }
}
