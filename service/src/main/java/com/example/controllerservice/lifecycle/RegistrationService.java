package com.example.controllerservice.lifecycle;

import com.example.repositoryaccess.StudentService;
import com.example.users.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {
    public StudentService studentService;

    public boolean registrationStudent(String login, String password, String fullName, Integer age) {
        return studentService.save(Student.builder()
                .login(login)
                .password(password)
                .fullName(fullName)
                .age(age)
                .roleNumber(3)
                .build());
    }
}
