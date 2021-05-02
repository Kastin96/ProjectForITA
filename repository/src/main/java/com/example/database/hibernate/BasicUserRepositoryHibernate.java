package com.example.database.hibernate;

import com.example.aspects.JpaTransaction;
import com.example.dao.BasicUserRepository;
import com.example.users.BasicUser;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Component
public class BasicUserRepositoryHibernate extends AbstractRepositoryHibernate<BasicUser> implements BasicUserRepository {

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

    @Override
    @JpaTransaction
    public Optional<BasicUser> findByLogin(String login) {
        Optional<BasicUser> result;
        EntityManager entityManager = helper.getEntityManager();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<BasicUser> criteriaQuery = criteriaBuilder.createQuery(BasicUser.class);
        final Root<BasicUser> userRoot = criteriaQuery.from(BasicUser.class);

        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("login"), login));

        result = Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());

        return result;
    }
}
