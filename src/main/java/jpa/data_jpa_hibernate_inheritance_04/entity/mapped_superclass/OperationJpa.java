package jpa.data_jpa_hibernate_inheritance_04.entity.mapped_superclass;

import jpa.data_jpa_hibernate_inheritance_04.entity.AbstractIdentifiableObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Single financial operation.
 */
@SuppressWarnings("PMD")
@ToString
@Entity
@Table(name = "journal_inheritance")
public class OperationJpa extends AbstractIdentifiableObject {
    /**
     * Operation's account.
     */
    @Getter
    @Setter
    @Column(nullable = false, updatable = false)
    private Integer accountId;

    /**
     * Operation's amount.
     */
    @Getter
    @Setter
    @Column(nullable = false, updatable = false, scale = 2, precision = 10)
    private BigDecimal amount;
}
