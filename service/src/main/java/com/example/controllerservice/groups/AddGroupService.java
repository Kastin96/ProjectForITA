package com.example.controllerservice.groups;

import com.example.controllerservice.basicuser.DispenserUserWithRole;
import com.example.groups.Group;
import com.example.repositoryaccess.GroupService;
import com.example.repositoryaccess.StudentService;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class AddGroupService {
    private GroupService groupService;
    private StudentService studentService;
    private DispenserUserWithRole dispenserUserWithRole;

    public boolean addNewGroup(ModelAndView modelAndView, String groupName, String groupTrainer, Set<Student> userList) {
        try {
            final Optional<? extends User> trainer = dispenserUserWithRole.getUserByLogin(groupTrainer);
            if (trainer.isPresent()) {
                if (trainer.get() instanceof Trainer) {
                    if (((Trainer) trainer.get()).getGroup() != null) {
                        modelAndView.addObject("badAddGroup", "The Trainer is busy!");
                    } else {
                        if (!userList.isEmpty()) {
                            Group group = Group.builder()
                                    .groupName(groupName)
                                    .trainer((Trainer) trainer.get())
                                    .build();
                            groupService.save(group);

                            final Optional<Group> byGroupName = groupService.findByGroupName(groupName);

                            if (byGroupName.isPresent()) {
                                for (Student student : userList) {
                                    final Set<Group> groups = student.getGroups();
                                    groups.add(byGroupName.get());

                                    studentService.save(student);
                                }
                            }
                            log.info("Group added = {}", group.getGroupName());
                            return true;
                        } else {
                            modelAndView.addObject("badAddGroup", "Something went wrong: try again! " +
                                    "No user found!");
                            log.warn("Error: Group not created = {}", trainer.get().getLogin());
                        }
                    }
                } else {
                    modelAndView.addObject("badAddGroup", "The trainer is incorrect!");
                }
            }
        } catch (NoSuchElementException | NoResultException exception) {
            modelAndView.addObject("badAddGroup", "The trainer is incorrect! - NoResultException!!");
        }
        return false;
    }

    public Set<Student> getListOfUniqueUsersFromString(String groupUser, String splitter) {
        Set<Student> userSet = new HashSet<>();
        for (String splittedUser : groupUser.split(splitter)) {
            try {
                final Optional<? extends User> userByLogin = dispenserUserWithRole.getUserByLogin(splittedUser);
                if (userByLogin.isPresent()) {
                    if (userByLogin.get() instanceof Student) {
                        userSet.add((Student) userByLogin.get());
                    }
                }
            } catch (NoSuchElementException | NoResultException exception) {
                log.warn("Error splitted users for new Group");
            }
        }
        return userSet;
    }


    public boolean checkGroupName(String groupName) {
        try {
            try {
                final Optional<Group> byGroupName = groupService.findByGroupName(groupName);
                if (byGroupName.isPresent()) {
                    return true;
                }
            } catch (NoResultException e) {
                return false;
            }
        } catch (NullPointerException exception) {
            log.warn("Error checkGroupName");
        }
        return true;
    }
}
