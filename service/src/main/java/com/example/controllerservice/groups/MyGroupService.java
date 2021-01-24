package com.example.controllerservice.groups;

import com.example.database.groupsrepository.GroupsRepositoryPostgres;
import com.example.groups.Group;
import com.example.users.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyGroupService {

    public static List<String> getGroupNameList(User user) {
        List<String> groupNameList = new ArrayList<>();
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
}
