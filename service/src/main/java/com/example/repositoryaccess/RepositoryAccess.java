package com.example.repositoryaccess;

import java.util.List;
import java.util.Optional;

public interface RepositoryAccess<T> {

    Optional<T> find(Integer id);

    List<T> findAll();

    boolean save(T entity);

    void remove(Integer id);
}
