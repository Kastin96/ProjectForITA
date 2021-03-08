package com.example.database.usersrepository;


import com.example.database.groupsrepository.GroupsRepositoryHibernate;
import com.example.groups.Group;
import com.example.users.BasicUser;
import com.example.users.Student;
import com.example.users.Trainer;

import java.util.Optional;
import java.util.Set;

public class demoAddGroup {
    public static void main(String[] args) {

//        final Optional<Group> group = GroupsRepositoryHibernate.getInstance().find(16);
//        final Optional<Student> student = StudentRepositoryHibernate.getInstance().find(39);
//        student.get().getGroups().add(group.get());
//        StudentRepositoryHibernate.getInstance().save(student.get());
//
//        final Optional<Student> student2 = StudentRepositoryHibernate.getInstance().find(40);
//        student2.get().getGroups().add(group.get());
//        StudentRepositoryHibernate.getInstance().save(student2.get());
//
//        final Optional<Student> student3 = StudentRepositoryHibernate.getInstance().find(41);
//        student3.get().getGroups().add(group.get());
//        StudentRepositoryHibernate.getInstance().save(student3.get());
//
//        if (group.isPresent()){
//            final Set<Student> students = group.get().getStudents();
//            students.add(student.get());
//            students.add(student2.get());
//            students.add(student3.get());
//            group.get().setStudents(students);
//            final Set<Student> studentSet = group.get().getStudents();
//            studentSet.forEach(System.out::println);
//
//            GroupsRepositoryHibernate.getInstance().save(group.get());
//        }

//        final Optional<BasicUser> trainer1 = BasicUserRepositoryHibernate.getInstance().findByLogin("trainer99");
//        trainer1.ifPresent(System.out::println);

        final Optional<Group> testGroup1 = GroupsRepositoryHibernate.getInstance().findByGroupName("testGroup99");
        testGroup1.ifPresent(System.out::println);

    }
}
