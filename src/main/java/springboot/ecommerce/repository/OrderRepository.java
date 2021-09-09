package springboot.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import springboot.ecommerce.entity.OrderEntity;

public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Integer> {

	public OrderEntity findById(int id);

	@Query("select o from OrderEntity o where o.user.firstName like %?1% or o.user.lastName like %?1% or o.status like %?1%")
	public Page<OrderEntity> findByKeyword(String keyword, Pageable pageable);

	@Query("select o from OrderEntity o where o.user.id = ?1")
	public Page<OrderEntity> findByUser(int id, Pageable pageable);

	@Query("select o from OrderEntity o where o.user.id = ?1 and o.status like %?2%")
	public Page<OrderEntity> findByStatus(int id, String status, Pageable pageable);

//	@Query("select o from OrderEntity o where o.user.id = ?1 and o.status not like %?2%")
//	public Page<OrderEntity> findByNotStatus(int id, String status, Pageable pageable);

}
