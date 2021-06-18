package com.example.controllerservice.groups;

import com.example.controllerservice.basicuser.BasicUserService;
import com.example.database.groupsrepository.GroupsRepositoryHibernate;
import com.example.database.groupsrepository.GroupsRepositoryPostgres;
import com.example.database.usersrepository.StudentRepositoryHibernate;
import com.example.database.usersrepository.TrainerRepositoryPostgres;
import com.example.database.usersrepository.UserRepositoryPostgres;
import com.example.groups.Group;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class AddGroupService {
    private final Logger log = LoggerFactory.getLogger(AddGroupService.class);

    @Autowired
    private GroupsRepositoryHibernate groupsRepository;
    @Autowired
    private StudentRepositoryHibernate studentRepository;
    @Autowired
    private BasicUserService basicUserService;


    public boolean addNewGroup(HttpServletRequest req, String groupName, String groupTrainer, Set<Student> userList) {

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
                                .withStudents(userList);

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

    public boolean addNewGroupByHibernate(ModelAndView modelAndView, String groupName, String groupTrainer, Set<Student> userList) {
        try {
            final Optional<? extends User> trainer = basicUserService.findUserWithRoleByLogin(groupTrainer);
            if (trainer.isPresent()) {
                if (trainer.get() instanceof Trainer) {
                    if (((Trainer) trainer.get()).getGroup() != null) {
                        modelAndView.addObject("badAddGroup", "The Trainer is busy!");
                    } else {
                        if (!userList.isEmpty()) {
                            Group group = new Group()
                                    .withGroupName(groupName)
                                    .withTrainer((Trainer) trainer.get());
                            groupsRepository.save(group);

                            final Optional<Group> byGroupName = groupsRepository.findByGroupName(groupName);

                            if (byGroupName.isPresent()) {
                                for (Student student : userList) {
                                    final Set<Group> groups = student.getGroups();
                                    groups.add(byGroupName.get());

                                    studentRepository.save(student);
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
            modelAndView.addObject("badAddGroup", "The trainer is incorrect!");
        }
        return false;
    }

    public Set<Student> getListOfUniqueUsersFromString(String groupUser, String splitter) {
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

    public Set<Student> getListOfUniqueUsersFromStringByHibernate(String groupUser, String splitter) {
        Set<Student> userSet = new HashSet<>();
        for (String splittedUser : groupUser.split(splitter)) {
            try {
                final Optional<? extends User> userByLogin = basicUserService.findUserWithRoleByLogin(splittedUser);
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

    public boolean checkGroupNameByHibernate(String groupName) {
        try {
            try {
                final Optional<Group> byGroupName = groupsRepository.findByGroupName(groupName);
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
