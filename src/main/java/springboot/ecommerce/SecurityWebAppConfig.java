package springboot.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springboot.ecommerce.service.UserDetailsServiceImpl;

//xác định lớp WebSecurityConfig của ta là một lớp dùng để cấu hình
@Configuration
//tích hợp Spring Security với Spring
@EnableWebSecurity
//Để kích hoạt Spring Security, trước tiên ta cần phải viết một lớp kế thừa abstract class 
//WebSecurityConfigurerAdapter
public class SecurityWebAppConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	// CryptPasswordEncoder sẽ giúp chúng ta mã hóa mật khẩu bằng thuật toán BCrypt
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Setting Service to find User in the database.
		// And Setting PassswordEncoder
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

	// Trong phương thức configure(HttpSecurity http), ta sẽ cấu hình các chi tiết
	// về bảo mật
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// .csrf().disable() de~ ko bi loi~ method not allowed
				.csrf().disable().authorizeRequests()
				// khai báo đường dẫn của request
				.antMatchers("/*")
				// cho phép tất cả các user đều được phép truy cập
				.permitAll()
				// khai báo đường dẫn của request
				.antMatchers("/customer/*")
				// chỉ cho phép các user có GrantedAuthority là MEMBER mới được phép truy cập
				.hasAnyRole("CUSTOMER")
				// khai báo đường dẫn của request
				.antMatchers("/admin/*")
				// chỉ cho phép các user có GrantedAuthority là ADMIN mới được phép truy cập
				.hasRole("ADMIN").and().formLogin()
				// đường dẫn tới trang chứa form đăng nhập
				.loginPage("/login").loginProcessingUrl("/authenticateTheUser")
				// gía trị của thuộc tính name của 2 input nhập username và password
				.usernameParameter("email").passwordParameter("password")
				// đường dẫn tới trang đăng nhập thành công
				.defaultSuccessUrl("/forward")
				// đường dẫn tới trang đăng nhập thất bại
				.failureUrl("/login?error").and()
				// Khi người dùng không đủ quyền để truy cập vào một trang -> redirect ve 1 page
				.exceptionHandling().accessDeniedPage("/accessDenied").and().rememberMe().key("uniqueAndSecret")
				.tokenValiditySeconds(43200).and().logout().permitAll();
	}

	public static void main(String[] args) {
		String encoded = new BCryptPasswordEncoder().encode("a");
		System.out.println(encoded);
	}

}
