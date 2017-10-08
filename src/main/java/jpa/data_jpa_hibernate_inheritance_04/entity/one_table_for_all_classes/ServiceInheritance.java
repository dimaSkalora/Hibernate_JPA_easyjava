package jpa.data_jpa_hibernate_inheritance_04.entity.one_table_for_all_classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Some service description.
 */
@SuppressWarnings("PMD")
@Entity
@DiscriminatorValue("SERVICE_inheritance")
@ToString
public class ServiceInheritance extends AbstractNamedObject {
    /**
     * Service group, to which that service belongs.
     */
    @Getter
    @Setter
    private Long serviceGroup;
}
