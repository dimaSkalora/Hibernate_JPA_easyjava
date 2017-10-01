package hibernate.data_hibernate_hello;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Greeter {
    /**
     * Primary key.
     */
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    /**
     * Greeting.
     */
    @Getter
    @Setter
    private String greeting;

    /**
     * Greeting target.
     */
    @Getter
    @Setter
    private String target;
}
