package springboot.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.ecommerce.entity.GenderEntity;
import springboot.ecommerce.repository.GenderRepository;

@Service
public class GenderService {

	@Autowired
	public GenderRepository genderRepository;

	public List<GenderEntity> getList() {
		return (List<GenderEntity>) genderRepository.findAll();
	}

	public void save(GenderEntity gender) {
		genderRepository.save(gender);
	}

	public GenderEntity findById(int id) {
		return genderRepository.findById(id);
	}

	public void deleteById(int id) {
		genderRepository.deleteById(id);
	}
	
	public void delete(GenderEntity gender) {
		genderRepository.delete(gender);
	}

}
