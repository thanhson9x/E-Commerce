package springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import springboot.ecommerce.entity.DiscountEntity;

public interface DiscountRepository extends PagingAndSortingRepository<DiscountEntity, Integer> {

	@Query("select d from DiscountEntity d where d.id like ?1")
	DiscountEntity findById(int id);

}
