package springboot.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@PropertySource("classpath:common.properties")
@Service
public class SendMailService {
	@Autowired
	private JavaMailSender emailSender;

	@Value("${common.mail.from}")
	private String mailFrom;

	public void sendSimpleMessage(String to, String subject, String text) {
		
		// tao. object SimpleMailMessage de~ chua' mail, nguoi` gui~, nguoi` nhan.,...
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailFrom);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		
		// dung` JavaMailSender de~ send mail
		emailSender.send(message);
	}
}
