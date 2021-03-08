package com.example.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Table(name = "users")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String password;
    @Column(name = "role")
    private Integer roleNumber;
    @Column(name = "role", insertable = false, updatable = false)
    private String role;
}
