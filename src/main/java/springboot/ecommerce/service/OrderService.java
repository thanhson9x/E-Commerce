package springboot.ecommerce.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import springboot.ecommerce.entity.OrderEntity;
import springboot.ecommerce.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	public OrderRepository orderRepository;

	@Value("${common.paging.number}")
	public int pagingNumber;

	@Transactional
	public Page<OrderEntity> getOrdersPaging(int page) {
		return (Page<OrderEntity>) orderRepository.findAll(PageRequest.of(page - 1, pagingNumber));
	}

	public List<OrderEntity> getList() {
		return (List<OrderEntity>) orderRepository.findAll();
	}

	public void save(OrderEntity order) {
		orderRepository.save(order);
	}

	public OrderEntity findById(int id) {
		return orderRepository.findById(id);
	}

	public void deleteById(int id) {
		orderRepository.deleteById(id);
	}

	public void delete(OrderEntity order) {
		orderRepository.delete(order);
	}

	public Page<OrderEntity> getOrderPageBySearch(String keyword, int page) {

		if (keyword != null && keyword.length() > 0) {
			return (Page<OrderEntity>) orderRepository.findByKeyword(keyword, PageRequest.of(page - 1, pagingNumber));
		} else {
			return (Page<OrderEntity>) orderRepository.findAll(PageRequest.of(page - 1, pagingNumber));
		}

	}

	public Page<OrderEntity> getOrderPageByStatus(int id, String status, int page) {

		if (status != null && status.length() > 0) {
			return (Page<OrderEntity>) orderRepository.findByStatus(id, status, PageRequest.of(page - 1, pagingNumber));
		} else {
			return (Page<OrderEntity>) orderRepository.findByUser(id, PageRequest.of(page - 1, pagingNumber));
		}

	}

}
