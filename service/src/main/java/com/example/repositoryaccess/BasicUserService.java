package com.example.repositoryaccess;

import com.example.users.BasicUser;

import java.util.Optional;

public interface BasicUserService extends RepositoryAccess<BasicUser> {
    Optional<BasicUser> findByLogin(String login);
}
