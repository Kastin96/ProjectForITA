package com.example.database.usersrepository;

import com.example.users.Student;
import com.example.users.Trainer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class TrainerRepositoryHibernate extends AbstractRepositoryHibernate<Trainer> {
    private static volatile TrainerRepositoryHibernate instance;

    public TrainerRepositoryHibernate() {
    }

    public static TrainerRepositoryHibernate getInstance() {
        if (instance == null) {
            synchronized (TrainerRepositoryHibernate.class) {
                if (instance == null) {
                    instance = new TrainerRepositoryHibernate();
                }
            }
        }
        return instance;
    }

    @Override
    protected TypedQuery<Trainer> getQuery() {
        return helper.getEntityManager()
                .createQuery("from Trainer where id = :id", Trainer.class);
    }

    @Override
    protected TypedQuery<Trainer> getAllQuery() {
        return helper.getEntityManager()
                .createQuery("from Trainer", Trainer.class);
    }
}
