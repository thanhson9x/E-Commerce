package springboot.ecommerce.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import springboot.ecommerce.entity.ProductEntity;
import springboot.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	public ProductRepository productRepository;

	@Value("${common.paging.number}")
	public int pagingNumber;

	@Transactional
	public Page<ProductEntity> getProductsPaging(int page) {
		return (Page<ProductEntity>) productRepository.findAll(PageRequest.of(page - 1, pagingNumber));
	}

	public List<ProductEntity> getList() {
		return (List<ProductEntity>) productRepository.findAll();
	}

	public void save(ProductEntity product) {
		productRepository.save(product);
	}

	public ProductEntity findById(int id) {
		return productRepository.findById(id);
	}

	public void deleteById(int id) {
		productRepository.deleteById(id);
	}

	public void delete(ProductEntity product) {
		productRepository.delete(product);
	}

	public Page<ProductEntity> getProductPageBySearch(String name, int category, int page) {

		if (name != null && name.length() > 0 && category == 0) {
			return (Page<ProductEntity>) productRepository.findByName(name, PageRequest.of(page - 1, pagingNumber));
		} else if (name != null && name.length() > 0 && category > 0) {
			return (Page<ProductEntity>) productRepository.findByNameAndCategoryId(name, category,
					PageRequest.of(page - 1, pagingNumber));
		} else if ((name == null || name.length() == 0) && category > 0) {
			return (Page<ProductEntity>) productRepository.findByCategoryId(category,
					PageRequest.of(page - 1, pagingNumber));
		} else {
			return (Page<ProductEntity>) productRepository.findAll(PageRequest.of(page - 1, pagingNumber));
		}

	}

	public Page<ProductEntity> getProductPageByDiscount(int id, int page) {

		return (Page<ProductEntity>) productRepository.findByDiscount(id, PageRequest.of(page - 1, pagingNumber));

	}

}
