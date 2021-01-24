package com.example.salary;

import com.example.database.usersrepository.TrainerRepositoryPostgres;
import com.example.users.User;

import java.util.List;

public class SalaryShowService {

    public static List<Integer> getSalaryList(User user) {
        return TrainerRepositoryPostgres.getInstance().getSalaryList(user.getId());
    }
}
