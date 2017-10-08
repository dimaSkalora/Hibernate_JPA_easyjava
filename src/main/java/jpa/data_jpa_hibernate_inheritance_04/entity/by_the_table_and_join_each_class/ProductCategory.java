package jpa.data_jpa_hibernate_inheritance_04.entity.by_the_table_and_join_each_class;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * Holds similar products.
 */
@SuppressWarnings("PMD")
@Entity
@ToString
public class ProductCategory extends ObjectWithDescription {
    /**
     * Some human friendly category code.
     */
    @Getter
    @Setter
    private String code;
}
