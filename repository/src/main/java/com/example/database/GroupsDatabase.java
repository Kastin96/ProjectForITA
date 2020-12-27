package com.example.database;

import com.example.groups.Group;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GroupsDatabase {

    private static volatile Map<UUID, Group> instance;

    public static Map<UUID, Group> getInstance() {
        if (instance == null) {
            synchronized (GroupsDatabase.class) {
                if (instance == null) {
                    instance = new HashMap<>();
                }
            }
        }

        return instance;
    }
}
