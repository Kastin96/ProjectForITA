package com.example.users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Trainer extends User {

    private List<Integer> salaryList = new ArrayList<>();

    public Trainer() {
    }

    public Trainer(String login, String password, String fullName, int age, List<Integer> salaryList) {
        super(login, password, fullName, age);
        this.salaryList = salaryList;
    }

    public Trainer(String login, String password, String fullName, int age, Integer... salaries) {
        super(login, password, fullName, age);
        this.salaryList.addAll(Arrays.asList(salaries));
    }

    public void addSalary(int salary) {
        this.salaryList.add(salary);
    }

    public List<Integer> getSalaryList() {
        return this.salaryList;
    }

    public void setSalaryList(List<Integer> salaryList) {
        this.salaryList = salaryList;
    }
}
