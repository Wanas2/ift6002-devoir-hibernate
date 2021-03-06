package ca.ulaval.glo4002.garage.infrastructure.persistence.hibernate;

import ca.ulaval.glo4002.garage.domain.appointments.AppointmentNumber;
import ca.ulaval.glo4002.garage.domain.orders.Order;
import ca.ulaval.glo4002.garage.domain.orders.Part;
import ca.ulaval.glo4002.garage.infrastructure.persistence.memory.InMemoryOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HibernateOrderRepositoryITest {
    private InMemoryOrderRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryOrderRepository();
    }

    @Test
    void findAll_returnsAllSavedOrders() {
        Order anOrder = createOrder("an identifier", "a part");
        Order anotherOrder = createOrder("another identifier", "another part");
        repository.save(anOrder);
        repository.save(anotherOrder);

        List<Order> allOrders = repository.findAll();

        assertThat(allOrders).contains(anOrder, anotherOrder);
    }

    private Order createOrder(String appointmentIdentifier, String partName) {
        Order anOrder = new Order(LocalDateTime.now(), AppointmentNumber.create(appointmentIdentifier));
        anOrder.addPart(new Part(1, partName));
        return anOrder;
    }
}