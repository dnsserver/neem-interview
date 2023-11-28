package neemapp.interview.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import neemapp.interview.repositories.PlanDeductibleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Boolean isHouseHold = false;


    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private Set<Deductible> deductibles;

    @Transient
    @JsonInclude
    private HashMap<Long, DisplayDeductible> allDeductibles = new HashMap<>();




    @ManyToOne
    @JoinColumn(name="family_id", referencedColumnName = "id")
    @JsonBackReference
    private Family family;
}
