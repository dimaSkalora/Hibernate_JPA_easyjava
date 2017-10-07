package jpa.data_jpa_hibernate_entitymapping_02.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Single financial operation.
 */
@SuppressWarnings("PMD")
@ToString
@Entity
@Table(name = "journal_jpa",
        indexes = {@Index(
                name = "j_account_idx",
                columnList = "accountId", unique = false)},
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"id_jpa", "accountId"})})
@SecondaryTable(name = "operations_details_jpa",
        pkJoinColumns = @PrimaryKeyJoinColumn(
                name = "op_id_jpa",
                referencedColumnName = "id_jpa"))
public class OperationJpa {
    /**
     * Operation id.
     */
    @Id
    @GeneratedValue
    @Getter
    @Setter
    @Column(name = "id_jpa", nullable = false, updatable = false)
    private Long rowId;

    /**
     * Related transaction id.
     *
     * Single transaction could have
     * more then one operations.
     */
    @Getter
    @Setter
    @Column(name = "trxId", nullable = false, updatable = false)
    private Long id;

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

    /**
     * Operation's timestamp.
     */
    @Getter
    @Setter
    @Column(nullable = false, updatable = false)
    private ZonedDateTime timestamp;

    /**
     * Optional operation description.
     */
    @Getter
    @Setter
    @Column(table = "operations_details_jpa", length = 64)
    private String description;

    /**
     * Optional operation code.
     */
    @Getter
    @Setter
    @Column(table = "operations_details_jpa")
    private Integer opCode;

}
