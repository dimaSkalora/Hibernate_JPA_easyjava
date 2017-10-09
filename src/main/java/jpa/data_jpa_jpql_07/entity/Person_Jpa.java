package jpa.data_jpa_jpql_07.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Collection;

@SuppressWarnings("PMD")
@Entity
public class Person_Jpa extends AbstractIdentifiableObject_Jpa {
    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private LocalDate dob;

    @Getter
    @Setter
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "PASSPORT_JPA_ID")
    private Passport_Jpa passport_jpa;

    @Getter
    @Setter
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_JPA_ID")
    private Address_Jpa primaryAddress_jpa;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PERSON_JPA_COMPANIES",
            joinColumns = @JoinColumn(name = "PERSON_JPA_ID"),
            inverseJoinColumns = @JoinColumn(name = "COMPANY_JPA_ID")
    )
    private Collection<Company_Jpa> workingPlaces_jpa;

    @Override
    public String toString() {
        return "Person_Jpa{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", passport=" + passport_jpa +
                ", primaryAddress=" + primaryAddress_jpa +
                ", workingPlaces=" + workingPlaces_jpa +
                '}';
    }
}
