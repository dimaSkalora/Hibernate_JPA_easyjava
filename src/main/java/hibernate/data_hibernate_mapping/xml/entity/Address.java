package hibernate.data_hibernate_mapping.xml.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.stream.Collectors;

@SuppressWarnings("PMD")
public class Address {
    @Getter
    @Setter
    private Integer id;

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
