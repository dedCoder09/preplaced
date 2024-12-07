package org.example.entity;

import org.example.helper.DateTimeHelper;

import java.text.ParseException;
import java.util.Date;

public class Meeting {

    String name;
    Date startTime;
    Date endTime;
    Integer noOfAttendees;
    String organiser;

    public Meeting(String name, String startTime, String endTime, Integer noOfAttendees, String organiser) {
        this.name = name;
        try {
            this.startTime = DateTimeHelper.parseStringToDate(startTime);
            this.endTime = DateTimeHelper.parseStringToDate(endTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.noOfAttendees = noOfAttendees;
        this.organiser = organiser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getNoOfAttendees() {
        return noOfAttendees;
    }

    public void setNoOfAttendees(Integer noOfAttendees) {
        this.noOfAttendees = noOfAttendees;
    }

    public String getOrganiser() {
        return organiser;
    }

    public void setOrganiser(String organiser) {
        this.organiser = organiser;
    }

    @Override
    public String toString() {
        return name +
                "," + DateTimeHelper.parseDateToTimeString(startTime) +
                "," + DateTimeHelper.parseDateToTimeString(endTime) +
                "," + noOfAttendees +
                "," + organiser ;
    }
}
