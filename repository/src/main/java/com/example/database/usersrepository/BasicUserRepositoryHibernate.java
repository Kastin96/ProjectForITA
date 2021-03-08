package com.example.database.usersrepository;

import com.example.users.BasicUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class BasicUserRepositoryHibernate extends AbstractRepositoryHibernate<BasicUser> {
    private static volatile BasicUserRepositoryHibernate instance;

    public BasicUserRepositoryHibernate() {
    }

    public static BasicUserRepositoryHibernate getInstance() {
        if (instance == null) {
            synchronized (BasicUserRepositoryHibernate.class) {
                if (instance == null) {
                    instance = new BasicUserRepositoryHibernate();
                }
            }
        }
        return instance;
    }

    @Override
    protected TypedQuery<BasicUser> getQuery() {
        return helper.getEntityManager()
                .createQuery("from BasicUser where id = :id", BasicUser.class);
    }

    @Override
    protected TypedQuery<BasicUser> getAllQuery() {
        return helper.getEntityManager()
                .createQuery("from BasicUser", BasicUser.class);
    }

    public Optional<BasicUser> findByLogin(String login) {
        Optional<BasicUser> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<BasicUser> criteriaQuery = criteriaBuilder.createQuery(BasicUser.class);
        final Root<BasicUser> userRoot = criteriaQuery.from(BasicUser.class);

        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("login"), login));

        result = Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());

        transaction.commit();
        entityManager.close();

        return result;
    }
}
