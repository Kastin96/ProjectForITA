package com.example.controllerservice.groups;

import com.example.database.groupsrepository.GroupsRepositoryHibernate;
import com.example.database.groupsrepository.GroupsRepositoryPostgres;
import com.example.groups.Group;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyGroupService {
    @Autowired
    private GroupsRepositoryHibernate groupRepository;

    public List<String> getGroupNameList(User user) {
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

    public List<String> getGroupNameListByHibernate(User user) {
        List<String> groupNameList = new ArrayList<>();
        if (user instanceof Student) {
            final Optional<List<Group>> allGroupByUser = groupRepository.findAllGroupByUser((Student) user);
            if (allGroupByUser.isPresent()) {
                for (Group group : allGroupByUser.get()) {
                    groupNameList.add(group.getGroupName());
                }
            }
        } else if (user instanceof Trainer) {
            final Optional<List<Group>> allGroupByUser = groupRepository.findAllGroupByUser((Trainer) user);
            if (allGroupByUser.isPresent()) {
                for (Group group : allGroupByUser.get()) {
                    groupNameList.add(group.getGroupName());
                }
            }
        } else {
            groupNameList = groupRepository.findAllGroupName();
        }
        return groupNameList;
    }
}
