package com.example.groups;

import com.example.users.Trainer;
import com.example.users.User;
import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Group extends AbstractGroup {
    private String groupName;
    private Trainer trainer;
    private Set<User> userList = new LinkedHashSet<>();

    public Group withId(Integer id){
        setId(id);
        return this;
    }

    public Group withGroupName(String groupName){
        setGroupName(groupName);
        return this;
    }

    public Group withTrainer(Trainer trainer){
        setGroupName(groupName);
        return this;
    }

    public Group withUserList(Set<User> userList){
        setUserList(userList);
        return this;
    }
}
