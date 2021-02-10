package com.example.search;

import com.example.groups.Group;
import com.example.localdatabase.GroupsDatabase;
import com.example.localdatabase.UserDatabase;
import com.example.users.Trainer;
import com.example.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SearchFromDatabase {

    public static User findUserFromUserDatabase(String userLogin) {
        for (Map.Entry<UUID, User> uuidUser : UserDatabase.getInstance().entrySet()) {
            if (uuidUser.getValue().getLogin().equalsIgnoreCase(userLogin)) {
                return uuidUser.getValue();
            }
        }
        return null;
    }

    public static boolean findFromGroupDatabase(String login) {
        User trainer = findUserFromUserDatabase(login);

        if (trainer instanceof Trainer) {
            for (Map.Entry<UUID, Group> uuidGroup : GroupsDatabase.getInstance().entrySet()) {
                if (uuidGroup.getValue().getTrainer().equals(trainer)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static List<Group> findUserFromGroupDatabase(User user) {
        List<Group> groupList = new ArrayList<>();

        for (Map.Entry<UUID, Group> uuidGroupEntry : GroupsDatabase.getInstance().entrySet()) {
            if (user instanceof Trainer) {
                if (uuidGroupEntry.getValue().getTrainer().equals(user)) {
                    groupList.add(uuidGroupEntry.getValue());
                }
            } else {
                for (User verifiedUser : uuidGroupEntry.getValue().getUserList()) {
                    if (verifiedUser.equals(user)) {
                        groupList.add(uuidGroupEntry.getValue());
                    }
                }
            }
        }

        return groupList;
    }
}
