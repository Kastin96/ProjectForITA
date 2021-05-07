package com.example.database.hibernate;

import com.example.database.EntityManagerHelper;
import com.example.dao.Repository;
import com.example.aspects.JpaTransaction;
import com.example.users.Trainer;
import com.example.users.User;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public abstract class AbstractRepositoryHibernate<T extends User> implements Repository<T> {
    protected EntityManagerHelper helper;

    protected abstract TypedQuery<T> getQuery();

    protected abstract TypedQuery<T> getAllQuery();

    @Autowired
    public void setHelper(EntityManagerHelper helper) {
        this.helper = helper;
    }

    @Override
    @JpaTransaction
    public Optional<T> find(Integer id) {
        Optional<T> result;

        result = Optional.ofNullable(getQuery().setParameter("id", id).getSingleResult());

        return result;
    }

    @Override
    @JpaTransaction
    public List<T> findAll() {
        List<T> result;

        result = getAllQuery().getResultList();

        return result;
    }

    @Override
    @JpaTransaction
    public boolean save(T entity) {
        try {
            EntityManager entityManager = helper.getEntityManager();

            if (entity.getId() == null) {
                entityManager.persist(entity);
            } else {
                entityManager.merge(entity);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @JpaTransaction
    public void remove(Integer id) {
        EntityManager entityManager = helper.getEntityManager();

        final Optional<T> result = find(id);
        result.ifPresent(entityManager::remove);
    }
}
