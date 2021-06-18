package com.example.repositoryaccess;

import com.example.groups.Group;
import com.example.users.Student;
import com.example.users.Trainer;

import java.util.List;
import java.util.Optional;

public interface GroupService extends RepositoryAccess<Group> {
    List<String> findAllGroupName();
    Optional<List<Group>> findAllGroupByUser(Student student);
    Optional<List<Group>> findAllGroupByUser(Trainer trainer);
    Optional<Group> findByGroupName(String groupName);
}
