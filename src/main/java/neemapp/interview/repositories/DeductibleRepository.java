package neemapp.interview.repositories;

import neemapp.interview.data.Deductible;
import neemapp.interview.data.DeductibleId;
import neemapp.interview.data.Family;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeductibleRepository extends CrudRepository<Deductible, DeductibleId> {
    List<Deductible> findAllByMemberId(Long member_id);
}
