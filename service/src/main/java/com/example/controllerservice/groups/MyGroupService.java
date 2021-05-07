package com.example.controllerservice.groups;

import com.example.groups.Group;
import com.example.repositoryaccess.GroupService;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MyGroupService {
    private GroupService groupService;

    public List<String> getGroupNameList(User user) {
        List<String> groupNameList = new ArrayList<>();
        if (user instanceof Student) {
            final Optional<List<Group>> allGroupByUser = groupService.findAllGroupByUser((Student) user);
            if (allGroupByUser.isPresent()) {
                for (Group group : allGroupByUser.get()) {
                    groupNameList.add(group.getGroupName());
                }
            }
        } else if (user instanceof Trainer) {
            final Optional<List<Group>> allGroupByUser = groupService.findAllGroupByUser((Trainer) user);
            if (allGroupByUser.isPresent()) {
                for (Group group : allGroupByUser.get()) {
                    groupNameList.add(group.getGroupName());
                }
            }
        } else {
            groupNameList = groupService.findAllGroupName();
        }
        return groupNameList;
    }
}
