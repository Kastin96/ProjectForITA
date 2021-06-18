package com.example.database.hibernate;

import com.example.dao.StudentRepository;
import com.example.users.Student;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;

@Component
public class StudentRepositoryHibernate extends AbstractRepositoryHibernate<Student> implements StudentRepository {

    @Override
    protected TypedQuery<Student> getQuery() {
        return helper.getEntityManager()
                .createQuery("from Student where id = :id", Student.class);
    }

    @Override
    protected TypedQuery<Student> getAllQuery() {
        return helper.getEntityManager()
                .createQuery("from Student", Student.class);
    }
}
