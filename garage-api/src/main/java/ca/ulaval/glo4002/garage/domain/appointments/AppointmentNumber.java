package ca.ulaval.glo4002.garage.domain.appointments;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Embeddable
@Access(AccessType.PROPERTY)
public class AppointmentNumber {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String number;

    public AppointmentNumber(){}

    public static AppointmentNumber create(LocalDate date, String identifier) {
        return new AppointmentNumber(date.format(FORMATTER) + "-" + identifier);
    }

    public static AppointmentNumber create(String appointmentNumberAsString) {
        return new AppointmentNumber(appointmentNumberAsString);
    }

    private AppointmentNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    @Override
    public String toString() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppointmentNumber that = (AppointmentNumber) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
