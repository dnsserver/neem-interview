package neemapp.interview.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisplayDeductible {
    private String category;
    private Double value;
    private Boolean isNonStandard;
}
