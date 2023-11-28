package neemapp.interview.repositories;

import neemapp.interview.data.Plan;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<Plan, Long> {
}
