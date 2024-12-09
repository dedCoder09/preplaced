package org.example.manager;

import org.example.entity.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestManager {

    static List<Request> requestList = new ArrayList<>();

    public static void addRequest(Request request){
        requestList.add(request);
    }

    public static List<Request> getAllRequest(){
        return requestList;
    }
}
