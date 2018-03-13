package mvc.controller.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.service.MailService;

@Controller
public class TestController {
	@Autowired
	MailService mailService;
	
	@RequestMapping("/test/email")
	public String emailTestHandle(@RequestParam String target) {
		boolean b = mailService.sendWelcomeMail(target);
		System.out.println("메일 전송 결과 = " + b);
		return "certification";
	}
}
