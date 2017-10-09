package jpa.data_jpa_hibernate_criteria_08.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.Period;

@SuppressWarnings("PMD")
@Entity
public class Passport_Jpa extends AbstractIdentifiableObject_Jpa {
    @Getter
    @Setter
    private String series;

    @Getter
    @Setter
    private String no;

    @Getter
    @Setter
    private LocalDate issueDate;

    @Getter
    @Setter
    private Period validity;

    @Getter
    @Setter
    @OneToOne(optional = false, mappedBy = "passport_jpa")
    private Person_Jpa owner;

    @Override
    public String toString() {
        return "Passport_Jpa{" +
                "series='" + series + '\'' +
                ", no='" + no + '\'' +
                ", issueDate=" + issueDate +
                ", validity=" + validity +
                ", owner=" + owner.getLastName() +
                '}';
    }
}
