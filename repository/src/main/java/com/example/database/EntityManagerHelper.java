package com.example.database;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Slf4j
@Component
public class EntityManagerHelper {
    private SessionFactory factory;
    private final ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<>();

    @SneakyThrows
    @PostConstruct
    public void init() {
        Configuration cfg = new Configuration().configure();
        this.factory = cfg.buildSessionFactory();
    }

    public EntityManager createNewEntityManager() {
        return factory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        EntityManager em = currentEntityManager.get();
        if (em == null) {
            currentEntityManager.set(em = factory.createEntityManager());
            log.info("Has been created and set EM");
        }
        return em;
    }

    public void closeCurrentEntityManger() {
        EntityManager em = currentEntityManager.get();
        if (em != null) {
            em.close();
            currentEntityManager.remove();
        }

    }
}
