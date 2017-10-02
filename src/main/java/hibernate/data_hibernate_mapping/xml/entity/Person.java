package hibernate.data_hibernate_mapping.xml.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@SuppressWarnings("PMD")
public class Person {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private Date dob;

    @Getter
    @Setter
    private Passport passport;

    @Getter
    @Setter
    private Address primaryAddress;

    @Getter
    @Setter
    private Collection<Company> workingPlaces;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", passport=" + passport +
                ", primaryAddress=" + primaryAddress +
                ", workingPlaces=" + workingPlaces +
                '}';
    }
}
