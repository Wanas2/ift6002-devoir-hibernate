package ca.ulaval.glo4002.garage.infrastructure.persistence.hibernate;

import ca.ulaval.glo4002.garage.domain.appointments.Appointment;
import ca.ulaval.glo4002.garage.domain.appointments.AppointmentNumber;
import ca.ulaval.glo4002.garage.domain.appointments.DuplicateAppointmentException;
import ca.ulaval.glo4002.garage.infrastructure.persistence.memory.InMemoryAppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HibernateAppointmentRepositoryITest {
    private Appointment anAppointment;

    private InMemoryAppointmentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryAppointmentRepository();

        anAppointment = givenAnAppointmentWithNumberFromIdentifier("anAppointmentIdentifier");
    }

    @Test
    void saveAppointment_canThenRetrieveItByNumber() {
        repository.save(anAppointment);

        Appointment appointmentFound = repository.findByNumber(anAppointment.getAppointmentNumber()).get();

        assertThat(appointmentFound).isSameAs(anAppointment);
    }

    @Test
    void saveAppointment_createsAnErrorWhenAnAppointmentIsSavedTwiceWithTheSameNumber() {
        repository.save(anAppointment);

        assertThrows(
                DuplicateAppointmentException.class,
                () -> repository.save(anAppointment)
        );
    }

    @Test
    void saveTwoAppointments_canRetrieveAListOfAppointments() {
        Appointment anotherAppointment = givenAnAppointmentWithNumberFromIdentifier("anotherAppointment identifier");
        repository.save(anAppointment);
        repository.save(anotherAppointment);

        Collection<Appointment> allAppointments = repository.findAll();

        assertThat(allAppointments).contains(anAppointment, anotherAppointment);
    }

    @Test
    void findAppointmentByNumber_resultIsEmptyIfAppointmentDoesNotExist() {
        Optional<Appointment> result = repository.findByNumber(AppointmentNumber.create("inexistant"));

        assertThat(result).isEmpty();
    }

    private Appointment givenAnAppointmentWithNumberFromIdentifier(String identifier) {
        AppointmentNumber number = AppointmentNumber.create(identifier);
        return new Appointment(number, "a name", "a phone");
    }
}