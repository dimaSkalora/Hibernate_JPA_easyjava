package jpa.data_jpa_hibernate_inheritance_04.entity.one_table_for_all_classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Some product.
 */
@SuppressWarnings("PMD")
@Entity
@DiscriminatorValue("PRODUCT_inheritance")
@ToString
public class ProductInheritance extends AbstractNamedObject {
    /**
     * Category, to which this product belongs.
     */
    @Getter
    @Setter
    private Long productCategoryId;
}
