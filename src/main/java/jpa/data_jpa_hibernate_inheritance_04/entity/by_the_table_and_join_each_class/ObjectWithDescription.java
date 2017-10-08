package jpa.data_jpa_hibernate_inheritance_04.entity.by_the_table_and_join_each_class;

import jpa.data_jpa_hibernate_inheritance_04.entity.AbstractIdentifiableObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Base class for all objects with description.
 */
@SuppressWarnings("PMD")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "descriptions_inheritance")
@ToString
public abstract class ObjectWithDescription extends AbstractIdentifiableObject {
    /**
     * Description text.
     */
    @Getter
    @Setter
    private String description;
}
