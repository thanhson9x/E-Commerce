package springboot.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class SpringbootEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEcommerceApplication.class, args);
	}

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public FilterRegistrationBean<CharacterEncodingFilter> characterEncodingFilterRegistration() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);

		FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<>(filter);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(268435456);
		return multipartResolver;
	}

}
