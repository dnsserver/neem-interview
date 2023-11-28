package neemapp.interview.repositories;

import neemapp.interview.data.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
