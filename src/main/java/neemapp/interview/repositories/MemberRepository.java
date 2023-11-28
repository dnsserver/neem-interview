package neemapp.interview.repositories;

import neemapp.interview.data.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Iterable<Member> findAllByFamilyId(Long famId);
}
