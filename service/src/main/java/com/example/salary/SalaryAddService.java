package com.example.salary;

import com.example.database.usersrepository.TrainerRepositoryHibernate;
import com.example.database.usersrepository.TrainerRepositoryPostgres;
import com.example.users.Trainer;
import com.example.users.User;

import java.util.List;
import java.util.Optional;

public class SalaryAddService {

    public static boolean add(User user, Integer salary) {
        return TrainerRepositoryPostgres.getInstance().addSalary(user.getId(), salary);
    }

    public static boolean addByHibernate(User user, Integer salary) {
        final Optional<Trainer> foundedTrainer = TrainerRepositoryHibernate.getInstance().find(user.getId());
        if (foundedTrainer.isPresent()) {
            final Trainer trainer = foundedTrainer.get();
            final List<Integer> salaryList = trainer.getSalaryList();
            salaryList.add(salary);
            trainer.setSalaryList(salaryList);
            return TrainerRepositoryHibernate.getInstance().save(trainer);
        }
        return false;
    }
}
