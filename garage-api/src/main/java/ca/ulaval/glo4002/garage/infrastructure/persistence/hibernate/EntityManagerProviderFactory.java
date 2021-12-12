package ca.ulaval.glo4002.garage.infrastructure.persistence.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProviderFactory {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getFactory(){
        if(entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("garage");
        }

        return entityManagerFactory;
    }
}
