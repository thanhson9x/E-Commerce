package springboot.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.ecommerce.entity.CreditCardEntity;
import springboot.ecommerce.repository.CreditCardRepository;

@Service
public class CreditCardService {

	@Autowired
	public CreditCardRepository creditCardRepository;

	public List<CreditCardEntity> getList() {
		return (List<CreditCardEntity>) creditCardRepository.findAll();
	}

	public void save(CreditCardEntity creditCard) {
		creditCardRepository.save(creditCard);
	}

	public CreditCardEntity findById(int id) {
		return creditCardRepository.findById(id);
	}

	public void deleteById(int id) {
		creditCardRepository.deleteById(id);
	}

	public void delete(CreditCardEntity creditCard) {
		creditCardRepository.delete(creditCard);
	}
}
