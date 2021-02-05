package com.example.database;

import com.example.users.User;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    Optional<T> find(Integer id);

    List<T> findAll();

    boolean save(T entity);

    //    boolean update(T entity);

    boolean remove(Integer id);
}
