package com.example.database;

import com.example.users.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserDatabase {

    private static volatile Map<UUID, User> instance;

    public static Map<UUID, User> getInstance() {
        if (instance == null) {
            synchronized (UserDatabase.class) {
                if (instance == null) {
                    instance = new HashMap<>();
                }
            }
        }

        return instance;
    }
}
