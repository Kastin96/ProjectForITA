package com.example.database.usersrepository;

import com.example.users.Trainer;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;

@Component
public class TrainerRepositoryHibernate extends AbstractRepositoryHibernate<Trainer> {

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
