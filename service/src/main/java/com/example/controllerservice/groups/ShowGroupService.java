package com.example.controllerservice.groups;

import com.example.database.postgres.GroupsRepositoryPostgres;
import com.example.groups.Group;
import com.example.repositoryaccess.GroupService;
import com.example.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ShowGroupService {
    private GroupService groupService;

    public List<String> getUserNameList(String groupName) {
        List<String> userNameList = new ArrayList<>();
        final Integer idGroup = GroupsRepositoryPostgres.getInstance().getIdGroupByName(groupName);
        final Optional<Group> group = GroupsRepositoryPostgres.getInstance().find(idGroup);
        for (User user : group.get().getStudents()) {
            userNameList.add(user.getLogin());
        }
        return userNameList;
    }

    public List<String> getUserNameListFromHibernate(String groupName) {
        List<String> userNameList = new ArrayList<>();

        final Optional<Group> group = groupService.findByGroupName(groupName);
        if (group.isPresent()) {
            userNameList = group.get().getStudents()
                    .stream()
                    .map(User::getLogin)
                    .collect(Collectors.toList());
        }
        return userNameList;
    }

    public Optional<Group> getGroupByName(String groupName) {
        final Integer idGroupByName = GroupsRepositoryPostgres.getInstance().getIdGroupByName(groupName);
        if (idGroupByName != null) {
            return GroupsRepositoryPostgres.getInstance().find(idGroupByName);
        }
        return Optional.empty();
    }

    public Optional<Group> getGroupByNameFromHibernate(String groupName) {
        return groupService.findByGroupName(groupName);
    }
}
