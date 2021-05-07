package com.example.controllerservice.groups;

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
        return groupService.findByGroupName(groupName);
    }
}
