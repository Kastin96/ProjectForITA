package com.example.database.usersrepository;

import com.example.users.Student;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;

@Component
public class StudentRepositoryHibernate extends AbstractRepositoryHibernate<Student> {

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
