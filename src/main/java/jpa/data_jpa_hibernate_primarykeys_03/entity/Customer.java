package jpa.data_jpa_hibernate_primarykeys_03.entity;

import jpa.data_jpa_hibernate_primarykeys_03.entity.keys.CustomerKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Customer definition.
 */
@SuppressWarnings("PMD")
@ToString
@Entity
@Table(name = "customers")
@IdClass(CustomerKey.class)
public class Customer {

    /**
     * Customer's passport series value.
     */
    @Id
    @Getter
    @Setter
    private String passportSeries;

    /**
     * Customer's passport number value.
     */
    @Id
    @Getter
    @Setter
    private String passportSNo;

    /**
     * Customer name.
     */
    @Getter
    @Setter
    private String name;
}
