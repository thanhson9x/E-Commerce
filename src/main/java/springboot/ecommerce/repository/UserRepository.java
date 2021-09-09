package springboot.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import springboot.ecommerce.entity.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {

//	@Query("select u from UserEntity u where u.id = ?1")
	UserEntity findById(int id);

	UserEntity findByEmail(String email);

	@Query("select u from UserEntity u where u.firstName like %?1% or u.lastName like %?1% or u.email like %?1%")
	public Page<UserEntity> findByKeyword(String keyword, Pageable pageable);

	@Query("select u from UserEntity u join u.roles r where r.id = ?1")
	public Page<UserEntity> findByRole(int role, Pageable pageable);

	@Query("select u from UserEntity u join u.roles r where (u.firstName like %?1% or u.lastName like %?1% or u.email like %?1%) and r.id = ?2")
	public Page<UserEntity> findByKeywordAndRole(String keyword, int role, Pageable pageable);

}
