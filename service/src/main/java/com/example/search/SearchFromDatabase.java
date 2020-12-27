package com.example.search;

import com.example.database.GroupsDatabase;
import com.example.database.UserDatabase;
import com.example.groups.Group;
import com.example.users.Trainer;
import com.example.users.User;

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

    public static boolean findTrainerFromGroupDatabase(String trainerLogin) {
        User trainer = findUserFromUserDatabase(trainerLogin);

        if (trainer instanceof Trainer) {
            for (Map.Entry<UUID, Group> uuidGroup : GroupsDatabase.getInstance().entrySet()) {
                if (uuidGroup.getValue().getTrainer().equals(trainer)){
                    return true;
                }
            }
        }

        return false;
    }


}
