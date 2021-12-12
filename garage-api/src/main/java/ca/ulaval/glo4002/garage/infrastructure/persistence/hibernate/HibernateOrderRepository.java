package ca.ulaval.glo4002.garage.infrastructure.persistence.hibernate;

import java.util.List;

import ca.ulaval.glo4002.garage.domain.orders.Order;
import ca.ulaval.glo4002.garage.domain.orders.OrderRepository;

import javax.persistence.EntityManager;

public class HibernateOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        entityManager.persist(order);
    }

    @Override
    public List<Order> findAll() {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        String req = "from Order";
        return entityManager.createQuery(req, Order.class).getResultList();
    }
}
