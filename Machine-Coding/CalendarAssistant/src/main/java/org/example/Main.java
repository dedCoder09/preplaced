package org.example;

import org.example.entity.COMMAND;
import org.example.service.CalendarAssistantService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CalendarAssistantService calendarAssistantService = new CalendarAssistantService();

        String filePath = "/Users/ritambera/Desktop/Machine-Coding/preplaced/Machine-Coding/CalendarAssistant/src/main/resources/input.txt";
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(true){
            COMMAND command = COMMAND.valueOf(scanner.nextLine());
            switch (command){
                case ADD_EMPLOYEE: while(true){
                    String input = scanner.nextLine();
                    if(!input.equals("END")){
                        calendarAssistantService.addEmployee(input);
                    }else break;
                }
                break;
                case ADD_MEETING: String employeeName = null;
                    while(true){
                        if(employeeName == null){
                            employeeName = scanner.nextLine();
                        }else{
                            String input = scanner.nextLine();
                            if(!input.equals("END")){
                                calendarAssistantService.addEmployeeMeeting(employeeName, input);
                            }else break;
                        }
                    }
                    calendarAssistantService.processAllMeetings(employeeName);
                    break;
                case SHOW_MEETINGS: calendarAssistantService.showAllMeetings(scanner.nextLine());
                    break;
                case SHOW_EMPLOYEES: calendarAssistantService.showAllEmployees();
                    break;
                case EXIT:return;
            }
            //System.out.println("\n");
        }

    }
}