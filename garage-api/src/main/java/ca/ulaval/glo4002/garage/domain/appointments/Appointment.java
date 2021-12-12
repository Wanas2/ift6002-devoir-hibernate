package ca.ulaval.glo4002.garage.domain.appointments;

import javax.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @AttributeOverrides({
            @AttributeOverride(name="number",
                    column=@Column(name="appointmentNumber"))
    })

    @Embedded
    private AppointmentNumber appointmentNumber;
    private String clientName;
    private String clientPhone;

    public Appointment(){
    }

    public Appointment(AppointmentNumber appointmentNumber, String clientName, String clientPhone) {
        this.appointmentNumber = appointmentNumber;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public AppointmentNumber getAppointmentNumber() {
        return appointmentNumber;
    }
}
