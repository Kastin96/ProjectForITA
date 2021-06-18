package com.example.database.usersrepository;

import com.example.users.Administrator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;

@Slf4j
@Component
public class AdministratorRepositoryHibernate extends AbstractRepositoryHibernate<Administrator> {

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
