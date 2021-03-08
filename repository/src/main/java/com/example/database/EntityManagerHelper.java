package com.example.database;

import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EntityManagerHelper {

    private final SessionFactory factory;

    @SneakyThrows
    private EntityManagerHelper() {
        Configuration cfg = new Configuration().configure();
        this.factory = cfg.buildSessionFactory();
    }

    private static class EntityManagerHelperHolder {
        private static final EntityManagerHelper HOLDER_INSTANCE = new EntityManagerHelper();
    }

    public static EntityManagerHelper getInstance() {
        return EntityManagerHelperHolder.HOLDER_INSTANCE;
    }

    @SneakyThrows
    public javax.persistence.EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
