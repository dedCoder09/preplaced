package org.example.helper;

import org.example.entity.Meeting;


import java.util.*;

public class TimeMeetingComparator implements Comparator<Meeting> {

    @Override
    public int compare(Meeting meeting1, Meeting meeting2){
        return timeInMillis(meeting1) - timeInMillis(meeting2);
    }

    public int timeInMillis(Meeting meeting1){
        return (int)meeting1.getStartTime().getTime();
    }

}
