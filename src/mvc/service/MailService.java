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
			//	���� ���
			message.setRecipient(RecipientType.TO, new InternetAddress(target));
			
			//	������ ���
			message.setFrom(new InternetAddress("admin@spring.io"));
			
			//	����
			message.setSubject("[SpringIO] ������ ���ϵ帳�ϴ�.");
			
			//	content ������ text/html;charset=utf-8�̶�� ������ .. HTML�� ���� ���� �ִ�.
			String content = "������ ���ϵ帳�ϴ�.<br/>��뿡 �����Ͻ����� ������ <b>������</b>�� ���� �����ּ���.<br/>";
			content += "������ ���Ͻø� <a href=\"http://192.168.10.65/certification/\">����</a>�� �����ּ���.";
			content += "<a href=\"http://192.168.10.65/\">������ �ѷ�����</a><br/>";
			message.setContent(content, "text/html;charset=utf-8");
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

}