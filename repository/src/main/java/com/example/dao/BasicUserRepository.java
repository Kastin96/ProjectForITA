package com.example.dao;

import com.example.users.BasicUser;

import java.util.Optional;

public interface BasicUserRepository extends Repository<BasicUser> {
    Optional<BasicUser> findByLogin(String login);
}
