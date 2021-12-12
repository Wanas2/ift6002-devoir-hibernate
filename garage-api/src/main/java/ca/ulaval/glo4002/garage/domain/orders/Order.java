package ca.ulaval.glo4002.garage.domain.orders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.garage.domain.appointments.AppointmentNumber;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime date;

    @AttributeOverrides({
            @AttributeOverride(name="number",
                    column=@Column(name="appointmentNumber"))
    })

    @Embedded
    private AppointmentNumber referenceNumber;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<Part> parts = new ArrayList<>();

    public Order(){}

    public Order(LocalDateTime date, AppointmentNumber referenceNumber) {
        this.date = date;
        this.referenceNumber = referenceNumber;
    }

    public void addPart(Part part) {
        parts.add(part);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public AppointmentNumber getReferenceNumber() {
        return referenceNumber;
    }

    public List<Part> listParts() {
        return parts;
    }
}
