package com.example.database;

import com.example.groups.Group;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("!!!");
        final Optional<Group> group = GroupsRepositoryPostgres.getInstance().find(5);
        System.out.println(group);



//        System.out.println("!!!");
//        final Group testerRRRR_group = new Group()
//                .withTrainer(TrainerRepositoryPostgres.getInstance().find(2).get())
//                .withGroupName("testerRRRR_GROUP2222")
//                .withUserList(Set.of(StudentRepositoryPostgres.getInstance().find(28).get(),
//                        StudentRepositoryPostgres.getInstance().find(25).get(),
//                        StudentRepositoryPostgres.getInstance().find(42).get(),
//                        StudentRepositoryPostgres.getInstance().find(8).get()));
//
//        final boolean save = GroupsRepositoryPostgres.getInstance().save(testerRRRR_group);
//        System.out.println(save);


        System.out.println("!!!");
        final List<Group> all = GroupsRepositoryPostgres.getInstance().findAll();
        all.stream().forEach(System.out::println);

        final boolean remove = GroupsRepositoryPostgres.getInstance().remove(13);
        System.out.println(remove);
    }
}
