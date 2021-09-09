package springboot.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.ecommerce.entity.OrderDetailsEntity;
import springboot.ecommerce.repository.OrderDetailsRepository;

@Service
public class OrderDetailsService {

	@Autowired
	public OrderDetailsRepository orderDetailsRepository;

	public List<OrderDetailsEntity> getList() {
		return (List<OrderDetailsEntity>) orderDetailsRepository.findAll();
	}

	public void save(OrderDetailsEntity orderDetails) {
		orderDetailsRepository.save(orderDetails);
	}

	public OrderDetailsEntity findById(int id) {
		return orderDetailsRepository.findById(id);
	}

	public void deleteById(int id) {
		orderDetailsRepository.deleteById(id);
	}

	public void delete(OrderDetailsEntity orderDetails) {
		orderDetailsRepository.delete(orderDetails);
	}

	public List<OrderDetailsEntity> getListByNullOrder() {
		return orderDetailsRepository.findByNullOrder();
	}

}
