package org.example.strategy;

import org.example.entity.Employee;
import org.example.helper.DateTimeHelper;
import org.example.manager.EmployeeManager;
import org.example.strategy.InputValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MeetingInputValidator implements InputValidator {

    @Override
    public boolean checkInputValidity(String meeting) {
        //System.out.println(meeting);
        String[] meetingDetails = meeting.split(",");
        String startTime = meetingDetails[1];
        String endTime = meetingDetails[2];
        String organiserEmployeeName = meetingDetails[4];
        organiserEmployeeName = organiserEmployeeName.toUpperCase();
        Map<String, Employee> employeeMap = EmployeeManager.getAllEmployees();
        //System.out.println("employee size->"+ employeeMap.size());

        try{
            Date startTimeDate = DateTimeHelper.parseStringToDate(startTime);
            Date endTimeDate = DateTimeHelper.parseStringToDate(endTime);
        } catch (ParseException e) {
            System.out.println("Time format is wrong");
            return false;
        }

        if(employeeMap.containsKey(organiserEmployeeName)){
            return true;
        }else{
            System.out.println("Employee not found");
            return false;
        }
    }

}
