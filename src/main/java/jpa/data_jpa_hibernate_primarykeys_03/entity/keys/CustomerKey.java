package jpa.data_jpa_hibernate_primarykeys_03.entity.keys;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@SuppressWarnings("PMD")
@EqualsAndHashCode
@ToString
@Embeddable
public class CustomerKey implements Serializable {
    static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String passportSeries;

    @Getter
    @Setter
    private String passportSNo;

}
