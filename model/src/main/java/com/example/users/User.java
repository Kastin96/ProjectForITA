package com.example.users;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {
    private Integer id;
    private String login;
    private String password;
    private String role;
}
