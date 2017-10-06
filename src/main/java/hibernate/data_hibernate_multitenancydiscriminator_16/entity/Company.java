package hibernate.data_hibernate_multitenancydiscriminator_16.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.stream.Collectors;

@SuppressWarnings("PMD")
@Entity
public class Company extends AbstractDiscriminatorObject  {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "workingPlaces")
    private Collection<Person> workers;

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", workers=" + workers
                .stream()
                .map(Person::getFirstName)
                .collect(Collectors.joining(","))
                + '}';
    }
}