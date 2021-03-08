package com.example.users;

import com.example.groups.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.LinkedHashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "users")
public class Student extends User {

    @Column(name = "full_name")
    private String fullName;
    private Integer age;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups = new LinkedHashSet<>();

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

    public Student withRoleNumber(Integer role) {
        setRoleNumber(role);
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

    public Student withGroups(Set<Group> groups){
        setGroups(groups);
        return this;
    }

    public Student withAge(Integer age) {
        setAge(age);
        return this;
    }

}
