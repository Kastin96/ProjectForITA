package com.example.database;

import com.example.groups.Group;

import java.util.List;
import java.util.Optional;

public class Groups implements Dao<Group>{
    @Override
    public Optional<Group> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Group> getAll() {
        return null;
    }

    @Override
    public void save(Group group) {

    }

    @Override
    public void update(Group group) {

    }

    @Override
    public void delete(Group group) {

    }
}
