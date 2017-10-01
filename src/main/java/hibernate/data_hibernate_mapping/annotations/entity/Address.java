package hibernate.data_hibernate_mapping.annotations.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.stream.Collectors;

@SuppressWarnings("PMD")
@Entity
public class Address extends AbstractIdentifiableObject{
    @Setter
    @Getter
    private String city;
    @Setter
    @Getter
    private String street;
    @Setter
    @Getter
    private String building;
    @Setter
    @Getter
    @OneToMany(mappedBy = "primaryAddress", fetch = FetchType.EAGER)
    private Collection<Person> tenants;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", tenants=" + tenants
                .stream()
                .map(Person::getFirstName)
                .collect(Collectors.joining(",")) +
                '}';
    }
}
