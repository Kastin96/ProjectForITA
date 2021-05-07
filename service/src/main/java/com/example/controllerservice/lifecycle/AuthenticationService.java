package com.example.controllerservice.lifecycle;

import com.example.controllerservice.basicuser.DispenserUserWithRole;
import com.example.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private DispenserUserWithRole dispenserUserWithRole;

    public Optional<? extends User> getUserByLogin(String login, String password) {

        final Optional<? extends User> user = dispenserUserWithRole.getUserByLogin(login);
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
