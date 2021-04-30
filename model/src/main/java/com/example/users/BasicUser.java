package com.example.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "users")
@Component
public class BasicUser extends User {

    public BasicUser withId(Integer id) {
        setId(id);
        return this;
    }

    public BasicUser withLogin(String login) {
        setLogin(login);
        return this;
    }

    public BasicUser withPassword(String password) {
        setPassword(password);
        return this;
    }

    public BasicUser withRole(String role) {
        setRole(role);
        return this;
    }

    public BasicUser withRoleNumber(Integer role) {
        setRoleNumber(role);
        return this;
    }
}
