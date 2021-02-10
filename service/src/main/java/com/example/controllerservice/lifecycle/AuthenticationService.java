package com.example.controllerservice.lifecycle;

import com.example.database.usersrepository.UserRepositoryPostgres;
import com.example.users.User;

import java.util.Optional;

public class AuthenticationService {

    public static Optional<? extends User> getUserByLogin(String login, String password) {

        final Optional<? extends User> user = UserRepositoryPostgres.getInstance().getUserByLogin(login);
        if (user.isPresent()) {
            if (user.get().getLogin().equalsIgnoreCase(login)) {
                if (user.get().getPassword().equalsIgnoreCase(password)) {
                    return user;
                }
            }
        }
        return Optional.empty();
    }
}
