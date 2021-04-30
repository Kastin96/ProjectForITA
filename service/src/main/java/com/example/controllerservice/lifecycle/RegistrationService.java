package com.example.controllerservice.lifecycle;

import com.example.database.usersrepository.StudentRepositoryHibernate;
import com.example.database.usersrepository.StudentRepositoryPostgres;
import com.example.users.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private StudentRepositoryHibernate studentRepository;

    public boolean registrationStudentByPostgres(String login, String password, String fullName, Integer age) {
        return StudentRepositoryPostgres.getInstance().save(new Student()
                .withLogin(login)
                .withPassword(password)
                .withFullName(fullName)
                .withAge(age));
    }

    public boolean registrationStudentByHibernate(String login, String password, String fullName, Integer age) {
        return studentRepository.save(new Student()
                .withRoleNumber(3)
                .withLogin(login)
                .withPassword(password)
                .withFullName(fullName)
                .withAge(age));
    }
}
