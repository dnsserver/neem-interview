package neemapp.interview.repositories;

import neemapp.interview.data.Family;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
public interface FamilyRepository extends CrudRepository<Family, Long> { }
