package springboot.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.ecommerce.entity.CategoryEntity;
import springboot.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	public CategoryRepository categoryRepository;

	public List<CategoryEntity> getList() {
		return (List<CategoryEntity>) categoryRepository.findAll();
	}

	public void save(CategoryEntity category) {
		categoryRepository.save(category);
	}

	public CategoryEntity findById(int id) {
		return categoryRepository.findById(id);
	}

	public void deleteById(int id) {
		categoryRepository.deleteById(id);
	}

	public void delete(CategoryEntity category) {
		categoryRepository.delete(category);
	}

}
