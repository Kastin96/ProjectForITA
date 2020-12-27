package com.example.hardcoremetod;

import com.example.database.GroupsDatabase;
import com.example.database.UserDatabase;
import com.example.groups.Group;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HardcoreMethod {

    public static void run() {
        Student student = new Student("test", "test", "testTest", 101);
        Student student2 = new Student("test2", "test2", "testTest2", 102);
        Student student3 = new Student("test3", "test3", "testTest3", 103);

        UserDatabase.getInstance().put(student.getId(), student);
        UserDatabase.getInstance().put(student2.getId(), student2);
        UserDatabase.getInstance().put(student3.getId(), student3);

        List<Integer> salaryList = new ArrayList<>();
        salaryList.add(100);
        salaryList.add(200);
        salaryList.add(300);
        salaryList.add(400);
        salaryList.add(500);
        salaryList.add(600);

        int ageTrainer = 50;

        Trainer trainer = new Trainer("testTrainer", "testTrainer",
                "testTrainerTestTrainer", ageTrainer, salaryList);

        UserDatabase.getInstance().put(trainer.getId(), trainer);

        List<User> userList = new ArrayList<>();
        userList.add(student);
        userList.add(student2);
        userList.add(student3);

//        Group testGroup = new Group("testGroup", trainer, userList);
//
//        GroupsDatabase.getInstance().put(testGroup.getId(), testGroup);

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
