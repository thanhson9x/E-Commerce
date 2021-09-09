package springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import springboot.ecommerce.entity.GenderEntity;

public interface GenderRepository extends PagingAndSortingRepository<GenderEntity, Integer>{

	@Query("select g from GenderEntity g where g.id like ?1")
	GenderEntity findById(int id);
	
}
