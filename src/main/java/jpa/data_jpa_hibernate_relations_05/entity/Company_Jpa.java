package jpa.data_jpa_hibernate_relations_05.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Collection;
import java.util.stream.Collectors;

@SuppressWarnings("PMD")
@Entity
@NamedQueries({
        @NamedQuery(
                name = "findCompaniesWithWorkerPassport_jpa",
                query = "Select c from Company_Jpa as c, IN(c.workers) as w where w.passport_jpa.series = :series")

})
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
