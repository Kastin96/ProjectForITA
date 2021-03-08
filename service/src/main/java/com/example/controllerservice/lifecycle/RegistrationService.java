package com.example.controllerservice.lifecycle;

import com.example.database.usersrepository.StudentRepositoryHibernate;
import com.example.database.usersrepository.StudentRepositoryPostgres;
import com.example.users.Student;

public class RegistrationService {

    public static boolean registrationStudentByPostgres(String login, String password, String fullName, Integer age) {
        return StudentRepositoryPostgres.getInstance().save(new Student()
                .withLogin(login)
                .withPassword(password)
                .withFullName(fullName)
                .withAge(age));
    }

    public static boolean registrationStudentByHibernate(String login, String password, String fullName, Integer age) {
        return StudentRepositoryHibernate.getInstance().save(new Student()
                .withRoleNumber(3)
                .withLogin(login)
                .withPassword(password)
                .withFullName(fullName)
                .withAge(age));
    }
}
