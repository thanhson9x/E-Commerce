package springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import springboot.ecommerce.entity.CreditCardEntity;

public interface CreditCardRepository extends CrudRepository<CreditCardEntity, Integer> {

	@Query("select c from CreditCardEntity c where c.id like ?1")
	CreditCardEntity findById(int id);

}
