package hibernate.data_hibernate_usertype_8.entity;

import hibernate.data_hibernate_usertype_8.type.NetworkObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Network storing entity.
 */
@SuppressWarnings("PMD")
@Entity
@ToString
public class Network {
    /**
     * Entity id.
     */
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    /**
     * Network data.
     */
    @Type(type = "hibernate.data_hibernate_usertype_8.typ.NetworkObjectType")
    @Getter
    @Setter
    private NetworkObject network;
}
