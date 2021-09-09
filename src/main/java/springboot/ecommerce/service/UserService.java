package springboot.ecommerce.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import springboot.ecommerce.entity.UserEntity;
import springboot.ecommerce.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;

	@Value("${common.paging.number}")
	public int pagingNumber;

	@Transactional
	public Page<UserEntity> getUsersPaging(int page) {
		return (Page<UserEntity>) userRepository.findAll(PageRequest.of(page - 1, pagingNumber));
	}

	public List<UserEntity> getList() {
		return (List<UserEntity>) userRepository.findAll();
	}

	public void save(UserEntity user) {
		userRepository.save(user);
	}

	public UserEntity findById(int id) {
		return userRepository.findById(id);
	}

	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	public void delete(UserEntity user) {
		userRepository.delete(user);
	}

	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Page<UserEntity> getUserPageBySearch(String keyword, int role, int page) {

		if (keyword != null && keyword.length() > 0 && role == 0) {
			return (Page<UserEntity>) userRepository.findByKeyword(keyword, PageRequest.of(page - 1, pagingNumber));
		} else if (keyword != null && keyword.length() > 0 && role > 0) {
			return (Page<UserEntity>) userRepository.findByKeywordAndRole(keyword, role,
					PageRequest.of(page - 1, pagingNumber));
		} else if ((keyword == null || keyword.length() == 0) && role > 0) {
			return (Page<UserEntity>) userRepository.findByRole(role, PageRequest.of(page - 1, pagingNumber));
		} else {
			return (Page<UserEntity>) userRepository.findAll(PageRequest.of(page - 1, pagingNumber));
		}

	}

}
