package com.example.controllerservice.groups;

import com.example.database.groupsrepository.GroupsRepositoryPostgres;
import com.example.database.usersrepository.*;
import com.example.groups.Group;
import com.example.localdatabase.GroupsDatabase;
import com.example.search.SearchFromDatabase;
import com.example.users.Trainer;
import com.example.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class AddGroupService {
    private final static Logger log = LoggerFactory.getLogger(AddGroupService.class);

    public static boolean addNewGroup(HttpServletRequest req, String groupName, String groupTrainer, Set<User> userList) {

        try {
            User trainer = TrainerRepositoryPostgres.getInstance().getPersonByLogin(groupTrainer).get();

            if (trainer instanceof Trainer) {
                if (!GroupsRepositoryPostgres.getInstance().getGroupListByTrainerId(trainer.getId()).isEmpty()) {
                    req.setAttribute("badAddGroup", "The Trainer is busy!");
                } else {
                    if (!userList.isEmpty()) {
                        Group group = new Group()
                                .withGroupName(groupName)
                                .withTrainer((Trainer) trainer)
                                .withUserList(userList);

                        GroupsRepositoryPostgres.getInstance().save(group);

                        log.info("Group added = {}", group.getGroupName());
                        return true;
                    } else {
                        req.setAttribute("badAddGroup", "Something went wrong: try again! " +
                                "No user found!");
                        log.warn("Error: Group not created = {}", trainer.getLogin());
                    }
                }
            } else {
                req.setAttribute("badAddGroup", "The trainer is incorrect!");
            }
        } catch (NoSuchElementException noSuchElementException) {
            req.setAttribute("badAddGroup", "The trainer is incorrect!");
        }
        return false;
    }

    public static Set<User> getListOfUniqueUsersFromString(String groupUser, String splitter) {
        Set<User> userSet = new HashSet<>();
        for (String splittedUser : groupUser.split(splitter)) {
            try {
                User user = UserRepositoryPostgres.getInstance().getUserByLogin(splittedUser.toLowerCase(Locale.ROOT)).get();
                userSet.add(user);
            } catch (NoSuchElementException noSuchElementException) {
                log.warn("Error splitted users for new Group");
            }
        }
        return userSet;
    }

    public static boolean checkGroupName(String groupName) {

        try {
            final Integer idGroupByName = GroupsRepositoryPostgres.getInstance().getIdGroupByName(groupName);
            final Optional<Group> group = GroupsRepositoryPostgres.getInstance().find(idGroupByName);
            if (group.isPresent()) {
                return true;
            }
        } catch (NullPointerException nullPointerException) {
            log.warn("Error checkGroupName");
        }
        return false;
    }
}
