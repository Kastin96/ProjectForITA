package com.example.repositoryaccess;

import com.example.dao.StudentRepository;
import com.example.users.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;

    @Override
    public Optional<Student> find(Integer id) {
        return studentRepository.find(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public boolean save(Student entity) {
        return studentRepository.save(entity);
    }

    @Override
    public void remove(Integer id) {
        studentRepository.remove(id);
    }
}
