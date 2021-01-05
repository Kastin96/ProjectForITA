package com.example.groups;

import com.example.users.Trainer;
import com.example.users.User;

import java.util.List;
import java.util.UUID;

public class Group {
    private UUID id;
    private String groupName;
    private Trainer trainer;
    private List<User> userList;

    public Group() {
    }

    public Group(String groupName, Trainer trainer, List<User> userList) {
        this.id = UUID.randomUUID();
        this.groupName = groupName;
        this.trainer = trainer;
        this.userList = userList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
