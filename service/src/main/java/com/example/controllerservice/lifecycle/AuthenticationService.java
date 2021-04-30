package com.example.controllerservice.lifecycle;

import com.example.database.usersrepository.BasicUserRepositoryHibernate;
import com.example.database.usersrepository.UserRepositoryPostgres;
import com.example.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private BasicUserRepositoryHibernate basicUserRepository;

    public Optional<? extends User> getUserByLogin(String login, String password) {

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

    public Optional<? extends User> getUserByLoginFromHibernate(String login, String password) {

        final Optional<? extends User> user = UserRepositoryPostgres.getInstance().getUserByLogin(login);
//        final Optional<BasicUser> user = basicUserRepository.findByLogin(login);
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
