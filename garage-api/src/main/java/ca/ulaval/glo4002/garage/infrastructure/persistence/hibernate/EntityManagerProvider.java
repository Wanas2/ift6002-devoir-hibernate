package ca.ulaval.glo4002.garage.infrastructure.persistence.hibernate;

import javax.persistence.EntityManager;

public class EntityManagerProvider {
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    public static EntityManager getEntityManager() {
        return threadLocal.get();
    }

    public static void setEntityManager(EntityManager entityManager) {
        threadLocal.set(entityManager);
    }

    public static void clearEntityManager() {
        threadLocal.set(null);
    }
}
