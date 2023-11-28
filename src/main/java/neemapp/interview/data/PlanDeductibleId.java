package neemapp.interview.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class PlanDeductibleId implements Serializable {
    private Long plan;
    private Long category;
}
