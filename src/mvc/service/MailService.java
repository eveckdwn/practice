package mvc.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	JavaMailSender mailSender;
	
	public boolean sendWelcomeMail(String target) {
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			//	받을 사람
			message.setRecipient(RecipientType.TO, new InternetAddress(target));
			
			//	보내는 사람
			message.setFrom(new InternetAddress("admin@spring.io"));
			
			//	제목
			message.setSubject("[SpringIO] 가입을 축하드립니다.");
			
			//	content 설정을 text/html;charset=utf-8이라고 보내면 .. HTML로 보낼 수도 있다.
			String content = "가입을 축하드립니다.<br/>사용에 불편하신점이 있으면 <b>고객센터</b>에 글을 남겨주세요.<br/>";
			content += "인증을 원하시면 <a href=\"http://192.168.10.65/certification/\">여기</a>를 눌러주세요.";
			content += "<a href=\"http://192.168.10.65/\">페이지 둘러보기</a><br/>";
			message.setContent(content, "text/html;charset=utf-8");
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

}