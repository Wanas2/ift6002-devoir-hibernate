package ca.ulaval.glo4002.garage.infrastructure.persistence.hibernate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import ca.ulaval.glo4002.garage.domain.appointments.Appointment;
import ca.ulaval.glo4002.garage.domain.appointments.AppointmentNumber;
import ca.ulaval.glo4002.garage.domain.appointments.AppointmentRepository;
import ca.ulaval.glo4002.garage.domain.appointments.DuplicateAppointmentException;

import javax.persistence.EntityManager;

public class HibernateAppointmentRepository implements AppointmentRepository {
    @Override
    public void save(Appointment appointment) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        if(exists(appointment)) {
            throw new DuplicateAppointmentException();
        }
        entityManager.persist(appointment);
    }

    private boolean exists(Appointment appointment) {
        List<Appointment> appointments = (List<Appointment>) findAll();
        return appointments.stream()
                .anyMatch(o -> o.getAppointmentNumber()
                        .equals(appointment.getAppointmentNumber()));
    }

    @Override
    public Collection<Appointment> findAll() {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        String request = "from Appointment";
        return entityManager.createQuery(request, Appointment.class).getResultList();
    }

    @Override
    public Optional<Appointment> findByNumber(AppointmentNumber appointmentNumber) {
        List<Appointment> appointments = (List<Appointment>) findAll();
        Appointment appointment;

        for(Appointment appointmentRetrieved : appointments){
            if(appointmentRetrieved.getAppointmentNumber().equals(appointmentNumber)) {
                appointment = appointmentRetrieved;
                return Optional.of(appointment);
            }
        }

        return Optional.empty();
    }
}
