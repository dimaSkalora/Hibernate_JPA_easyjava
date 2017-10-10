package jpa.data_jpa_hibernate_callbacks_10.listeners;

import jpa.data_jpa_hibernate_callbacks_10.entity.OperationJpa;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * JPA callback example
 */
public class OperationListener {
    @PrePersist
    public void prePersist(OperationJpa o) {
        System.out.println("Pre-Persistiting operation: " + o.getDescription());
    }

    @PostPersist
    public void postPersist(OperationJpa o) {
        System.out.println("Post-Persist operation: " + o.getDescription());
    }

    @PreRemove
    public void preRemove(OperationJpa o) {
        System.out.println("Pre-Removing operation: " + o.getDescription());
    }

    @PostRemove
    public void postRemove(OperationJpa o) {
        System.out.println("Post-Remove operation: " + o.getDescription());
    }

    @PreUpdate
    public void preUpdate(OperationJpa o) {
        System.out.println("Pre-Updating operation: " + o.getDescription());
    }

    @PostUpdate
    public void postUpdate(OperationJpa o) {
        System.out.println("Post-Update operation: " + o.getDescription());
    }
}
