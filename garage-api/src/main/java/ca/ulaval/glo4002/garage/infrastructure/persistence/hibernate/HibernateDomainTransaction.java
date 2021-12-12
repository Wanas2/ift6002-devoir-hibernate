package ca.ulaval.glo4002.garage.infrastructure.persistence.hibernate;

import org.glassfish.jersey.internal.util.Producer;

import ca.ulaval.glo4002.garage.domain.DomainTransaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class HibernateDomainTransaction implements DomainTransaction {
	@Override
	public <T> T wrapInTransaction(Producer<T> call) {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();

		T result;
		try {
			result = call.call();
			entityTransaction.commit();
		} catch (Exception e){
			entityTransaction.rollback();
			throw e;
		}

		return result;
	}
}
