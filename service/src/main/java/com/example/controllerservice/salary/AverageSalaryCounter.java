package com.example.controllerservice.salary;

import com.example.database.usersrepository.TrainerRepositoryPostgres;
import com.example.users.Trainer;
import com.example.users.User;

import java.math.BigDecimal;

public class AverageSalaryCounter {
    public static BigDecimal count(User user) {
        return TrainerRepositoryPostgres.getInstance().getAverageSalary(user.getId());
    }
}
