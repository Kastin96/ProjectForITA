package com.example.groups;

import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"students", "trainer"})
@ToString(callSuper = true,  exclude = {"students", "trainer"})
@Entity
@Table(name = "groups")
public class Group extends AbstractGroup implements Serializable {
    @Column(name = "group_name")
    private String groupName;

    @ManyToOne()
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToMany(mappedBy = "groups", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Student> students = new LinkedHashSet<>();

    public Group withId(Integer id) {
        setId(id);
        return this;
    }

    public Group withGroupName(String groupName) {
        setGroupName(groupName);
        return this;
    }

    public Group withTrainer(Trainer trainer) {
        setTrainer(trainer);
        return this;
    }

    public Group withUsers(Set<Student> students) {
        setStudents(students);
        return this;
    }
}
