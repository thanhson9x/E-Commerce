package springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import springboot.ecommerce.entity.RoleEntity;

public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Integer> {

	@Query("select r from RoleEntity r where r.id like ?1")
	public RoleEntity findById(int id);

	RoleEntity findByName(String name);

}
