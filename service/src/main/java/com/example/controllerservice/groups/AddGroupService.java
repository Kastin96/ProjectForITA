package com.example.controllerservice.groups;

import com.example.database.groupsrepository.GroupsRepositoryPostgres;
import com.example.database.usersrepository.TrainerRepositoryPostgres;
import com.example.database.usersrepository.UserRepositoryPostgres;
import com.example.groups.Group;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public class AddGroupService {
    private final static Logger log = LoggerFactory.getLogger(AddGroupService.class);

    public static boolean addNewGroup(HttpServletRequest req, String groupName, String groupTrainer, Set<Student> userList) {

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
                                .withUsers(userList);

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

    public static Set<Student> getListOfUniqueUsersFromString(String groupUser, String splitter) {
        Set<Student> userSet = new HashSet<>();
        for (String splittedUser : groupUser.split(splitter)) {
            try {
                Student user = (Student) UserRepositoryPostgres.getInstance().getUserByLogin(splittedUser.toLowerCase(Locale.ROOT)).get();
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
