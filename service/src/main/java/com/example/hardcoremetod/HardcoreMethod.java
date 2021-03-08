package com.example.hardcoremetod;

import com.example.database.groupsrepository.GroupsRepositoryPostgres;
import com.example.database.usersrepository.StudentRepositoryPostgres;
import com.example.database.usersrepository.TrainerRepositoryPostgres;
import com.example.database.usersrepository.UserRepositoryPostgres;
import com.example.groups.Group;
import com.example.users.Student;
import com.example.users.Trainer;

import java.util.Optional;
import java.util.Set;

public class HardcoreMethod {

    public static void run(Integer amountStudent, Integer amountTrainer) {


        for (int i = 1; i <= amountStudent; i++) {
            if (StudentRepositoryPostgres.getInstance().findAll().size() >= amountStudent) {
                break;
            }
            StudentRepositoryPostgres.getInstance().save(new Student()
                    .withLogin("user" + i)
                    .withPassword("user" + i)
                    .withFullName("TEST User" + i)
                    .withAge(i + 10));
        }

        for (int i = 1; i <= amountTrainer; i++) {
            if (TrainerRepositoryPostgres.getInstance().findAll().size() >= amountTrainer) {
                break;
            }
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
            if (GroupsRepositoryPostgres.getInstance().findAll().size() >= (amountStudent / amountTrainer)) {
                break;
            }
            int index = 1;
            GroupsRepositoryPostgres.getInstance().save(new Group()
                    .withGroupName("testGroup" + i)
                    .withTrainer(TrainerRepositoryPostgres.getInstance().getPersonByLogin("trainer" + i).get())
                    .withUsers(Set.of((Student) UserRepositoryPostgres.getInstance().getUserByLogin("user" + index++).get(),
                            (Student) UserRepositoryPostgres.getInstance().getUserByLogin("user" + index++).get(),
                            (Student) UserRepositoryPostgres.getInstance().getUserByLogin("user" + index++).get(),
                            (Student) UserRepositoryPostgres.getInstance().getUserByLogin("user" + index++).get(),
                            (Student) UserRepositoryPostgres.getInstance().getUserByLogin("user" + index).get())));
        }

    }


}
