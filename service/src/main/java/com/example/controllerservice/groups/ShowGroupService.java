package com.example.controllerservice.groups;

import com.example.database.groupsrepository.GroupsRepositoryHibernate;
import com.example.database.groupsrepository.GroupsRepositoryPostgres;
import com.example.groups.Group;
import com.example.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShowGroupService {

    public static List<String> getUserNameList(String groupName) {
        List<String> userNameList = new ArrayList<>();
        final Integer idGroup = GroupsRepositoryPostgres.getInstance().getIdGroupByName(groupName);
        final Optional<Group> group = GroupsRepositoryPostgres.getInstance().find(idGroup);
        for (User user : group.get().getStudents()) {
            userNameList.add(user.getLogin());
        }
        return userNameList;
    }

    public static List<String> getUserNameListFromHibernate(String groupName) {
        List<String> userNameList = new ArrayList<>();

        final Optional<Group> group = GroupsRepositoryHibernate.getInstance().findByGroupName(groupName);
        if (group.isPresent()) {
            userNameList = group.get().getStudents()
                    .stream()
                    .map(User::getLogin)
                    .collect(Collectors.toList());
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

    public static Optional<Group> getGroupByNameFromHibernate(String groupName) {
        return GroupsRepositoryHibernate.getInstance().findByGroupName(groupName);
    }
}
