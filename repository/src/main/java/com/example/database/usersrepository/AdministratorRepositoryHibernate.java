package com.example.database.usersrepository;

import com.example.users.Administrator;

import javax.persistence.TypedQuery;

public class AdministratorRepositoryHibernate extends AbstractRepositoryHibernate<Administrator> {
    private static volatile AdministratorRepositoryHibernate instance;

    public AdministratorRepositoryHibernate() {
    }

    public static AdministratorRepositoryHibernate getInstance() {
        if (instance == null) {
            synchronized (AdministratorRepositoryHibernate.class) {
                if (instance == null) {
                    instance = new AdministratorRepositoryHibernate();
                }
            }
        }
        return instance;
    }

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
