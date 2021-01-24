package com.example.database.usersrepository;

public enum Roles {
    ADMIN("admin"),
    TRAINER("trainer"),
    STUDENT("student");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
