package jpa.data_jpa_hibernate_primarykeys_03.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User definition.
 */
@SuppressWarnings("PMD")
@ToString
@Entity
@Table(name = "users")
public class User {

    /**
     * User login name.
     */
    @Id
    @Getter
    @Setter
    private String username;

    /**
     * User password.
     */
    @Getter
    @Setter
    @Column(nullable = false)
    private String password;

    /**
     * User e-mail, must be unique.
     */
    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String email;
}
