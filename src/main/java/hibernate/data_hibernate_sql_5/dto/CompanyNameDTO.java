package hibernate.data_hibernate_sql_5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Company name extractor
 */
@SuppressWarnings("PMD")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompanyNameDTO {
    /**
     * The name.
     */
    @Getter
    @Setter
    private String name;
}
