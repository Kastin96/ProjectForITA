package com.example.controllerservice.lifecycle;

import com.example.database.usersrepository.StudentRepositoryPostgres;
import com.example.users.Student;

public class RegistrationService {

    public static boolean registration(String login, String password, String fullName, Integer age){
        return StudentRepositoryPostgres.getInstance().save(new Student()
                .withLogin(login)
                .withPassword(password)
                .withFullName(fullName)
                .withAge(age));
    }
}
