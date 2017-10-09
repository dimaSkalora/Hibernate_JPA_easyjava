package jpa.data_jpa_hibernate_criteria_08.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.stream.Collectors;

@SuppressWarnings("PMD")
@Entity

public class Company_Jpa extends AbstractIdentifiableObject_Jpa {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "workingPlaces_jpa")
    private Collection<Person_Jpa> workers;

    @Override
    public String toString() {
        return "Company_Jpa{" +
                "name='" + name + '\'' +
                ", workers=" + workers
                .stream()
                .map(Person_Jpa::getFirstName)
                .collect(Collectors.joining(","))
                + '}';
    }
}
