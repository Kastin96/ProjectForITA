package com.example.hardcoremetod;

import com.example.localdatabase.GroupsDatabase;
import com.example.localdatabase.UserDatabase;
import com.example.groups.Group;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HardcoreMethod {

    public static void run(int amountUsers, int amountTrainers) {

        List<User> studentsList = new ArrayList<>();
        for (int i = 0; i < amountUsers; i++) {
            Student student = new Student("test" + i,
                    "test" + i,
                    "testTest" + i,
                    i);

            studentsList.add(student);
            UserDatabase.getInstance().put(student.getId(), student);
        }

        List<User> trainersList = new ArrayList<>();
        for (int i = 0; i < amountTrainers; i++) {
            Trainer trainer = new Trainer("testTrainer" + i,
                    "testTrainer" + i,
                    "testTrainerTestTrainer" + i,
                    i, 100, 200, 300);

            trainersList.add(trainer);
            UserDatabase.getInstance().put(trainer.getId(), trainer);
        }

        Group testGroup1 = new Group("testGroup1",
                (Trainer) trainersList.get(0),
                studentsList.subList(0, amountUsers / 2));
        Group testGroup2 = new Group("testGroup2",
                (Trainer) trainersList.get(1),
                studentsList.subList(amountUsers / 2, amountUsers));

        GroupsDatabase.getInstance().put(testGroup1.getId(), testGroup1);
        GroupsDatabase.getInstance().put(testGroup2.getId(), testGroup2);
    }

    public static void generateEmpty(int studentLimit, int trainerLimit) {
        generateEmptyStudent(studentLimit);
        generateEmptyTrainer(trainerLimit);
    }

    public static void generateEmptyStudent(int studentsLimit) {
        List<Student> collectedStudent = Stream
                .generate(Student::new)
                .limit(studentsLimit)
                .collect(Collectors.toList());

        collectedStudent.forEach(student -> UserDatabase
                .getInstance()
                .put(student.getId(), student)
        );
    }

    public static void generateEmptyTrainer(int trainersLimit) {

        List<Trainer> collectedStudent = Stream
                .generate(Trainer::new)
                .limit(trainersLimit)
                .collect(Collectors.toList());

        collectedStudent.forEach(trainer -> UserDatabase
                .getInstance()
                .put(trainer.getId(), trainer)
        );
    }
}
