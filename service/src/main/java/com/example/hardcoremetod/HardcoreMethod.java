package com.example.hardcoremetod;

import com.example.database.groupsrepository.GroupsRepositoryPostgres;
import com.example.database.usersrepository.*;
import com.example.groups.Group;
import com.example.users.Student;
import com.example.users.Trainer;

import java.util.*;

public class HardcoreMethod {

    public static void run(Integer amountStudent, Integer amountTrainer) {

        for (int i = 1; i <= amountStudent; i++) {
            StudentRepositoryPostgres.getInstance().save(new Student()
                    .withLogin("user" + i)
                    .withPassword("user" + i)
                    .withFullName("TEST User" + i)
                    .withAge(i + 10));
        }

        for (int i = 1; i <= amountTrainer; i++) {
            TrainerRepositoryPostgres.getInstance().save(new Trainer()
                    .withLogin("trainer" + i)
                    .withPassword("trainer" + i)
                    .withFullName("TEST Trainer" + i)
                    .withAge(i + 20));

            final Optional<Trainer> personByLogin = TrainerRepositoryPostgres.getInstance()
                    .getPersonByLogin("trainer" + i);

            TrainerRepositoryPostgres.getInstance().addSalary(personByLogin.get().getId(), 1000);
            TrainerRepositoryPostgres.getInstance().addSalary(personByLogin.get().getId(), 1200);
            TrainerRepositoryPostgres.getInstance().addSalary(personByLogin.get().getId(), 1500);
        }

        for (int i = 1; i <= amountStudent / amountTrainer; i++) {
            int index = 1;
            GroupsRepositoryPostgres.getInstance().save(new Group()
                    .withGroupName("testGroup" + i)
                    .withTrainer(TrainerRepositoryPostgres.getInstance().getPersonByLogin("trainer" + i).get())
                    .withUserList(Set.of(UserRepositoryPostgres.getInstance().getUserByLogin("user" + index++).get(),
                            UserRepositoryPostgres.getInstance().getUserByLogin("user" + index++).get(),
                            UserRepositoryPostgres.getInstance().getUserByLogin("user" + index++).get(),
                            UserRepositoryPostgres.getInstance().getUserByLogin("user" + index++).get(),
                            UserRepositoryPostgres.getInstance().getUserByLogin("user" + index).get())));
        }

    }


}
