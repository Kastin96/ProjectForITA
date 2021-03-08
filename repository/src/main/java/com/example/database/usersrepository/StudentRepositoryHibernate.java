package com.example.database.usersrepository;

import com.example.users.Administrator;
import com.example.users.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class StudentRepositoryHibernate extends AbstractRepositoryHibernate<Student>{
    private static volatile StudentRepositoryHibernate instance;

    public StudentRepositoryHibernate() {
    }

    public static StudentRepositoryHibernate getInstance(){
        if (instance == null){
            synchronized (StudentRepositoryHibernate.class){
                if (instance == null){
                    instance = new StudentRepositoryHibernate();
                }
            }
        }
        return instance;
    }

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
