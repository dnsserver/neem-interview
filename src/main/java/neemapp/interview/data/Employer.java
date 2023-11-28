package neemapp.interview.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Employer {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    private Set<Family> families;

    @OneToMany
    private Set<Plan> insurancePlans;

}
