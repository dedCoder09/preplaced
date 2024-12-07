package org.example.strategy;

import org.example.entity.Employee;
import org.example.entity.Meeting;
import org.example.helper.TimeMeetingComparator;
import org.example.manager.EmployeeManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RemoveOverlappingMeeting implements MeetingConflictResolutionStrategy{


    @Override
    public List<Meeting> conflictResolution(Employee employee, List<Meeting> meetingList) {
        if(meetingList.size() <= 1) return meetingList;
        Collections.sort(meetingList, new TimeMeetingComparator());

        List<Meeting> conflictFreeMeetings = new ArrayList<>();
        conflictFreeMeetings.add(meetingList.get(0));

        for(int i=1;i< meetingList.size();i++){
            Meeting meeting1 = conflictFreeMeetings.get(conflictFreeMeetings.size()-1);
            Meeting meeting2 = meetingList.get(i);
            if(meeting1.getEndTime().before(meeting2.getStartTime()) || meeting1.getEndTime().equals(meeting2.getStartTime())){
                conflictFreeMeetings.add(meeting2);
            }else{
                Employee meeting1Organisor = EmployeeManager.getEmployee(meeting1.getOrganiser());
                Employee meeting2Organisor = EmployeeManager.getEmployee(meeting2.getOrganiser());

                if(meeting1Organisor.getName().equals(employee.getName()) || meeting2Organisor.getName().equals(employee.getName())){
                    if(meeting2Organisor.getName().equals(employee.getName()) ){
                        conflictFreeMeetings.remove(meeting1);
                        conflictFreeMeetings.add(meeting2);
                    }
                } else if(meeting1Organisor.getEmployeeType().getRank() != meeting2Organisor.getEmployeeType().getRank()){
                    if(meeting2Organisor.getEmployeeType().getRank() < meeting1Organisor.getEmployeeType().getRank()){
                        conflictFreeMeetings.remove(meeting1);
                        conflictFreeMeetings.add(meeting2);
                    }
                } else if(meeting1.getNoOfAttendees() != meeting2.getNoOfAttendees()){
                    if(meeting1.getNoOfAttendees() > meeting2.getNoOfAttendees()){
                        conflictFreeMeetings.remove(meeting1);
                        conflictFreeMeetings.add(meeting2);
                    }
                } else if(meeting2.getEndTime().before(meeting1.getEndTime())){
                    conflictFreeMeetings.remove(meeting1);
                    conflictFreeMeetings.add(meeting2);
                }
            }
        }

        return conflictFreeMeetings;
    }
}
