package com.example.controllerservice.groups;

import com.example.database.groupsrepository.GroupsRepositoryHibernate;
import com.example.database.groupsrepository.GroupsRepositoryPostgres;
import com.example.database.usersrepository.StudentRepositoryHibernate;
import com.example.database.usersrepository.TrainerRepositoryHibernate;
import com.example.groups.Group;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MyGroupService {

    public static List<String> getGroupNameList(User user) {
        List<String> groupNameList;
        if (user instanceof Student) {
            final List<Group> groupListByUserId = GroupsRepositoryPostgres.getInstance()
                    .getGroupListByUserId(user.getId());
            groupNameList = groupListByUserId
                    .stream()
                    .map(Group::getGroupName)
                    .collect(Collectors.toList());
        } else if (user instanceof Trainer) {
            final List<Group> groupListByTrainerId = GroupsRepositoryPostgres.getInstance()
                    .getGroupListByTrainerId(user.getId());
            groupNameList = groupListByTrainerId
                    .stream()
                    .map(Group::getGroupName)
                    .collect(Collectors.toList());
        } else {
            final List<Group> allGroupList = GroupsRepositoryPostgres.getInstance()
                    .findAll();
            groupNameList = allGroupList
                    .stream()
                    .map(Group::getGroupName)
                    .collect(Collectors.toList());
        }
        return groupNameList;
    }

    public static List<String> getGroupNameListByHibernate(User user) {
        List<String> groupNameList = new ArrayList<>();
        if (user instanceof Student) {
            final Optional<Student> student = StudentRepositoryHibernate.getInstance().find(user.getId());
            if (student.isPresent()){
                final Set<Group> groups = student.get().getGroups();
                for (Group group : groups) {
                    groupNameList.add(group.getGroupName());
                }
            }
        } else if (user instanceof Trainer) {
            final Optional<Trainer> trainer = TrainerRepositoryHibernate.getInstance().find(user.getId());
            if (trainer.isPresent()){
                final Group group = trainer.get().getGroup();
                if (group != null){
                    groupNameList.add(group.getGroupName());
                }
            }
        } else {
            groupNameList = GroupsRepositoryHibernate.getInstance().findAllGroupName();
        }
        return groupNameList;
    }
}
