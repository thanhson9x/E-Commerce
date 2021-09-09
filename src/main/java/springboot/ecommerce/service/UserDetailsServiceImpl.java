package springboot.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springboot.ecommerce.entity.RoleEntity;
import springboot.ecommerce.entity.UserEntity;
import springboot.ecommerce.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		List<RoleEntity> roles = user.getRoles();
		for (RoleEntity role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				grantedAuthorities);

	}

}
