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
public class Administrator extends User {

    public Administrator withId(Integer id) {
        setId(id);
        return this;
    }

    public Administrator withLogin(String login) {
        setLogin(login);
        return this;
    }

    public Administrator withPassword(String password) {
        setPassword(password);
        return this;
    }

    public Administrator withRole(String role) {
        setRole(role);
        return this;
    }

    public Administrator withRoleNumber(Integer role) {
        setRoleNumber(role);
        return this;
    }
}
