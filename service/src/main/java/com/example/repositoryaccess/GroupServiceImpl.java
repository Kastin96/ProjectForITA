package com.example.repositoryaccess;

import com.example.dao.GroupRepository;
import com.example.groups.Group;
import com.example.users.Student;
import com.example.users.Trainer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GroupServiceImpl implements GroupService{
    public GroupRepository groupRepository;

    @Override
    public Optional<Group> find(Integer id) {
        return groupRepository.find(id);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public boolean save(Group entity) {
        return groupRepository.save(entity);
    }

    @Override
    public void remove(Integer id) {
        groupRepository.remove(id);
    }

    @Override
    public List<String> findAllGroupName() {
        return groupRepository.findAllGroupName();
    }

    @Override
    public Optional<List<Group>> findAllGroupByUser(Student student) {
        return groupRepository.findAllGroupByUser(student);
    }

    @Override
    public Optional<List<Group>> findAllGroupByUser(Trainer trainer) {
        return groupRepository.findAllGroupByUser(trainer);
    }

    @Override
    public Optional<Group> findByGroupName(String groupName) {
        return groupRepository.findByGroupName(groupName);
    }
}
