package jpa.data_jpa_hibernate_inheritance_04.entity.separate_tables_for_each_class;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * Liquid cargo.
 */
@SuppressWarnings("PMD")
@Entity
@ToString
public class LiquidCargo extends Cargo {
    /**
     * Cargo volume.
     */
    @Getter
    @Setter
    private Long volume;

    /**
     * Liquid type.
     */
    @Getter
    @Setter
    private String liquidType;
}
