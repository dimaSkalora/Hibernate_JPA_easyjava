package hibernate.data_hibernate_mapping.xml.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.stream.Collectors;

@SuppressWarnings("PMD")
public class Company {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
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
