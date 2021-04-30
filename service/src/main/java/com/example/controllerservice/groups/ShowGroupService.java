package com.example.controllerservice.groups;

import com.example.database.groupsrepository.GroupsRepositoryHibernate;
import com.example.database.groupsrepository.GroupsRepositoryPostgres;
import com.example.groups.Group;
import com.example.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowGroupService {
    @Autowired
    private GroupsRepositoryHibernate groupRepository;

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

        final Optional<Group> group = groupRepository.findByGroupName(groupName);
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
        return groupRepository.findByGroupName(groupName);
    }
}
