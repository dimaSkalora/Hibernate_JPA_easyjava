package jpa.data_jpa_hibernate_criteria_08.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.stream.Collectors;

@SuppressWarnings("PMD")
@Entity
public class Address_Jpa extends AbstractIdentifiableObject_Jpa {
    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String street;

    @Getter
    @Setter
    private String building;

    @Getter
    @Setter
    @OneToMany(mappedBy = "primaryAddress_jpa", fetch = FetchType.EAGER)
    private Collection<Person_Jpa> tenants;

    @Override
    public String toString() {
        return "Address_Jpa{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", tenants=" + tenants
                .stream()
                .map(Person_Jpa::getFirstName)
                .collect(Collectors.joining(",")) +
                '}';
    }
}
