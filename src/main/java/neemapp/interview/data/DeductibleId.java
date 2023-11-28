package neemapp.interview.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class DeductibleId implements Serializable {
    private Long member;
    private Long category;
}
