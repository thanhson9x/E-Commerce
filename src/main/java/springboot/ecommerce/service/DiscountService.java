package springboot.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.ecommerce.entity.DiscountEntity;
import springboot.ecommerce.repository.DiscountRepository;

@Service
public class DiscountService {

	@Autowired
	public DiscountRepository discountRepository;

	public List<DiscountEntity> getList() {
		return (List<DiscountEntity>) discountRepository.findAll();
	}

	public void save(DiscountEntity discount) {
		discountRepository.save(discount);
	}

	public DiscountEntity findById(int id) {
		return discountRepository.findById(id);
	}

	public void deleteById(int id) {
		discountRepository.deleteById(id);
	}

	public void delete(DiscountEntity discount) {
		discountRepository.delete(discount);
	}

}
