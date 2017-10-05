package hibernate.data_hibernate_interceptors_12.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("PMD")
@MappedSuperclass
@ToString
public abstract class AbstractIdentifiableObject {
    /**
     * Common id field.
     */
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;
}
