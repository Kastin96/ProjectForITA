package com.example.database.usersrepository;

import com.example.users.Administrator;
import com.example.users.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class AdministratorRepositoryHibernate extends AbstractRepositoryHibernate<Administrator>{
    private static volatile AdministratorRepositoryHibernate instance;

    public AdministratorRepositoryHibernate() {
    }

    public static AdministratorRepositoryHibernate getInstance(){
        if (instance == null){
            synchronized (AdministratorRepositoryHibernate.class){
                if (instance == null){
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
