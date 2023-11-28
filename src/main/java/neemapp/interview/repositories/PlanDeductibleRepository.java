package neemapp.interview.repositories;

import neemapp.interview.data.PlanDeductibleId;
import neemapp.interview.data.PlanDeductible;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PlanDeductibleRepository extends CrudRepository<PlanDeductible, PlanDeductibleId> {
    Set<PlanDeductible> findAllByPlanId(Long plan_id);
}
