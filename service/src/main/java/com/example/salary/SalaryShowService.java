package com.example.salary;

import com.example.database.usersrepository.TrainerRepositoryPostgres;
import com.example.users.Trainer;
import com.example.users.User;

import java.util.List;
import java.util.Optional;

public class SalaryShowService {

    public static List<Integer> getSalaryList(User user) {
        return TrainerRepositoryPostgres.getInstance().getSalaryList(user.getId());
    }

    public static List<Integer> getSalaryListByHibernate(User user) {
        final Optional<Trainer> trainer = TrainerRepositoryPostgres.getInstance().find(user.getId());
        return trainer.map(Trainer::getSalaryList).orElse(getSalaryList(user));
    }
}
