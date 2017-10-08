package jpa.data_jpa_hibernate_inheritance_04.entity.separate_tables_for_each_class;

import jpa.data_jpa_hibernate_inheritance_04.entity.AbstractIdentifiableObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Base cargo class.
 */
@SuppressWarnings("PMD")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString
public class Cargo extends AbstractIdentifiableObject {
    /**
     * Cargo weight in kg.
     */
    @Getter
    @Setter
    private Long weight;
}
