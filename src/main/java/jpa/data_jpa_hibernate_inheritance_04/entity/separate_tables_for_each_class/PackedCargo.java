package jpa.data_jpa_hibernate_inheritance_04.entity.separate_tables_for_each_class;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * Cargo packed in a box, thus having dimensions.
 */
@SuppressWarnings("PMD")
@Entity
@ToString
public class PackedCargo extends Cargo {
    /**
     * Square box width.
     */
    @Getter
    @Setter
    private Long width;

    /**
     * Box height.
     */
    @Getter
    @Setter
    private Long height;
}
