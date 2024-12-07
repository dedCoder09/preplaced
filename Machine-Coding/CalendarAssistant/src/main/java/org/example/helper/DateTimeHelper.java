package org.example.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {

    private static DateFormat primaryFormat = new SimpleDateFormat("hh:mma");
    private static DateFormat secondaryFormat = new SimpleDateFormat("HH:mm");

    public static Date parseStringToDate(String time) throws ParseException {
        return parseStringToDate(time, primaryFormat);
    }

    public static Date parseStringToDate(String time, DateFormat dateFormat) throws ParseException {
        Date date = null;
        try{
            date = dateFormat.parse(time);
            return date;
        } catch (ParseException e) {
            if(dateFormat != secondaryFormat){
                return parseStringToDate(time, secondaryFormat);
            }else{
                throw e;
            }
        }
    }

    public static String parseDateToTimeString(Date date){
        return parseDateToTimeString(date, primaryFormat);
    }
    public static String parseDateToTimeString(Date date, DateFormat dateFormat){
        String time = dateFormat.format(date);
        return time.toUpperCase();
    }
}
