package springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import springboot.ecommerce.entity.CategoryEntity;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Integer> {

	@Query("select c from CategoryEntity c where c.id like ?1")
	CategoryEntity findById(int id);

}
