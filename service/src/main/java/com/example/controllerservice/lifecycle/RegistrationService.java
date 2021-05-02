package com.example.controllerservice.lifecycle;

import com.example.repositoryaccess.StudentService;
import com.example.database.postgres.StudentRepositoryPostgres;
import com.example.users.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {
    public StudentService studentService;

    public boolean registrationStudentByPostgres(String login, String password, String fullName, Integer age) {
        return StudentRepositoryPostgres.getInstance().save(new Student()
                .withLogin(login)
                .withPassword(password)
                .withFullName(fullName)
                .withAge(age));
    }

    public boolean registrationStudentByHibernate(String login, String password, String fullName, Integer age) {
        return studentService.save(new Student()
                .withRoleNumber(3)
                .withLogin(login)
                .withPassword(password)
                .withFullName(fullName)
                .withAge(age));
    }
}
