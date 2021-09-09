package springboot.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.ecommerce.entity.RoleEntity;
import springboot.ecommerce.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	public RoleRepository roleRepository;

	public List<RoleEntity> getList() {
		return (List<RoleEntity>) roleRepository.findAll();
	}

	public void save(RoleEntity role) {
		roleRepository.save(role);
	}

	public RoleEntity findById(int id) {
		return roleRepository.findById(id);
	}

	public void deleteById(int id) {
		roleRepository.deleteById(id);
	}

	public void delete(RoleEntity role) {
		roleRepository.delete(role);
	}

}
