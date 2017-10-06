package jpa.data_jpa_hibernate_hellojpahibernate_01.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("PMD")
@Entity
public class GreeterJpa {
    @Id
    @GeneratedValue
    @Setter
    @Getter
    private Integer id;

    @Setter
    @Getter
    private String greeting;

    @Setter
    @Getter
    private String target;
}
