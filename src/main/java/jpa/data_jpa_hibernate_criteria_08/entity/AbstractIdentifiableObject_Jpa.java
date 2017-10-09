package jpa.data_jpa_hibernate_criteria_08.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("PMD")
@MappedSuperclass
@ToString
public abstract class AbstractIdentifiableObject_Jpa {
    /**
     * Common id field.
     */
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;
}
