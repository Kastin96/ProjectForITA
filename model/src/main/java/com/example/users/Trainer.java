package com.example.users;

import com.example.groups.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CollectionType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "users")
public class Trainer extends User {

    @Column(name = "full_name")
    private String fullName;
    private Integer age;

    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "trainer_salary",
            joinColumns = @JoinColumn(name = "trainer_id")
    )
    @Column(name = "salary")
    private List<Integer> salaryList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "trainer_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;

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

    public Trainer withRoleNumber(Integer role) {
        setRoleNumber(role);
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

    public Trainer withGroup(Group group){
        setGroup(group);
        return this;
    }

    public Trainer withAge(Integer age) {
        setAge(age);
        return this;
    }
}
