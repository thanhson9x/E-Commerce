package springboot.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import springboot.ecommerce.entity.ProductEntity;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Integer> {

	@Query("select p from ProductEntity p where p.id like ?1")
	public ProductEntity findById(int id);

//	@Query("select p from ProductEntity p where p.name like %:keyword%")
//	public List<ProductEntity> findByName(@Param("keyword") String name);
//
//	@Query("select p from ProductEntity p where p.category.id like ?1")
//	public List<ProductEntity> findByCategoryId(int id);

	@Query("select p from ProductEntity p where p.name like %?1%")
	public Page<ProductEntity> findByName(String name, Pageable pageable);

	@Query("select p from ProductEntity p where p.category.id = ?1")
	public Page<ProductEntity> findByCategoryId(int category, Pageable pageable);

	@Query("select p from ProductEntity p where p.name like %?1% and p.category.id = ?2")
	public Page<ProductEntity> findByNameAndCategoryId(String name, int category, Pageable pageable);
	
	@Query("select p from ProductEntity p where p.discount.id != ?1")
	public Page<ProductEntity> findByDiscount(int id, Pageable pageable);

}
