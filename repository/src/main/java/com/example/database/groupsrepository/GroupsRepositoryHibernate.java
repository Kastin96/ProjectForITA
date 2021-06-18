package com.example.database.groupsrepository;

import com.example.groups.Group;
import com.example.users.Student;
import com.example.users.Trainer;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
public class GroupsRepositoryHibernate extends AbstractGroupRepositoryHibernate<Group> {

    @Override
    protected TypedQuery<Group> getQuery() {
        return helper.getEntityManager()
                .createQuery("from Group where id = :id", Group.class);
    }

    @Override
    protected TypedQuery<Group> getAllQuery() {
        return helper.getEntityManager()
                .createQuery("from Group", Group.class);
    }

    public List<String> findAllGroupName() {
        List<String> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        final Root<Group> groupRoot = criteriaQuery.from(Group.class);

        final CompoundSelection<String> groupName = criteriaBuilder.construct(String.class, groupRoot.get("groupName"));

        criteriaQuery.select(groupName);

        result = entityManager.createQuery(criteriaQuery).getResultList();

        transaction.commit();
        entityManager.close();

        return result;
    }

    public Optional<List<Group>> findAllGroupByUser(Student student) {
        Optional<List<Group>> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        final Root<Group> groupRoot = criteriaQuery.from(Group.class);

        criteriaQuery.select(groupRoot).where(criteriaBuilder.isMember(student, groupRoot.get("students")));

        result = Optional.ofNullable(entityManager.createQuery(criteriaQuery).getResultList());

        transaction.commit();
        entityManager.close();

        return result;
    }

    public Optional<List<Group>> findAllGroupByUser(Trainer trainer) {
        Optional<List<Group>> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        final Root<Group> groupRoot = criteriaQuery.from(Group.class);

        criteriaQuery.select(groupRoot).where(criteriaBuilder.equal(groupRoot.get("trainer"), trainer));

        result = Optional.ofNullable(entityManager.createQuery(criteriaQuery).getResultList());

        transaction.commit();
        entityManager.close();

        return result;
    }

    public Optional<Group> findByGroupName(String groupName) {
        Optional<Group> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        final Root<Group> groupRoot = criteriaQuery.from(Group.class);

        criteriaQuery.select(groupRoot).where(criteriaBuilder.equal(groupRoot.get("groupName"), groupName));

        result = Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());

        transaction.commit();
        entityManager.close();

        return result;
    }
}
