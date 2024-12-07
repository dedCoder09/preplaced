package org.example.strategy;

import org.example.entity.Employee;
import org.example.entity.Meeting;

import java.util.List;

public interface MeetingConflictResolutionStrategy {

    public List<Meeting> conflictResolution(Employee employee, List<Meeting> meetingList);
}
