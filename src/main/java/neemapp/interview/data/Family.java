package neemapp.interview.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Family {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "family")
    private Set<Member> members;

    @ManyToOne
    @JoinColumn(name="plan_id", referencedColumnName = "id")
    @JsonBackReference
    private Plan plan;
}
