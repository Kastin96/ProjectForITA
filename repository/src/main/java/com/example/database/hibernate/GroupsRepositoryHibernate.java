package com.example.database.hibernate;

import com.example.aspects.JpaTransaction;
import com.example.dao.GroupRepository;
import com.example.database.EntityManagerHelper;
import com.example.groups.Group;
import com.example.users.Student;
import com.example.users.Trainer;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class GroupsRepositoryHibernate implements GroupRepository {

    protected EntityManagerHelper helper;

    @Autowired
    public void setHelper(EntityManagerHelper helper) {
        this.helper = helper;
    }

    private TypedQuery<Group> getQuery() {
        return helper.getEntityManager()
                .createQuery("from Group where id = :id", Group.class);
    }

    private TypedQuery<Group> getAllQuery() {
        return helper.getEntityManager()
                .createQuery("from Group", Group.class);
    }

    @Override
    @JpaTransaction
    public Optional<Group> find(Integer id) {
        Optional<Group> result;

        result = Optional.ofNullable(getQuery().setParameter("id", id).getSingleResult());

        return result;
    }

    @Override
    @JpaTransaction
    public List<Group> findAll() {
        List<Group> result;

        result = getAllQuery().getResultList();

        return result;
    }

    @Override
    @JpaTransaction
    public boolean save(Group entity) {
        try {
            final EntityManager entityManager = helper.getEntityManager();

            final Set<Student> students = entity.getStudents();
            for (Student student : students) {
                entityManager.refresh(student);
            }

            if (entity.getId() == null) {
                entityManager.persist(entity);
            } else {
                entityManager.merge(entity);
            }

            return true;
        } catch (HibernateException he) {
            return false;
        }
    }

    @Override
    public void remove(Integer id) {
    }

    @Override
    @JpaTransaction
    public List<String> findAllGroupName() {
        List<String> result;
        final EntityManager entityManager = helper.getEntityManager();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        final Root<Group> groupRoot = criteriaQuery.from(Group.class);

        final CompoundSelection<String> groupName = criteriaBuilder.construct(String.class, groupRoot.get("groupName"));

        criteriaQuery.select(groupName);

        result = entityManager.createQuery(criteriaQuery).getResultList();

        return result;
    }

    @Override
    @JpaTransaction
    public Optional<List<Group>> findAllGroupByUser(Student student) {
        Optional<List<Group>> result;
        final EntityManager entityManager = helper.getEntityManager();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        final Root<Group> groupRoot = criteriaQuery.from(Group.class);

        criteriaQuery.select(groupRoot).where(criteriaBuilder.isMember(student, groupRoot.get("students")));

        result = Optional.ofNullable(entityManager.createQuery(criteriaQuery).getResultList());

        return result;
    }

    @Override
    @JpaTransaction
    public Optional<List<Group>> findAllGroupByUser(Trainer trainer) {
        Optional<List<Group>> result;
        final EntityManager entityManager = helper.getEntityManager();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        final Root<Group> groupRoot = criteriaQuery.from(Group.class);

        criteriaQuery.select(groupRoot).where(criteriaBuilder.equal(groupRoot.get("trainer"), trainer));

        result = Optional.ofNullable(entityManager.createQuery(criteriaQuery).getResultList());

        return result;
    }

    @Override
    @JpaTransaction
    public Optional<Group> findByGroupName(String groupName) {
        Optional<Group> result;
        EntityManager entityManager = helper.getEntityManager();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        final Root<Group> groupRoot = criteriaQuery.from(Group.class);

        criteriaQuery.select(groupRoot).where(criteriaBuilder.equal(groupRoot.get("groupName"), groupName));

        result = Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());

        return result;
    }
}
