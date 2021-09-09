package springboot.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import springboot.ecommerce.entity.OrderDetailsEntity;

public interface OrderDetailsRepository extends PagingAndSortingRepository<OrderDetailsEntity, Integer> {

	@Query("select o from OrderDetailsEntity o where o.id like ?1")
	OrderDetailsEntity findById(int id);

	@Query("select o from OrderDetailsEntity o where o.order = null")
	List<OrderDetailsEntity> findByNullOrder();

}
