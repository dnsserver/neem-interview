package neemapp.interview.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(PlanDeductibleId.class)
public class PlanDeductible {

    private Double individual;

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;

    @Id
    @ManyToOne
    @JoinColumn(name="plan_id", referencedColumnName="id")
    @JsonBackReference
    private Plan plan;
}
