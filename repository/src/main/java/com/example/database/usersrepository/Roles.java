package com.example.database.usersrepository;

public enum Roles {
    ADMIN("admin", 1),
    TRAINER("trainer", 2),
    STUDENT("student", 3);

    private final String role;
    private final Integer roleNumber;


    Roles(String role, Integer roleNumber) {
        this.role = role;
        this.roleNumber = roleNumber;
    }

    public String getRole() {
        return role;
    }

    public Integer getRoleNumber() {
        return roleNumber;
    }
}
