package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    String name;
    EMPLOYEE_TYPE employeeType;
    List<Meeting> meetingList;

    public Employee(String name, EMPLOYEE_TYPE employeeType) {
        this.name = name;
        this.employeeType = employeeType;
        this.meetingList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EMPLOYEE_TYPE getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EMPLOYEE_TYPE employeeType) {
        this.employeeType = employeeType;
    }

    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    public void setMeetingList(List<Meeting> meetingList) {
        this.meetingList = meetingList;
    }
}
