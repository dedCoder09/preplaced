package org.example.service;

import org.example.entity.Employee;
import org.example.entity.Meeting;
import org.example.manager.EmployeeManager;
import org.example.strategy.InputValidator;
import org.example.strategy.MeetingConflictResolutionStrategy;
import org.example.strategy.MeetingInputValidator;
import org.example.strategy.RemoveOverlappingMeeting;

import java.util.List;
import java.util.Map;

public class CalendarAssistantService {

    public void addEmployee(String employeeInput){
        String[] employeeDetails = employeeInput.split(",");
        EmployeeManager.addEmployee(employeeDetails[0],employeeDetails[1]);
    }

    public void addEmployeeMeeting(String employeeName, String employeeMeetingInput){
        //System.out.println("employeeMeetingInput -> " + employeeMeetingInput);
        employeeName = employeeName.toUpperCase();
        InputValidator inputValidator = new MeetingInputValidator();
        if(!inputValidator.checkInputValidity(employeeMeetingInput)){
            System.out.println("Unable to pass input Validator Check for meeting input");
            return;
        }
        String[] employeeMeetingDetails = employeeMeetingInput.split(",");
        Meeting meeting = new Meeting(employeeMeetingDetails[0],employeeMeetingDetails[1],employeeMeetingDetails[2],Integer.valueOf(employeeMeetingDetails[3]),employeeMeetingDetails[4]);

        EmployeeManager.addEmployeeMeeting(employeeName, meeting);
    }

    public void showAllEmployees(){
        Map<String,Employee> employeeMap = EmployeeManager.getAllEmployees();
        for (String employeeName: employeeMap.keySet()){
            System.out.println("name : " + employeeName + ", designation : " + employeeMap.get(employeeName).getEmployeeType().name());
        }
    }

    public void processAllMeetings(String employeeName){
        employeeName = employeeName.toUpperCase();
        Employee employee = EmployeeManager.getEmployee(employeeName);
        List<Meeting> employeeMeetings = employee.getMeetingList();
        MeetingConflictResolutionStrategy meetingConflictResolutionStrategy = new RemoveOverlappingMeeting();
        employeeMeetings = meetingConflictResolutionStrategy.conflictResolution(employee, employeeMeetings);
        employee.setMeetingList(employeeMeetings);
    }

    public void showAllMeetings(String employeeName){
        employeeName = employeeName.toUpperCase();
        Employee employee = EmployeeManager.getEmployee(employeeName);
        List<Meeting> employeeMeetings = employee.getMeetingList();
        System.out.println(employeeName);
        employeeMeetings.stream().forEach(System.out::println);
    }
}
