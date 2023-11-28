package neemapp.interview.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Plan {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String number;
    private String notes;
}
