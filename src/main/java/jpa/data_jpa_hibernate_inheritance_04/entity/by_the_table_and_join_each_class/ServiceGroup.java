package jpa.data_jpa_hibernate_inheritance_04.entity.by_the_table_and_join_each_class;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * Group of services.
 */
@SuppressWarnings("PMD")
@Entity
@ToString
public class ServiceGroup extends ObjectWithDescription {
    /**
     * Some human friendly group code.
     */
    @Getter
    @Setter
    private String code;
}
