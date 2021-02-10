package com.example.controllerservice.groups;

import com.example.database.groupsrepository.GroupsRepositoryPostgres;
import com.example.groups.Group;
import com.example.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowGroupService {

    public static List<String> getUserNameList(String groupName) {
        List<String> userNameList = new ArrayList<>();
        final Integer idGroup = GroupsRepositoryPostgres.getInstance().getIdGroupByName(groupName);
        final Optional<Group> group = GroupsRepositoryPostgres.getInstance().find(idGroup);
        for (User user : group.get().getUserList()) {
            userNameList.add(user.getLogin());
        }
        return userNameList;
    }

    public static Optional<Group> getGroupByName(String groupName) {
        final Integer idGroupByName = GroupsRepositoryPostgres.getInstance().getIdGroupByName(groupName);
        if (idGroupByName != null) {
            return GroupsRepositoryPostgres.getInstance().find(idGroupByName);
        }
        return Optional.empty();
    }
}
