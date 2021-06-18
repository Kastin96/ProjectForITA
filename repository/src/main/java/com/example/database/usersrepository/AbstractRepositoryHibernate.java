package com.example.database.usersrepository;

import com.example.database.EntityManagerHelper;
import com.example.database.Repository;
import com.example.users.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Component
public abstract class AbstractRepositoryHibernate<T extends User> implements Repository<T> {
    protected final EntityManagerHelper helper = EntityManagerHelper.getInstance();

    protected abstract TypedQuery<T> getQuery();

    protected abstract TypedQuery<T> getAllQuery();

    @Override
    public Optional<T> find(Integer id) {
        Optional<T> result;

        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        result = Optional.ofNullable(getQuery().setParameter("id", id).getSingleResult());

        transaction.commit();

        entityManager.close();
        return result;
    }

    @Override
    public List<T> findAll() {
        List<T> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        result = getAllQuery().getResultList();

        transaction.commit();
        entityManager.close();
        return result;
    }

    @Override
    public boolean save(T entity) {
        try {
            final EntityManager entityManager = helper.getEntityManager();
            final EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            if (entity.getId() == null) {
                entityManager.persist(entity);
            } else {
                entityManager.merge(entity);
            }

            transaction.commit();
            entityManager.close();
            return true;
        } catch (PersistenceException exception) {
            return false;
        }
    }

    @Override
    public void remove(Integer id) {
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final Optional<T> result = find(id);
        result.ifPresent(entityManager::remove);

        transaction.commit();
        entityManager.close();
    }
}
