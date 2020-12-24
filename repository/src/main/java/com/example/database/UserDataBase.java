package com.example.database;

import com.example.users.Student;
import com.example.users.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserDataBase {

    private static volatile Map<UUID, Object> instance;

    public static Map<UUID, Object> getInstance() {
        if (instance == null) {
            synchronized (UserDataBase .class) {
                if (instance == null) {
                    instance = new HashMap<>();
                }
            }
        }
        return instance;
    }
}
