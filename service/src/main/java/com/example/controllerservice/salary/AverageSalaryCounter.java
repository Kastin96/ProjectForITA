package com.example.controllerservice.salary;

import com.example.database.usersrepository.TrainerRepositoryHibernate;
import com.example.database.usersrepository.TrainerRepositoryPostgres;
import com.example.salary.AverageSalary;
import com.example.users.Trainer;
import com.example.users.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AverageSalaryCounter {
    public static BigDecimal count(User user) {
        return TrainerRepositoryPostgres.getInstance().getAverageSalary(user.getId());
    }

    public static BigDecimal countByHibernate(User user) {
        final Optional<Trainer> trainer = TrainerRepositoryHibernate.getInstance().find(user.getId());
        if (trainer.isPresent()){
            final List<Integer> salaryList = trainer.get().getSalaryList();
            if (!salaryList.isEmpty()){
                return AverageSalary.count(salaryList);
            }
        }
        return count(user);
    }
}
