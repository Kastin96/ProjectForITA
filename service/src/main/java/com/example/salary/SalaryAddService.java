package com.example.salary;

import com.example.database.usersrepository.TrainerRepositoryPostgres;
import com.example.users.User;

public class SalaryAddService {

    public static boolean add(User user, Integer salary) {
        return TrainerRepositoryPostgres.getInstance().addSalary(user.getId(), salary);
    }
}
