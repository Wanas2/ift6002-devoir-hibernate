package ca.ulaval.glo4002.garage.domain.orders;

import javax.persistence.*;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int quantity;
    private String name;

    public Part() {}

    public Part(int quantity, String name) {
        this.quantity = quantity;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
