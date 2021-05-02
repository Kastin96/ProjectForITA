package com.example.database.hibernate;

import com.example.dao.AdministratorRepository;
import com.example.users.Administrator;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;

@Component
public class AdministratorRepositoryHibernate extends AbstractRepositoryHibernate<Administrator> implements AdministratorRepository {

    @Override
    protected TypedQuery<Administrator> getQuery() {
        return helper.getEntityManager()
                .createQuery("from Administrator where id = :id", Administrator.class);
    }

    @Override
    protected TypedQuery<Administrator> getAllQuery() {
        return helper.getEntityManager()
                .createQuery("from Administrator", Administrator.class);
    }
}
