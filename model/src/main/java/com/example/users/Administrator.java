package com.example.users;

import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Administrator extends User {

    public Administrator withId(Integer id){
        setId(id);
        return this;
    }

    public Administrator withLogin(String login){
        setLogin(login);
        return this;
    }

    public Administrator withPassword(String password){
        setPassword(password);
        return this;
    }

    public Administrator withRole(String role) {
        setRole(role);
        return this;
    }
}
