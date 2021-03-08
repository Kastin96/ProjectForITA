package com.example.database;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    Optional<T> find(Integer id);

    List<T> findAll();

    boolean save(T entity);

    void remove(Integer id);
}
