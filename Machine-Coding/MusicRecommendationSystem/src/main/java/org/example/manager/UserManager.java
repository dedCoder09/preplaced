package org.example.manager;

import org.example.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    public static Map<String, User> userMap = new HashMap<>();

    public static void addUser(User user){
        userMap.put(user.getName(),user);
    }

    public static User getUser(String name){
        User user = userMap.get(name);
        return user;
    }

    public static Map<String, User> getAllUsers(){
        return userMap;
    }
}
