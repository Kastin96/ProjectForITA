package com.example.users;

import java.util.Objects;

public abstract class User {

    private String login;
    private String password;
    private String fullName;
    private int age;

    public User() {
    }

    public User(String login, String password, String fullName, int age) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(fullName, user.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                '}';
    }
}
