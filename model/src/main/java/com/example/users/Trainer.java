package com.example.users;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Trainer extends User {

    private String fullName;
    private int age;

    private List<Integer> salaryList = new ArrayList<>();

    public void addSalary(int salary) {
        this.salaryList.add(salary);
    }

    public Trainer withId(Integer id) {
        setId(id);
        return this;
    }

    public Trainer withLogin(String login) {
        setLogin(login);
        return this;
    }

    public Trainer withPassword(String password) {
        setPassword(password);
        return this;
    }

    public Trainer withRole(String role) {
        setRole(role);
        return this;
    }

    public Trainer withFullName(String fullName) {
        setFullName(fullName);
        return this;
    }

    public Trainer withSalaryList(List<Integer> salaryList) {
        setSalaryList(salaryList);
        return this;
    }

    public Trainer withAge(int age) {
        setAge(age);
        return this;
    }
}
