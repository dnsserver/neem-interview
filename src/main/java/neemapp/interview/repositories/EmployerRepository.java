package neemapp.interview.repositories;

import neemapp.interview.data.Employer;
import org.springframework.data.repository.CrudRepository;

public interface EmployerRepository extends CrudRepository<Employer, Long> {
}
