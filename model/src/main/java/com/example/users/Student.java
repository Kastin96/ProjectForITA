package com.example.users;

public class Student extends User {

    public Student() {
    }

    public Student(String login, String password, String fullName, int age) {
        super(login, password, fullName, age);
    }
}
