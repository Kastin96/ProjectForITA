package com.example.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Student extends User {

    private String fullName;
    private int age;

    public Student withId(Integer id) {
        setId(id);
        return this;
    }

    public Student withLogin(String login) {
        setLogin(login);
        return this;
    }

    public Student withPassword(String password) {
        setPassword(password);
        return this;
    }

    public Student withRole(String role) {
        setRole(role);
        return this;
    }

    public Student withFullName(String fullName) {
        setFullName(fullName);
        return this;
    }

    public Student withAge(int age) {
        setAge(age);
        return this;
    }

}
