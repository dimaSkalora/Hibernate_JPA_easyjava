package hibernate.data_hibernate_mapping.dynamic.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@SuppressWarnings("PMD")
public class Passport {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String series;

    @Getter
    @Setter
    private String no;

    @Getter
    @Setter
    private Date issueDate;

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", series='" + series + '\'' +
                ", no='" + no + '\'' +
                ", issueDate=" + issueDate +
                '}';
    }
}
