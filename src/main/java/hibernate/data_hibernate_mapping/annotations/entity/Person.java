package hibernate.data_hibernate_mapping.annotations.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@SuppressWarnings("PMD")
@Entity
public class Person extends AbstractIdentifiableObject {
    @Setter
    @Getter
    private String firstName;

    @Setter
    @Getter
    private String lastName;

    @Setter
    @Getter
    private LocalDate dob;

    @Setter
    @Getter
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "PASSPORT_ID")
    private Passport passport;

    @Getter
    @Setter
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    private Address primaryAddress;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PERSON_COMPANIES",
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "COMPANY_ID")
    )
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
